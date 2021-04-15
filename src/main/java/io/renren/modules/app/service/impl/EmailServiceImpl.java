package io.renren.modules.app.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import io.renren.common.exception.RRException;
import io.renren.modules.app.bo.EmailEvent;
import io.renren.modules.app.entity.M4gCampaignEmailsEntity;
import io.renren.modules.app.entity.M4gCampaignsEntity;
import io.renren.modules.app.entity.M4gSubscriberEntity;
import io.renren.modules.app.service.EmailService;
import io.renren.modules.app.service.M4gCampaignEmailsService;
import io.renren.modules.app.service.M4gCampaignsService;
import io.renren.modules.app.service.M4gSubscriberService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    M4gSubscriberService m4gSubscriberService;

    @Autowired
    M4gCampaignsService m4gCampaignsService;

    @Autowired
    M4gCampaignEmailsService m4gCampaignEmailsService;

    @Autowired
    M4gSubscriberService subscriberService;

    // config set ensure bounce stats info are sent to simple message and queue
    static final String CONFIGSET = "stats";

    @Override
    public String sendEmail(String from, String to, String subject, String htmlBody, String textBody, Long campId) {
        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            // Replace US_WEST_2 with the AWS Region you're using for
                            // Amazon SES.
                            .withRegion(Regions.US_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withTags(new MessageTag().withName("campId").withValue(campId.toString()))
                    .withDestination(
                            new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(htmlBody))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(textBody)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(subject)))
                    .withSource(from)
                    // Comment or remove the next line if you are not using a
                    // configuration set
                    .withConfigurationSetName(CONFIGSET);
            SendEmailResult sendEmailResult = client.sendEmail(request);
            return sendEmailResult.getMessageId();
        } catch (Exception ex) {
            throw new RRException("一个以上邮件发送失败，请检查你的收件人等邮件配置。");
        }
    }

    @Override
    @Async
    public void sendByCampaignId(Long id) {
        M4gCampaignsEntity campaignDetail = m4gCampaignsService.getById(id);
        List<M4gSubscriberEntity> subsList = m4gSubscriberService.findValidByTagId(campaignDetail.getTagId());

        // dedup
        subsList = subsList.stream().collect(
                collectingAndThen(
                        toCollection(() -> new TreeSet<>(comparing(M4gSubscriberEntity::getEmail))), ArrayList::new));

        // first update campaign to sending
        campaignDetail.setStatus(3);
        m4gCampaignsService.updateById(campaignDetail);

        String subject = campaignDetail.getSubject();
        String fromEmail = campaignDetail.getFromEmail();
        String body = campaignDetail.getBody();
        String bodyPaint = Jsoup.parse(body).wholeText();

        List<M4gCampaignEmailsEntity> campaignEmailsEntityList = new ArrayList<>();

        // send email one by one
        for (M4gSubscriberEntity subs : subsList) {
            String to = subs.getEmail();
            try {
                String messageId = this.sendEmail(fromEmail, to, subject, this.renderTemplate(body, subs), this.renderTemplate(bodyPaint, subs), id);
                M4gCampaignEmailsEntity record = new M4gCampaignEmailsEntity();
                record.setCampaignId(id);
                record.setEmailId(subs.getId());
                record.setTrackingId(messageId);
                record.setSendTime(new Date());
                campaignEmailsEntityList.add(record);

                // add to db one by one in case the queue listener can't find tracking id
                m4gCampaignEmailsService.save(record);
            } catch (Exception e) {
                System.out.println("一个以上邮件发送失败，请检查你的收件人等邮件配置。");
                // throw new RRException("一个以上邮件发送失败，请检查你的收件人等邮件配置。");
            }
        }
        // update tracking Id(messageId)
//        m4gCampaignEmailsService.createByList(campaignEmailsEntityList);
        campaignDetail.setTotalSentCount(Long.valueOf(campaignEmailsEntityList.size()));
        campaignDetail.setStatus(4);
        m4gCampaignsService.updateById(campaignDetail);
    }

    @Override
    public void handleEvent(String msgId, EmailEvent event) {
        M4gCampaignEmailsEntity record = m4gCampaignEmailsService.getByTrackingId(msgId);
        if (record == null) return;
        Long campaignId = record.getCampaignId();
        M4gCampaignsEntity campaignDetail = m4gCampaignsService.getById(campaignId);
        EmailEvent.EventType eventType = event.getEventType();
        switch (eventType) {
            case Delivery:
                Boolean isDelivered = record.getIsDelivered();
                if (isDelivered != Boolean.TRUE) {
                    record.setIsDelivered(Boolean.TRUE);
                    // todo: also add 1 count for campaign stats
                    campaignDetail.setDeliverCount(campaignDetail.getDeliverCount() + 1);
                    m4gCampaignEmailsService.getBaseMapper().updateById(record);
                    m4gCampaignsService.updateById(campaignDetail);
                    System.out.println("Update delivery for messageId: " + msgId);
                }
                break;
            case Open:
                Boolean isOpen = record.getIsOpen();
                if (isOpen != Boolean.TRUE) {
                    record.setIsOpen(Boolean.TRUE);
                    record.setFirstOpen(event.getOpen().get("timestamp").toString());
                    // todo add 1 count for stats
                    campaignDetail.setOpenCount(campaignDetail.getOpenCount() + 1);
                    m4gCampaignEmailsService.getBaseMapper().updateById(record);
                    m4gCampaignsService.updateById(campaignDetail);
                    System.out.println("Update open for messageId: " + msgId);
                }
                break;
            case Bounce:
                Boolean isBounce = record.getIsBounce();
                if (isBounce != Boolean.TRUE) {
                    record.setIsBounce(Boolean.TRUE);
                    // todo add 1 count for stats
                    campaignDetail.setPermanentBounceCount(campaignDetail.getPermanentBounceCount() + 1);
                    m4gCampaignEmailsService.getBaseMapper().updateById(record);
                    m4gCampaignsService.updateById(campaignDetail);
                    // disable the email
                    M4gSubscriberEntity subscriber = subscriberService.getById(record.getEmailId());
                    subscriber.setIsBounce(Boolean.TRUE);
                    subscriber.setIsValid(Boolean.FALSE);
                    subscriberService.updateById(subscriber);
                    System.out.println("Update bounce for messageId: " + msgId);
                }
                break;

            case Complaint:
                Boolean isComplaint = record.getIsComplaint();
                if (isComplaint != Boolean.TRUE) {
                    record.setIsComplaint(Boolean.TRUE);
                    // todo add 1 count for stats
                    campaignDetail.setComplaintCount(campaignDetail.getComplaintCount() + 1);
                    m4gCampaignEmailsService.getBaseMapper().updateById(record);
                    m4gCampaignsService.updateById(campaignDetail);
                    // disable the email
                    M4gSubscriberEntity subscriber = subscriberService.getById(record.getEmailId());
                    subscriber.setIsComplaint(Boolean.TRUE);
                    subscriber.setIsValid(Boolean.FALSE);
                    subscriberService.updateById(subscriber);
                    System.out.println("Update complaint for messageId: " + msgId);
                }
                break;

            case Reject:
                Boolean isReject = record.getIsReject();
                if (isReject != Boolean.TRUE) {
                    record.setIsReject(Boolean.TRUE);
                    // todo add 1 count for stats
                    campaignDetail.setRejectCount(campaignDetail.getRejectCount() + 1);
                    m4gCampaignEmailsService.getBaseMapper().updateById(record);
                    m4gCampaignsService.updateById(campaignDetail);
                    // disable the email
                    M4gSubscriberEntity subscriber = subscriberService.getById(record.getEmailId());
                    subscriber.setIsReject(Boolean.TRUE);
                    subscriber.setIsValid(Boolean.FALSE);
                    subscriberService.updateById(subscriber);
                    System.out.println("Update reject for messageId: " + msgId);
                }
                break;
            default:
                break;
        }
    }

    private String renderTemplate(String template, M4gSubscriberEntity sub) {
        return template.replace("{{name}}", sub.getName());
    }
}

package io.renren.modules.app.service;

import io.renren.modules.app.bo.EmailEvent;

public interface EmailService {
    String sendEmail(String from, String to, String subject, String htmlBody, String textBody, Long campId);
    void sendByCampaignId(Long id);
    void handleEvent(String msgId, EmailEvent event);
}

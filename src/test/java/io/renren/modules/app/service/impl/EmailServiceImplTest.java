package io.renren.modules.app.service.impl;

import io.renren.modules.app.entity.M4gSubscriberEntity;
import io.renren.modules.app.service.EmailService;
import io.renren.modules.app.service.M4gSubscriberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    EmailService emailService;

    @Autowired
    M4gSubscriberService m4gSubscriberService;

    @Test
    public void sendEmail() {
        String s = emailService.sendEmail("hi@aixin-tech.com",
                "stanleyyylau@gmail.com",
                "test subject",
                "hihi stanely<h1>cool</h1>",
                "hihi stanley", 1L);
        System.out.println(s);
    }

    @Test
    public void sendByCampaignId() {
        emailService.sendByCampaignId(1l);
        System.out.println("hihi");
    }

    @Test
    public void test() {
        List<M4gSubscriberEntity> validByTagId = m4gSubscriberService.findValidByTagId(1l);
        System.out.println(1);
    }
}
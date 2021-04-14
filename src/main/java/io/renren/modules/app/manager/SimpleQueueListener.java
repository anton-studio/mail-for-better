package io.renren.modules.app.manager;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.renren.modules.app.bo.EmailEvent;
import io.renren.modules.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class SimpleQueueListener {
    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    @Autowired
    EmailService emailService;


    @SqsListener(value = "mail-for-better", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void processMessage(String message) {
        try {
            // todo: might receive same msg twice
            Map<String, String> msg = OBJECT_MAPPER.readValue(message, HashMap.class);
            String message1 = msg.get("Message");
            EmailEvent event = OBJECT_MAPPER.readValue(message1, EmailEvent.class);
            String messageId = event.getMail().getMessageId();

            System.out.println("New SQS message: " + event.getEventType().toString() + " to: " + event.getMail().getDestination().toString() + "(" +  messageId + ")"  );

            emailService.handleEvent(messageId, event);
        } catch (Exception e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }
    }
}

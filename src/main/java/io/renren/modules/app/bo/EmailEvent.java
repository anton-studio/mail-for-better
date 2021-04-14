package io.renren.modules.app.bo;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EmailEvent {

    // https://docs.aws.amazon.com/ses/latest/DeveloperGuide/event-publishing-retrieving-sns-contents.html#event-publishing-retrieving-sns-contents-bounce-object
    // don't have time to include all fields
    private EventType eventType;
    private Mail mail;
    private Delivery delivery;
    private Map<String, Object> bounce;
    private Map<String, Object> complaint;
    private Map<String, Object> send;
    private Map<String, Object> reject;
    private Map<String, Object> open;
    private Map<String, Object> click;
    private Map<String, Object> failure;
    private Map<String, Object> deliveryDelay;

    public enum EventType {
        Delivery("Delivery"),
        Reject("Reject"),
        Open("Open"),
        Click("Click"),
        Bounce("Bounce"),
        Complaint("Complaint"),
        RenderingFailure("Rendering Failure"),
        DeliveryDelay("DeliveryDelay"),
        Send("Send");

        private String value;
        EventType(String value) {
            this.value = value;
        }
    }

    @Data
    public class Mail {
        private Date timestamp;
        private String source;
        private String messageId;
        private List<String> destination;
    }

    @Data
    public class Delivery {
        private Date timestamp;
        private Integer processingTimeMillis;
        private String smtpResponse;
        private String reportingMTA;
        private List<String> recipients;
    }

}

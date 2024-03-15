package com.allasassis.notification.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final AmazonSNS amazonSNS;

    public NotificationService(AmazonSNS amazonSNS) {
        this.amazonSNS = amazonSNS;
    }

    public void notify(String message, String phone) {
        amazonSNS.publish(new PublishRequest().withMessage(message).withPhoneNumber(phone));
    }
}

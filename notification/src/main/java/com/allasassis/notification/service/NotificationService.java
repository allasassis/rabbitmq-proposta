package com.allasassis.notification.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private AmazonSNS amazonSNS;

    public void notify(String message, String phone) {
        amazonSNS.publish(new PublishRequest().withMessage(message).withPhoneNumber(phone));
    }
}

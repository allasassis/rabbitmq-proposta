package com.allasassis.notification.listener;

import com.allasassis.notification.constant.ConstantMessage;
import com.allasassis.notification.domain.Proposal;
import com.allasassis.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PendingProposeListener {

    private final NotificationService notificationService;

    public PendingProposeListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.pending.propose}")
    public void pendingPropose(Proposal proposal) {
        String message = String.format(ConstantMessage.PROPOSAL_IN_ANALYSIS, proposal.getUser().getName());
        notificationService.notify(message);
    }
}

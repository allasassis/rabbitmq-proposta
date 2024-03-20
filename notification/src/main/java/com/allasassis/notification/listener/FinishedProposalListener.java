package com.allasassis.notification.listener;

import com.allasassis.notification.constant.ConstantMessage;
import com.allasassis.notification.domain.Proposal;
import com.allasassis.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FinishedProposalListener {

    private final NotificationService notificationService;

    public FinishedProposalListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.finished.propose}")
    public void finishedProposal(Proposal proposal) {
        String message;
        if (proposal.getApproved()) {
            message = String.format(ConstantMessage.PROPOSAL_APPROVED, proposal.getUser().getName());
        } else {
            message = String.format(ConstantMessage.PROPOSAL_DENIED, proposal.getUser().getName(), proposal.getObservation());
        }
        notificationService.notify(message, proposal.getUser().getPhone());
    }
}

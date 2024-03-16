package com.allasassis.creditanalysis.listener;

import com.allasassis.creditanalysis.domain.Proposal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PendingProposeListener {

    @RabbitListener(queues = "${rabbitmq.queue.pending.propose}")
    public void pendingPropose(Proposal proposal) {
    }
}

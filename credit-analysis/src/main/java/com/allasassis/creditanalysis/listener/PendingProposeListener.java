package com.allasassis.creditanalysis.listener;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.CreditAnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PendingProposeListener {

    private final CreditAnalysisService creditAnalysisService;

    public PendingProposeListener(CreditAnalysisService creditAnalysisService) {
        this.creditAnalysisService = creditAnalysisService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.pending.propose}")
    public void pendingPropose(Proposal proposal) {
            creditAnalysisService.analyse(proposal);
    }
}

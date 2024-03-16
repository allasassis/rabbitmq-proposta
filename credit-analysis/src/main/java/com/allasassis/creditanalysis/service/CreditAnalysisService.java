package com.allasassis.creditanalysis.service;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.exception.StrategyException;
import com.allasassis.creditanalysis.service.CalculationPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    // Spring is smart enough to understand that we want a list with ALL the implementations of CalculationPoint
    // interface, although they need to be managed by Spring with @Component
    private final List<CalculationPoint> calculationPointList;

    private final NotificationRabbitService notificationRabbitService;

    @Value("${rabbitmq.finishedpropose.exchange}")
    private String exchangeFinishedPropose;

    public CreditAnalysisService(List<CalculationPoint> calculationPointList, NotificationRabbitService notificationRabbitService) {
        this.calculationPointList = calculationPointList;
        this.notificationRabbitService = notificationRabbitService;
    }

    public void analyse(Proposal proposal) {
        try {
            boolean approved = calculationPointList.stream().mapToInt(value -> value.calculate(proposal)).sum() > 350;
            proposal.setApproved(approved);
        } catch (StrategyException e) {
            proposal.setApproved(false);
            proposal.setObservation(e.getMessage());
        }
        notificationRabbitService.notify(exchangeFinishedPropose, proposal);
    }
}

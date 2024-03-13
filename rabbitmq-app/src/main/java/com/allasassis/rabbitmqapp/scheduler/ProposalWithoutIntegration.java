package com.allasassis.rabbitmqapp.scheduler;

import com.allasassis.rabbitmqapp.repository.ProposalRepository;
import com.allasassis.rabbitmqapp.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ProposalWithoutIntegration {

    private final ProposalRepository proposalRepository;

    private final NotificationService notificationService;

    private final String exchange;

    public ProposalWithoutIntegration(ProposalRepository proposalRepository, NotificationService notificationService, @Value("${rabbitmq.pendingpropose.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void getProposalWithoutIntegration() {
        proposalRepository.findAllByIntegratedIsFalse().forEach(proposal -> {
                notificationService.notify(proposal, exchange);
        });
    }
}

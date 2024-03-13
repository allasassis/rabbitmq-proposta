package com.allasassis.rabbitmqapp.service;

import com.allasassis.rabbitmqapp.entity.Proposal;
import com.allasassis.rabbitmqapp.repository.ProposalRepository;
import com.allasassis.rabbitmqapp.scheduler.ProposalWithoutIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProposalWithoutIntegration.class);

    private final RabbitTemplate rabbitTemplate;

    private final ProposalRepository proposalRepository;

    public NotificationService(RabbitTemplate rabbitTemplate, ProposalRepository proposalRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.proposalRepository = proposalRepository;
    }

    public void notify(Proposal proposal, String exchange) {
        try {
            rabbitTemplate.convertAndSend(exchange, "", proposal);
            updateProposal(proposal);
        } catch (RuntimeException ex) {
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);
            LOGGER.error(ex.getMessage());
        }
    }

    private void updateProposal(Proposal proposal) {
        proposal.setIntegrated(true);
        proposalRepository.save(proposal);
    }
}

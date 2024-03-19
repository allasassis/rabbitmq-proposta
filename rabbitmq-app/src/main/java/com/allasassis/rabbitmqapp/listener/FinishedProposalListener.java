package com.allasassis.rabbitmqapp.listener;

import com.allasassis.rabbitmqapp.entity.Proposal;
import com.allasassis.rabbitmqapp.repository.ProposalRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class FinishedProposalListener {

    @Autowired
    private ProposalRepository proposalRepository;

    @RabbitListener(queues = "${rabbitmq.queue.finished.propose}")
    public void finishedProposal(Proposal proposal) {
        proposalRepository.save(proposal);
    }
}

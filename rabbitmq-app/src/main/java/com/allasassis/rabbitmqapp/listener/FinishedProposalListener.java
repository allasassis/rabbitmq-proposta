package com.allasassis.rabbitmqapp.listener;

import com.allasassis.rabbitmqapp.entity.Proposal;
import com.allasassis.rabbitmqapp.mapper.ProposalMapper;
import com.allasassis.rabbitmqapp.repository.ProposalRepository;
import com.allasassis.rabbitmqapp.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class FinishedProposalListener {

    private ProposalRepository proposalRepository;

    private WebSocketService webSocketService;

    @RabbitListener(queues = "${rabbitmq.queue.finished.propose}")
    public void finishedProposal(Proposal proposal) {
        proposalRepository.updateProposal(proposal.getId(), proposal.getApproved(), proposal.getObservation());
        webSocketService.notify(ProposalMapper.INSTANCE.convertEntityToDto(proposal));
    }
}

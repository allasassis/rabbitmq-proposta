package com.allasassis.rabbitmqapp.service;

import com.allasassis.rabbitmqapp.dto.ProposalRequestDto;
import com.allasassis.rabbitmqapp.dto.ProposalResponseDto;
import com.allasassis.rabbitmqapp.entity.Proposal;
import com.allasassis.rabbitmqapp.mapper.ProposalMapper;
import com.allasassis.rabbitmqapp.repository.ProposalRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;

    private final NotificationService notificationService;

    private final String exchange;

    public ProposalService(ProposalRepository proposalRepository, NotificationService notificationService, @Value("${rabbitmq.pendingpropose.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public ProposalResponseDto create(ProposalRequestDto dto) {
        Proposal proposal = ProposalMapper.INSTANCE.convertDtoToProposal(dto);
        proposalRepository.save(proposal);
        int priority = proposal.getUser().getSalary() > 10000 ? 10 : 5;

        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setPriority(priority);
            return message;
        };

        notificationService.notify(proposal, exchange);
        return ProposalMapper.INSTANCE.convertEntityToDto(proposal);
    }

    public List<ProposalResponseDto> getProposals() {
        return ProposalMapper.INSTANCE.convertListEntityToDto(proposalRepository.findAll());
    }
}

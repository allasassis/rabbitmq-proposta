package com.allasassis.rabbitmqapp.service;

import com.allasassis.rabbitmqapp.dto.ProposalResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate template;

    public WebSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void notify(ProposalResponseDto responseDto) {
        template.convertAndSend("/propostas", responseDto);
    }
}

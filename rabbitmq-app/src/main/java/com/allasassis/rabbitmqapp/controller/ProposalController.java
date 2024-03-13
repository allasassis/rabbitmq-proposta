package com.allasassis.rabbitmqapp.controller;

import com.allasassis.rabbitmqapp.dto.ProposalRequestDto;
import com.allasassis.rabbitmqapp.dto.ProposalResponseDto;
import com.allasassis.rabbitmqapp.service.ProposalService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proposta")
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public ResponseEntity<ProposalResponseDto> create(@RequestBody ProposalRequestDto dto) {
        ProposalResponseDto response = proposalService.create(dto);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri()).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProposalResponseDto>> getProposals() {
        return ResponseEntity.ok(proposalService.getProposals());
    }
}

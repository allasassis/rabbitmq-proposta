package com.allasassis.rabbitmqapp.mapper;

import com.allasassis.rabbitmqapp.dto.ProposalRequestDto;
import com.allasassis.rabbitmqapp.dto.ProposalResponseDto;
import com.allasassis.rabbitmqapp.entity.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface ProposalMapper {

    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastName", source = "lastName")
    @Mapping(target = "user.identity", source = "identity")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.salary", source = "salary")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", constant = "true")
    @Mapping(target = "observation", ignore = true)
    Proposal convertDtoToProposal(ProposalRequestDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "identity", source = "user.identity")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "salary", source = "user.salary")
    @Mapping(target = "valueRequested", expression = "java(setValueRequested(proposal))")
    ProposalResponseDto convertEntityToDto(Proposal proposal);

    List<ProposalResponseDto> convertListEntityToDto(Iterable<Proposal> proposals);

    default String setValueRequested(Proposal proposal) {
        return NumberFormat.getCurrencyInstance().format(proposal.getValueRequested());
    }
}

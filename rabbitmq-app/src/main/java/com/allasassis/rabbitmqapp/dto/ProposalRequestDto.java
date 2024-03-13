package com.allasassis.rabbitmqapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProposalRequestDto {

    @JsonProperty("nome")
    private String name;
    @JsonProperty("sobrenome")
    private String lastName;
    @JsonProperty("cpf")
    private String identity;
    @JsonProperty("telefone")
    private String phone;
    @JsonProperty("renda")
    private Double salary;
    @JsonProperty("valorSolicitado")
    private Double valueRequested;
    @JsonProperty("prazoPagamento")
    private Integer paymentDeadline;
}

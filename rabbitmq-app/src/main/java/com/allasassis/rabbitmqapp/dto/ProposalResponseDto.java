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
public class ProposalResponseDto {

    private Long id;
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

    @JsonProperty("valorSolicitadoFmt")
    private String valueRequested;

    @JsonProperty("prazoPagamento")
    private Integer paymentDeadline;

    @JsonProperty("aprovada")
    private Boolean approved;

    @JsonProperty("integrado")
    private Boolean integrated;

    @JsonProperty("observacao")
    private String observation;
}

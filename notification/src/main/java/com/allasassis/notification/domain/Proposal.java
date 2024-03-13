package com.allasassis.notification.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposal {


    private Long id;

    private Double valueRequested;

    private Integer paymentDeadline;

    private Boolean approved;

    private Boolean integrated;

    private String observation;

    private User user;
}

package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.strategy.CalculationPoint;

import java.util.Random;

public class BadCreditImpl implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        if (badCredit()) {
            throw new RuntimeException("The customer has bad credit!");
        }
        return 100;
    }

    private boolean badCredit() {
        return new Random().nextBoolean();
    }
}

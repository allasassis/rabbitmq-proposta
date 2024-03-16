package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.strategy.CalculationPoint;

import java.util.Random;

public class OtherLoansInProgress implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        if (!thereAreOtherLoansInProgress()) {
            return 80;
        }
        return 0;
    }

    private boolean thereAreOtherLoansInProgress() {
        return new Random().nextBoolean();
    }
}

package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.CalculationPoint;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
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

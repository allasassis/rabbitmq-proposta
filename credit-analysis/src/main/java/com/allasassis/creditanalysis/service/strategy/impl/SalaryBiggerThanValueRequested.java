package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.CalculationPoint;
import org.springframework.stereotype.Component;

@Component
public class SalaryBiggerThanValueRequested implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        return isSalaryBiggerThanValueRequested(proposal) ? 100 : 0;
    }

    private boolean isSalaryBiggerThanValueRequested(Proposal proposal) {
        return proposal.getValueRequested() <= proposal.getUser().getSalary();
    }
}

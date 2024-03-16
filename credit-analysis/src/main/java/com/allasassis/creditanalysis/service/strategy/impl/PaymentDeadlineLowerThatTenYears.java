package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.strategy.CalculationPoint;

public class PaymentDeadlineLowerThatTenYears implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        return isSPaymentDeadlineLowerThatTenYears(proposal) ? 80 : 0;
    }

    private boolean isSPaymentDeadlineLowerThatTenYears(Proposal proposal) {
        return proposal.getPaymentDeadline() < 120;
    }
}

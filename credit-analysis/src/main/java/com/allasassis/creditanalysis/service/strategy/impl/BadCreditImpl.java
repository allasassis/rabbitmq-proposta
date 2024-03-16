package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.constant.ConstantMessage;
import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.exception.StrategyException;
import com.allasassis.creditanalysis.service.CalculationPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(1)
@Component
public class BadCreditImpl implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        if (badCredit()) {
            throw new StrategyException(String.format(ConstantMessage.BAD_CREDIT, proposal.getUser().getName()));
        }
        return 100;
    }

    private boolean badCredit() {
        return new Random().nextBoolean();
    }
}

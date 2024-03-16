package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.constant.ConstantMessage;
import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.exception.StrategyException;
import com.allasassis.creditanalysis.service.CalculationPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Order(2)
@Component
public class PunctuationScoreImpl implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        int score = score();
        if (score < 200) {
            throw new StrategyException(String.format(ConstantMessage.LOW_SCORE, proposal.getUser().getName()));
        } else if (score <= 400) {
            return 150;
        } else if (score <= 600) {
            return 180;
        } else {
            return 220;
        }
    }

    private int score() {
        return new Random().nextInt(0, 1000);
    }
}


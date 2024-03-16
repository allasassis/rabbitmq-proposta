package com.allasassis.creditanalysis.service.strategy.impl;

import com.allasassis.creditanalysis.domain.Proposal;
import com.allasassis.creditanalysis.service.strategy.CalculationPoint;

import java.util.Random;

public class PunctuationScoreImpl implements CalculationPoint {

    @Override
    public int calculate(Proposal proposal) {
        int score = score();
        if (score <= 200) {
            throw new RuntimeException("The customer has low score!");
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

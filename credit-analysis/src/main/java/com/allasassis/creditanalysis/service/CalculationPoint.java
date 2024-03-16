package com.allasassis.creditanalysis.service;

import com.allasassis.creditanalysis.domain.Proposal;

public interface CalculationPoint {

    int calculate(Proposal proposal);
}

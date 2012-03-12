package com.educatedsolutions.parser.terms;

import org.apache.commons.math.fraction.BigFraction;

public class FractionTerm implements Term {
    
    private BigFraction fraction;
    
    public FractionTerm(NumberTerm numerator, NumberTerm denominator) {
        
    }

    public String toLatexString() {
        return null;
    }

}

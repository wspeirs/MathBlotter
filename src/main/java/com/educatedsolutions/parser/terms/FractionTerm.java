package com.educatedsolutions.parser.terms;

import org.apache.commons.math.fraction.BigFraction;

public class FractionTerm implements Term {
    
    private BigFraction fraction;
    
    public FractionTerm(NumberTerm numerator, NumberTerm denominator) {
        fraction = new BigFraction(numerator.getNumber().toBigInteger(), denominator.getNumber().toBigInteger());
    }

    public String toLatexString() {
        StringBuilder sb = new StringBuilder(fraction.getNumerator().toString());
        
        sb.append(" \\over ");
        sb.append(fraction.getDenominator().toString());
        
        return sb.toString();
    }

}

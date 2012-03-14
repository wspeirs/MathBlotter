package com.educatedsolutions.parser.terms;

import org.apache.commons.math.fraction.BigFraction;

public class FractionTerm implements Term {
    
    private BigFraction fraction;
    
    public FractionTerm(NumberTerm numerator, NumberTerm denominator) {
        fraction = new BigFraction(numerator.getNumber().toBigInteger(), denominator.getNumber().toBigInteger());
    }

    public String toLatexString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\\frac {");
        sb.append(fraction.getNumerator().toString());
        sb.append("}");
        sb.append("{");
        sb.append(fraction.getDenominator().toString());
        sb.append("}");
        
        return sb.toString();
    }

}

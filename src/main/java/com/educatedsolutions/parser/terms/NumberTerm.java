package com.educatedsolutions.parser.terms;

import java.math.BigDecimal;

public class NumberTerm implements Term {
    
    private BigDecimal number;
    
    public NumberTerm(String number) {
        this.number = new BigDecimal(number);
    }

    public String toLatexString() {
        return number.toString();
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void negate() {
        number.negate();
    }
}

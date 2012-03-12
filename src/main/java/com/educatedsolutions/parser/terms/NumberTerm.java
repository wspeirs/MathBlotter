package com.educatedsolutions.parser.terms;

import java.math.BigDecimal;

public class NumberTerm implements Term {
    
    private BigDecimal number;
    
    public NumberTerm(String number) {
        this.number = new BigDecimal(number);
    }

    public String toLatexString() {
        return null;
    }

    public BigDecimal getNumber() {
        return number;
    }

}

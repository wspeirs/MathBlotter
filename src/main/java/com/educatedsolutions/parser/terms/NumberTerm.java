package com.educatedsolutions.parser.terms;

import java.math.BigDecimal;

public class NumberTerm extends ValueTerm implements Term {
    
    private BigDecimal number;
    
    public NumberTerm(String number) {
        this.number = new BigDecimal(number);
    }

    public String toLatexString() {
        return super.toLatexString() + number.toString();
    }

    public BigDecimal getNumber() {
        return number;
    }
}

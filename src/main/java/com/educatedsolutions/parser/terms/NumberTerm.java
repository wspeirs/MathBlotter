package com.educatedsolutions.parser.terms;

import java.math.BigDecimal;
import java.util.List;

public class NumberTerm extends ValueTerm implements Term {
    
    private BigDecimal number;
    
    public NumberTerm(String number) {
        this.number = new BigDecimal(number);
    }

    public NumberTerm(BigDecimal number) {
        this.number = number;
    }

    @Override
    public String toLatexString() {
        return super.toLatexString() + number.toString();
    }
    
    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public BigDecimal getNumber() {
        return number;
    }
}

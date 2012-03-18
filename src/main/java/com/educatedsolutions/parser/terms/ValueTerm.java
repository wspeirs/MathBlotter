package com.educatedsolutions.parser.terms;

public abstract class ValueTerm implements Term {
    
    boolean negative;
    
    @Override
    public String toLatexString() {
        return negative ? "-" : "";
    }

    public boolean isNegative() {
        return negative;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }
    
    public void negate() {
        negative = negative ? false : true;
    }

}

package com.educatedsolutions.parser.terms;

import java.util.List;

public class NumberTerm implements Term {
    
    private double number;
    private boolean isInteger;
    
    public static final NumberTerm NEGATIVE_ONE = new NumberTerm("-1");
    
    public NumberTerm(String number) {
        this.number = Double.parseDouble(number);
        isInteger = Math.floor(this.number) == this.number;
    }

    public NumberTerm(double number) {
        this.number = number;
        isInteger = Math.floor(this.number) == this.number;
    }

    @Override
    public String toLatexString() {
        if(isInteger) {
            return ((int)number) + "";
        } else {
            return number + "";
        }
    }
    
    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public double getNumber() {
        return number;
    }
    
    public boolean isInteger() {
        return isInteger;
    }
}

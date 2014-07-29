package com.mathblotter.parser.terms;

import java.util.List;

public class NumberTerm implements NegatableTerm {

    private double number;
    private boolean isInteger;

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

    @Override
    public NegatableTerm negate() {
        number *= -1;

        return this;
    }

    @Override
    public boolean isNegative() {
        return number < 0;
    }
}

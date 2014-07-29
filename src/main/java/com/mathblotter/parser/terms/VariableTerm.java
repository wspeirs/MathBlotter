package com.mathblotter.parser.terms;

import java.util.List;

public class VariableTerm implements NegatableTerm {

    private String variable;
    private boolean isNegative;

    public VariableTerm(String variable) {
        this.variable = variable;
    }

    @Override
    public String toLatexString() {
        return isNegative ? "-" + variable : variable;
    }

    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public String getVarible() {
        return variable;
    }

    @Override
    public NegatableTerm negate() {
        isNegative = isNegative ? false : true;

        return this;
    }

    @Override
    public boolean isNegative() {
        return isNegative;
    }

}

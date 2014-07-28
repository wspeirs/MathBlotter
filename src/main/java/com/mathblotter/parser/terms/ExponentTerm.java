package com.mathblotter.parser.terms;

import java.util.List;

public class ExponentTerm implements Term {
    
    private Term base;
    private Term exponent;

    public ExponentTerm(Term base, Term exponent) {
        this.base = base;
        this.exponent = exponent;
    }
    
    @Override
    public String toLatexString() {
        return base.toLatexString() + "^{" + exponent.toLatexString() + "}";
    }
    
    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public Term getBase() {
        return base;
    }

    public Term getExponent() {
        return exponent;
    }

}

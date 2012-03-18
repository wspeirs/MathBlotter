package com.educatedsolutions.parser.terms;

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

    public Term getBase() {
        return base;
    }

    public Term getExponent() {
        return exponent;
    }

}

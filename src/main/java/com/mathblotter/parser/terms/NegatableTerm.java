package com.mathblotter.parser.terms;


public interface NegatableTerm extends Term {

    public NegatableTerm negate();

    public boolean isNegative();
}

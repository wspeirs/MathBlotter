package com.educatedsolutions.parser.terms;

import java.util.List;

public interface TermVisitor {

    public Term visit(Term term, List<Term> children);
    public Term visit(ExponentTerm term, List<Term> children);
    public Term visit(NumberTerm term, List<Term> children);
    public Term visit(PolynomialTerm term, List<Term> children);
    public Term visit(ProductTerm term, List<Term> children);
    public Term visit(SubExpressionTerm term, List<Term> children);
    public Term visit(SumTerm term, List<Term> children);
    public Term visit(VariableTerm term, List<Term> children);
}

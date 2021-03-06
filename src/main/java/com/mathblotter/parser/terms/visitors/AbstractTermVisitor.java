package com.mathblotter.parser.terms.visitors;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mathblotter.parser.terms.NumberTerm;
import com.mathblotter.parser.terms.PolynomialTerm;
import com.mathblotter.parser.terms.SubExpressionTerm;
import com.mathblotter.parser.terms.Term;
import com.mathblotter.parser.terms.TermVisitor;
import com.mathblotter.parser.terms.VariableTerm;

public abstract class AbstractTermVisitor implements TermVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(TermSimplifier.class);

    @Override
    public Term visit(Term term, List<Term> children) {
        return term;
    }

    @Override
    public Term visit(NumberTerm term, List<Term> children) {
        LOG.debug("Visiting NumberTerm: {}", term.toLatexString());
        return term;
    }

    @Override
    public Term visit(PolynomialTerm term, List<Term> children) {
        LOG.debug("Visiting PolynomialTerm: {}", term.toLatexString());
        return term;
    }

    @Override
    public Term visit(SubExpressionTerm term, List<Term> children) {
        return children.get(0);
    }

    @Override
    public Term visit(VariableTerm term, List<Term> children) {
        LOG.debug("Visiting VariableTerm: {}", term.toLatexString());
        return term;
    }

}

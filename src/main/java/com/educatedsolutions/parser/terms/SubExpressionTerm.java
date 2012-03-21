package com.educatedsolutions.parser.terms;

import java.util.List;


public class SubExpressionTerm implements Term {
    
    private Term term;
    
    public SubExpressionTerm(Term term) {
        this.term = term;
    }

    @Override
    public String toLatexString() {
        return "(" + term.toLatexString() + ")";
    }

    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public Term getTerm() {
        return term;
    }
    
}

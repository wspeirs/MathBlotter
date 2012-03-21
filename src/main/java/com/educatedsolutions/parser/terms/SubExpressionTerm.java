package com.educatedsolutions.parser.terms;


public class SubExpressionTerm implements Term {
    
    private Term term;
    
    public SubExpressionTerm(Term term) {
        this.term = term;
    }

    public String toLatexString() {
        return "(" + term.toLatexString() + ")";
    }

    public Term getTerm() {
        return term;
    }
    
}

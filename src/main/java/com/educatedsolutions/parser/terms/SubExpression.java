package com.educatedsolutions.parser.terms;


public class SubExpression implements Term {
    
    private Term term;
    
    public SubExpression(Term term) {
        this.term = term;
    }

    public String toLatexString() {
        return "";
    }

    public Term getTerm() {
        return term;
    }
    
}

package com.educatedsolutions.parser.terms;

import java.util.List;

public class SumTerm implements Term {

    private List<Term> terms;
    
    public SumTerm(List<Term> terms) {
        this.terms = terms;
    }
    
    public String toLatexString() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Term> getTerms() {
        return terms;
    }

}

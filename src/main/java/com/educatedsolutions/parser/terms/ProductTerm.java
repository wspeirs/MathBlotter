package com.educatedsolutions.parser.terms;

import java.util.List;

public class ProductTerm implements Term {
    
    private List<Term> terms;
    
    public ProductTerm(List<Term> terms) {
        this.terms = terms;
    }

    public String toLatexString() {
        return null;
    }

    public List<Term> getTerms() {
        return terms;
    }

}

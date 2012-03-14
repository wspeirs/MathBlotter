package com.educatedsolutions.parser.terms;

import java.util.List;

public class ProductTerm implements Term {
    
    private List<Term> terms;
    
    public ProductTerm(List<Term> terms) {
        this.terms = terms;
    }

    public String toLatexString() {
        StringBuilder sb = new StringBuilder(terms.get(0).toLatexString());
        
        for(int i=1; i < terms.size(); ++i) {
            sb.append(" * ");
            sb.append(terms.get(i).toLatexString());
        }
        
        return sb.toString();
    }

    public List<Term> getTerms() {
        return terms;
    }

}

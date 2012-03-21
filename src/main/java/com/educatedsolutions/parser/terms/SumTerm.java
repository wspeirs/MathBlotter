package com.educatedsolutions.parser.terms;

import java.util.List;

public class SumTerm implements Term {

    private List<Term> terms;
    
    public SumTerm(List<Term> terms) {
        this.terms = terms;
    }
    
    @Override
    public String toLatexString() {
        StringBuilder sb = new StringBuilder(terms.get(0).toLatexString());
        
        for(int i=1; i < terms.size(); ++i) {
            sb.append(" + ");
            sb.append(terms.get(i).toLatexString());
        }
        
        return sb.toString();
    }

    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public List<Term> getTerms() {
        return terms;
    }

}

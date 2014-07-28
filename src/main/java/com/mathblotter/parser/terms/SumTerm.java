package com.mathblotter.parser.terms;

import java.util.List;

public class SumTerm implements Term {

    private List<Term> terms;
    private String operations;
    
    public SumTerm(List<Term> terms, String operations) {
        this.terms = terms;
        this.operations = operations;
    }
    
    @Override
    public String toLatexString() {
        StringBuilder sb = new StringBuilder(terms.get(0).toLatexString());
        
        for(int i=1; i < terms.size(); ++i) {
            sb.append(operations.charAt(i-1));
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
    
    public String getOperations() {
        return operations;
    }

}

package com.educatedsolutions.parser.terms;

import java.util.List;

public class ProductTerm implements Term {
    
    private List<Term> terms;
    private String operations;
    
    public ProductTerm(List<Term> terms, String operations) {
        this.terms = terms;
        this.operations = operations;
    }

    @Override
    public String toLatexString() {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i < operations.length(); ++i) {
            if(operations.charAt(i) == '/') {
                sb.append("\\frac{");
                sb.append(terms.get(i).toLatexString());
                sb.append("}{");
                sb.append(terms.get(i+1).toLatexString());
                sb.append("}");
            } else {
                sb.append(terms.get(i).toLatexString());
                sb.append("*");
                sb.append(terms.get(i+1).toLatexString());
            }
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

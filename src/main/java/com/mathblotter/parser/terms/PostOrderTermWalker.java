package com.mathblotter.parser.terms;

import java.util.ArrayList;
import java.util.List;

public class PostOrderTermWalker {

    private TermVisitor visitor;
    
    public PostOrderTermWalker(TermVisitor visitor) {
        this.visitor = visitor;
    }
    
    public Term walk(Term term) {
        List<Term> terms = new ArrayList<Term>();

        // only certain Terms have children, so switch based on term type
        if(term instanceof ExponentTerm) {
            terms.add(walk(((ExponentTerm)term).getBase()));
            terms.add(walk(((ExponentTerm)term).getExponent()));
        } else if(term instanceof ProductTerm) {
            for(Term t:((ProductTerm)term).getTerms()) {
                terms.add(walk(t));
            }
        } else if(term instanceof SumTerm) {
            for(Term t:((SumTerm)term).getTerms()) {
                terms.add(walk(t));
            }
        } else if(term instanceof SubExpressionTerm) {
            terms.add(walk(((SubExpressionTerm)term).getTerm()));
        }
        
        return term.accept(visitor, terms);
    }
}

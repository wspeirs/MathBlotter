package com.educatedsolutions.parser.terms.visitors;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.terms.ExponentTerm;
import com.educatedsolutions.parser.terms.NumberTerm;
import com.educatedsolutions.parser.terms.ProductTerm;
import com.educatedsolutions.parser.terms.SumTerm;
import com.educatedsolutions.parser.terms.Term;

public class TermSimplifier extends AbstractTermVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(TermSimplifier.class);
    
    /**
     * Checks to see if all the Term in a list are NumberTerm 
     * @param terms The terms to check.
     * @return True if all NumberTerm.
     */
    private boolean allNumbers(List<Term> terms) {
        for(Term c:terms) {
            if(!(c instanceof NumberTerm)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Term visit(ExponentTerm term, List<Term> children) {
        if(children.get(0) instanceof NumberTerm &&
           children.get(1) instanceof NumberTerm) {
            LOG.debug("EXPONENT SIMPLIFY: {}", term.toLatexString());
            
            NumberTerm base = (NumberTerm) children.get(0);
            NumberTerm exponent = (NumberTerm) children.get(1);
            
            double ret = Math.pow(base.getNumber(), exponent.getNumber());
            
            LOG.debug("RESULT: {}", ret);

            return new NumberTerm(ret);
        }
        
        return term;
    }

    @Override
    public Term visit(ProductTerm term, List<Term> children) {
        
        if(allNumbers(children)) {
            LOG.debug("PRODUCT SIMPLIFY: {}", term.toLatexString());

            double ret = ((NumberTerm)children.get(0)).getNumber();
            String operations = term.getOperations();
            
            for(int i=0; i < operations.length(); ++i) {
                if(operations.charAt(i) == '*') {
                    ret *= ((NumberTerm)children.get(i+1)).getNumber();
                } else {
                    ret /= ((NumberTerm)children.get(i+1)).getNumber();
                }
            }

            LOG.debug("RESULT: {}", ret);

            return new NumberTerm(ret);
        }
        
        return term;
    }

    @Override
    public Term visit(SumTerm term, List<Term> children) {
        
        if(allNumbers(children)) {
            LOG.debug("SUM SIMPLIFY: {}", term.toLatexString());

            double ret = ((NumberTerm)children.get(0)).getNumber();
            String operations = term.getOperations();
            
            for(int i=0; i < operations.length(); ++i) {
                if(operations.charAt(i) == '+') {
                    ret += ((NumberTerm)children.get(i+1)).getNumber();
                } else {
                    ret -= ((NumberTerm)children.get(i+1)).getNumber();
                }
            }

            LOG.debug("RESULT: {}", ret);
            
            return new NumberTerm(ret);
        }
        
        return term;
    }

}

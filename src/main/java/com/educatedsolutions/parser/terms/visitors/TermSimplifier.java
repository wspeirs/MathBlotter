package com.educatedsolutions.parser.terms.visitors;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.terms.ExponentTerm;
import com.educatedsolutions.parser.terms.NumberTerm;
import com.educatedsolutions.parser.terms.ProductTerm;
import com.educatedsolutions.parser.terms.SubExpressionTerm;
import com.educatedsolutions.parser.terms.SumTerm;
import com.educatedsolutions.parser.terms.Term;

public class TermSimplifier extends AbstractTermVisitor {
    private static final Logger LOG = LoggerFactory.getLogger(TermSimplifier.class);

    @Override
    public Term visit(ExponentTerm term, List<Term> children) {
        if(children.get(0) instanceof NumberTerm &&
           children.get(1) instanceof NumberTerm) {
            LOG.debug("EXPONENT SIMPLIFY: {}", term.toLatexString());
            
            NumberTerm base = (NumberTerm) children.get(0);
            NumberTerm exponent = (NumberTerm) children.get(1);
            
            BigDecimal ret = base.getNumber().pow(exponent.getNumber().intValue());
            
            LOG.debug("RESULT: {}", ret);

            return new NumberTerm(ret);
        }
        
        return term;
    }

    @Override
    public Term visit(ProductTerm term, List<Term> children) {
        boolean allNumbers = true;
        
        for(Term c:children) {
            if(!(c instanceof NumberTerm)) {
                allNumbers = false;
                break;
            }
        }
        
        if(allNumbers) {
            LOG.debug("PRODUCT SIMPLIFY: {}", term.toLatexString());

            BigDecimal ret = BigDecimal.ONE;
            
            for(Term c:children) {
                ret = ret.multiply(((NumberTerm)c).getNumber());
            }

            LOG.debug("RESULT: {}", ret);

            return new NumberTerm(ret);
        }
        
        return term;
    }

    @Override
    public Term visit(SumTerm term, List<Term> children) {
        boolean allNumbers = true;
        
        for(Term c:children) {
            if(!(c instanceof NumberTerm)) {
                allNumbers = false;
                break;
            }
        }
        
        if(allNumbers) {
            LOG.debug("SUM SIMPLIFY: {}", term.toLatexString());

            BigDecimal ret = BigDecimal.ZERO;
            
            for(Term c:children) {
                ret = ret.add(((NumberTerm)c).getNumber());
            }

            LOG.debug("RESULT: {}", ret);
            
            return new NumberTerm(ret);
        }
        
        return term;
    }

}

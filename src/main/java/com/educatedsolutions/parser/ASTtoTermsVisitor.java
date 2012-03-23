/**
 * 
 */
package com.educatedsolutions.parser;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.javacc.ASTAdditiveExpression;
import com.educatedsolutions.parser.javacc.ASTAssignment;
import com.educatedsolutions.parser.javacc.ASTBindingVariable;
import com.educatedsolutions.parser.javacc.ASTConstant;
import com.educatedsolutions.parser.javacc.ASTEquality;
import com.educatedsolutions.parser.javacc.ASTEquationVariable;
import com.educatedsolutions.parser.javacc.ASTExponentiationExpression;
import com.educatedsolutions.parser.javacc.ASTExpression;
import com.educatedsolutions.parser.javacc.ASTInequality;
import com.educatedsolutions.parser.javacc.ASTInteger;
import com.educatedsolutions.parser.javacc.ASTMultiplicativeExpression;
import com.educatedsolutions.parser.javacc.ASTNumber;
import com.educatedsolutions.parser.javacc.ASTPrimaryExpression;
import com.educatedsolutions.parser.javacc.ASTReal;
import com.educatedsolutions.parser.javacc.ASTStart;
import com.educatedsolutions.parser.javacc.ASTStatement;
import com.educatedsolutions.parser.javacc.ASTUnaryExpression;
import com.educatedsolutions.parser.javacc.ASTVariable;
import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.MathParserVisitor;
import com.educatedsolutions.parser.javacc.SimpleNode;
import com.educatedsolutions.parser.terms.ExponentTerm;
import com.educatedsolutions.parser.terms.NumberTerm;
import com.educatedsolutions.parser.terms.ProductTerm;
import com.educatedsolutions.parser.terms.SubExpressionTerm;
import com.educatedsolutions.parser.terms.SumTerm;
import com.educatedsolutions.parser.terms.Term;
import com.educatedsolutions.parser.terms.VariableTerm;

/**
 * @author wspeirs
 *
 */
public class ASTtoTermsVisitor implements MathParserVisitor {
    
    private static final Logger LOG = LoggerFactory.getLogger(ASTtoTermsVisitor.class);

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.SimpleNode, java.lang.Boolean)
     */
    @Override
    public Term visit(SimpleNode node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting SimpleNode");
        
        throw new MathParserException("Visiting SimpleNode");
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTStart, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTStart node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTStart");

        if(data.size() != 1) {
            throw new MathParserException("Start has more than 1 child");
        }
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTStatement, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTStatement node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTStatement");

        if(data.size() != 1) {
            throw new MathParserException("Statement has more than 1 child");
        }
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTEquality, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTEquality node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTEquality");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTInequality, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTInequality node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTInequality");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTAssignment, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTAssignment node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTAssignment");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTExpression");
        
        if(data.size() != 1) {
            throw new MathParserException("Expression has more than 1 child");
        }
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTAdditiveExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTAdditiveExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTAdditiveExpression");
        
        if(data.size() == 1) {
            return data.get(0);
        } else {
            return new SumTerm(data, node.getImage());   // TODO: This is wrong, fix
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTMultiplicativeExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTMultiplicativeExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTMultiplicativeExpression");
        
        if(data.size() == 1) {
            return data.get(0);
        } else {
            return new ProductTerm(data, node.getImage());
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTPrimaryExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTPrimaryExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTPrimaryExpression");
        
        int dataSize = data.size();
        
        if(dataSize != 1) {
            throw new MathParserException("PrimaryExpression doesn't have 1 child");
        }
        
        if(node.jjtGetChild(0) instanceof ASTExpression) {
            return new SubExpressionTerm(data.get(0));
        } else {
            return data.get(0);
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTBindingVariable, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTBindingVariable node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTBindingVariable");
        
        return null;    // don't need to do anything
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTVariable, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTVariable node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTVariable");
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTConstant, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTConstant node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting SimpleNode");
        
        throw new MathParserException("Not implemented");
        
        //return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTUnaryExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTUnaryExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTNumber");
        
        if(data.size() != 1) {
            throw new MathParserException("Number found without 1 child");
        }

        List<Term> terms = new ArrayList<Term>(2);
        
        terms.add(NumberTerm.NEGATIVE_ONE);
        terms.add(data.get(0)); // variable or number
        
        return new ProductTerm(terms, "*");
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTNumber, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTNumber node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTNumber");
        
        if(data.size() != 1) {
            throw new MathParserException("Number found without 1 child");
        }
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTInteger, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTInteger node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTInteger");

        if(data.size() != 0) {
            throw new MathParserException("Real found with children");
        }
        
        return new NumberTerm(((SimpleNode)node).getImage());
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTReal, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTReal node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ASTReal");
        
        if(data.size() != 0) {
            throw new MathParserException("Real found with children");
        }
        
        return new NumberTerm(((SimpleNode)node).getImage());
    }

    @Override
    public Term visit(ASTExponentiationExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Visiting ExponentialExpression");
        
        if(data.size() == 2) {
            return new ExponentTerm(data.get(0), data.get(1));
        } else {
            return data.get(0);
        }
    }

    @Override
    public Term visit(ASTEquationVariable node, List<Term> data) throws MathParserException {
        return new VariableTerm(((SimpleNode)node).getImage());
    }

}

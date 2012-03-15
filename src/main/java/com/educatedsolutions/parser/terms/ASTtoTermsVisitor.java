/**
 * 
 */
package com.educatedsolutions.parser.terms;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.javacc.ASTAdditiveExpression;
import com.educatedsolutions.parser.javacc.ASTAssignment;
import com.educatedsolutions.parser.javacc.ASTBindingVariable;
import com.educatedsolutions.parser.javacc.ASTConstant;
import com.educatedsolutions.parser.javacc.ASTEquality;
import com.educatedsolutions.parser.javacc.ASTExpression;
import com.educatedsolutions.parser.javacc.ASTFraction;
import com.educatedsolutions.parser.javacc.ASTInequality;
import com.educatedsolutions.parser.javacc.ASTInteger;
import com.educatedsolutions.parser.javacc.ASTMultiplicativeExpression;
import com.educatedsolutions.parser.javacc.ASTNumber;
import com.educatedsolutions.parser.javacc.ASTPolynomialTerm;
import com.educatedsolutions.parser.javacc.ASTPrimaryExpression;
import com.educatedsolutions.parser.javacc.ASTReal;
import com.educatedsolutions.parser.javacc.ASTStart;
import com.educatedsolutions.parser.javacc.ASTStatement;
import com.educatedsolutions.parser.javacc.ASTUnaryExpression;
import com.educatedsolutions.parser.javacc.ASTVariable;
import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.MathParserVisitor;
import com.educatedsolutions.parser.javacc.SimpleNode;

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
        LOG.debug("Vsiting SimpleNode");
        
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTStart, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTStart node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTStart");

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
        LOG.debug("Vsiting ASTStatement");

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
        LOG.debug("Vsiting ASTEquality");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTInequality, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTInequality node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTInequality");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTAssignment, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTAssignment node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTAssignment");
        return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTExpression");
        
        if(data.size() != 1) {
            throw new MathParserException("Expression has more than 1 child");
        }
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTPolynomialTerm, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTPolynomialTerm node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTPolynomialTerm");
        
        int numChildren = node.jjtGetNumChildren();
        
        if(data.size() < 2 || numChildren < 2) {
            throw new MathParserException("Not enough children to make a polynomial");
        }
        
        if(numChildren == 2) {
            return new PolynomialTerm((NumberTerm) data.get(0), ((SimpleNode)node.jjtGetChild(1)).getImage());
        } else {
            return new PolynomialTerm((NumberTerm) data.get(0),
                                      ((SimpleNode)node.jjtGetChild(1)).getImage(),
                                      (NumberTerm) data.get(2));
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTAdditiveExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTAdditiveExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTAdditiveExpression");
        
        if(data.size() == 1) {
            return data.get(0);
        } else {
            return new SumTerm(data);   // TODO: This is wrong, fix
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTMultiplicativeExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTMultiplicativeExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTMultiplicativeExpression");
        
        if(data.size() == 1) {
            return data.get(0);
        } else {
            return new ProductTerm(data);
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTPrimaryExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTPrimaryExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTPrimaryExpression");
        
        int dataSize = data.size();
        
        if(dataSize != 1) {
            throw new MathParserException("PrimaryExpression doesn't have 1 child");
        }
        
        if(node.jjtGetChild(0) instanceof ASTExpression) {
            return new SubExpression(data.get(0));
        } else {
            return data.get(0);
        }
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTBindingVariable, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTBindingVariable node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTBindingVariable");
        
        return null;    // don't need to do anything
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTVariable, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTVariable node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTVariable");
        
        return null;    // don't need to do anything
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTConstant, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTConstant node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting SimpleNode");
        
        throw new MathParserException("Not implemented");
        
        //return null;
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTUnaryExpression, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTUnaryExpression node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTNumber");
        
        if(data.size() != 1) {
            throw new MathParserException("Number found without 1 child");
        }
        
        ((NumberTerm)data.get(0)).negate(); // just negate the number
        
        return data.get(0);
    }

    /* (non-Javadoc)
     * @see com.educatedsolutions.parser.javacc.MathParserVisitor#visit(com.educatedsolutions.parser.javacc.ASTNumber, java.lang.Boolean)
     */
    @Override
    public Term visit(ASTNumber node, List<Term> data) throws MathParserException {
        LOG.debug("Vsiting ASTNumber");
        
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
        LOG.debug("Vsiting ASTInteger");

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
        LOG.debug("Vsiting ASTReal");
        
        if(data.size() != 0) {
            throw new MathParserException("Real found with children");
        }
        
        return new NumberTerm(((SimpleNode)node).getImage());
    }

}

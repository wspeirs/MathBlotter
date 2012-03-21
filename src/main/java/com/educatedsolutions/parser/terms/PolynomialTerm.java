package com.educatedsolutions.parser.terms;

import java.util.List;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

public class PolynomialTerm implements Term {
    
    private PolynomialFunction poly;
    private double[] coefficients;
    private String variable;
    
    public PolynomialTerm(NumberTerm number, String variable) {
        coefficients = new double[2];
        
        coefficients[0] = 0;
        coefficients[1] = number.getNumber().doubleValue();
        
        this.poly = new PolynomialFunction(coefficients);
        
        this.variable = variable.trim();
    }
    
    public PolynomialTerm(NumberTerm number, String variable, NumberTerm exponent) {
        int order = exponent.getNumber().intValueExact() + 1;
        coefficients = new double[order];
        
        for(int i=0; i < order-1; ++i) {
            coefficients[i] = 0;
        }
        
        coefficients[order-1] = number.getNumber().doubleValue();
        
        this.poly = new PolynomialFunction(coefficients);
        
        this.variable = variable;
    }

    @Override
    public String toLatexString() {
        StringBuilder sb = new StringBuilder(coefficients[coefficients.length-1] + "");
        
        for(int i=coefficients.length-2; i > 1; --i) {
            sb.append(variable);
            sb.append("^");
            sb.append(i+1);
            
            if(coefficients[i] >= 0) {
                sb.append("+");
            }
            
            sb.append(coefficients[i]);
        }
        
        sb.append(variable);
        
        if(coefficients[0] >= 0) {
            sb.append("+");
        }
        
        sb.append(coefficients[0]);
        
        return poly.toString();
    }

    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public PolynomialFunction getPoly() {
        return poly;
    }

    public String getVariable() {
        return variable;
    }

}

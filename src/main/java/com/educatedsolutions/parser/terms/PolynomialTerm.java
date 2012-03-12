package com.educatedsolutions.parser.terms;

import org.apache.commons.math.analysis.polynomials.PolynomialFunction;

public class PolynomialTerm implements Term {
    
    private PolynomialFunction poly;
    private String variable;
    
    public PolynomialTerm(NumberTerm number, String variable) {
        double[] coefficients = new double[2];
        
        coefficients[0] = 0;
        coefficients[1] = number.getNumber().doubleValue();
        
        this.poly = new PolynomialFunction(coefficients);
        
        this.variable = variable;
    }
    
    public PolynomialTerm(NumberTerm number, String variable, NumberTerm exponent) {
        int order = exponent.getNumber().intValueExact() + 1;
        double[] coefficients = new double[order];
        
        for(int i=0; i < order-1; ++i) {
            coefficients[i] = 0;
        }
        
        coefficients[order-1] = number.getNumber().doubleValue();
        
        this.poly = new PolynomialFunction(coefficients);
        
        this.variable = variable;
    }

    public String toLatexString() {
        return null;
    }

    public PolynomialFunction getPoly() {
        return poly;
    }

    public String getVariable() {
        return variable;
    }

}

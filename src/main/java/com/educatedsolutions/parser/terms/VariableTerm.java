package com.educatedsolutions.parser.terms;

public class VariableTerm extends ValueTerm implements Term {
    
    private String variable;
    
    public VariableTerm(String variable) {
        this.variable = variable;
    }

    @Override
    public String toLatexString() {
        return super.toLatexString() + variable;
    }

    public String getVarible() {
        return variable;
    }

}

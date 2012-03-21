package com.educatedsolutions.parser.terms;

import java.util.List;

public class VariableTerm extends ValueTerm implements Term {
    
    private String variable;
    
    public VariableTerm(String variable) {
        this.variable = variable;
    }

    @Override
    public String toLatexString() {
        return super.toLatexString() + variable;
    }

    @Override
    public Term accept(TermVisitor visitor, List<Term> children) {
        return visitor.visit(this, children);
    }

    public String getVarible() {
        return variable;
    }

}

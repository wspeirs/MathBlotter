package com.educatedsolutions.parser.terms;

import java.util.List;

public interface Term {

    public String toLatexString();
    
    public Term accept(TermVisitor visitor, List<Term> children);
}

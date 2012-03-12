package com.educatedsolutions.parser;

import java.util.ArrayList;
import java.util.List;

import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.MathParserVisitor;
import com.educatedsolutions.parser.javacc.Node;
import com.educatedsolutions.parser.terms.Term;

public class PostOrderASTWalker {
    
    private MathParserVisitor visitor;
    
    public PostOrderASTWalker(MathParserVisitor visitor) {
        this.visitor = visitor;
    }

    public Term walk(final Node node) throws MathParserException {
        // go through the children in order
        int numChildren = node.jjtGetNumChildren();
        
        List<Term> terms = new ArrayList<Term>(numChildren);
        
        for(int i=0; i < numChildren; ++i) {
            terms.add(walk(node.jjtGetChild(i)));
        }

        return node.jjtAccept(visitor, terms);
    }
}

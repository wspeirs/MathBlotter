package com.educatedsolutions.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.javacc.SimpleNode;
import com.educatedsolutions.parser.terms.Term;

public class TermsTest extends ParserTest {
    private static final Logger LOG = LoggerFactory.getLogger(TermsTest.class);

    private ASTtoTermsVisitor visitor = new ASTtoTermsVisitor();
    private PostOrderASTWalker walker = new PostOrderASTWalker(visitor);

    protected Object runParser(String line) throws Exception {
        SimpleNode node = (SimpleNode) super.runParser(line);

        Term term = walker.walk(node);
        
        if(LOG.isDebugEnabled()) {
            LOG.debug("LATEX: " + term.toLatexString());
        }
        
        return term;
    }
}

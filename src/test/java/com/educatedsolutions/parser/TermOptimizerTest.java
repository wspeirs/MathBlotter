package com.educatedsolutions.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.terms.PostOrderTermWalker;
import com.educatedsolutions.parser.terms.Term;
import com.educatedsolutions.parser.terms.visitors.TermSimplifier;

public class TermOptimizerTest extends TermsTest {
    private static final Logger LOG = LoggerFactory.getLogger(TermOptimizerTest.class);

    private TermSimplifier optimizer = new TermSimplifier();
    private PostOrderTermWalker walker = new PostOrderTermWalker(optimizer);
    
    protected Object runParser(String line) throws Exception {
        Term term = (Term) super.runParser(line);
        
        Term simplifiedTerm = walker.walk(term);
        
        LOG.debug("SIMPLIFIED: {} -> {}", line, simplifiedTerm.toLatexString());
        
        return simplifiedTerm;
    }

}

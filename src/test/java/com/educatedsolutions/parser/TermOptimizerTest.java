package com.educatedsolutions.parser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.javacc.SimpleNode;
import com.educatedsolutions.parser.terms.PostOrderTermWalker;
import com.educatedsolutions.parser.terms.Term;
import com.educatedsolutions.parser.terms.visitors.TermSimplifier;

public class TermOptimizerTest {
    private static final Logger LOG = LoggerFactory.getLogger(TermOptimizerTest.class);

    public static String runOptimizer(Term term) throws Exception {
        TermSimplifier optimizer = new TermSimplifier();
        PostOrderTermWalker walker = new PostOrderTermWalker(optimizer);
        Term simplifiedTerm = walker.walk(term);
        
        return simplifiedTerm.toLatexString();
    }

    protected String runTest(String line) throws Exception {
        SimpleNode node = ParserTest.runParser(line);
        Term term = TermsTest.toTerms(node);
        String optimized = runOptimizer(term);
        
        LOG.debug("SIMPLIFIED: {} -> {}", line, optimized);
        
        return optimized;
    }
    
    @Test public void integerAdditionTest() throws Exception {
        assertEquals("5", runTest("2+3"));
    }
    
    @Test public void integerSubtractionTest() throws Exception {
        assertEquals("-1", runTest("2-3"));
    }
    
    @Test public void integerExponentiationTest() throws Exception {
        assertEquals("8", runTest("2^3"));
    }
    
    @Test public void variableExponentiationTest() throws Exception {
        assertEquals("x^{y}", runTest("x^y"));
    }
    
    @Test public void variableAdditionTest() throws Exception {
        assertEquals("x+y", runTest("x+y"));
    }
    
    @Test public void expressionExponentiationTest() throws Exception {
        assertEquals("(2+-1*4)^{(-1*x--1*y)}", runTest("(2+-4)^(-x--y)"));
    }
    
    @Test public void divisionTest() throws Exception {
        assertEquals("0.575", runTest("2.3/4"));
    }
    
    @Test public void subExpressionMultiplicationTest() throws Exception {
        assertEquals("482.4", runTest("(2*4.5)*(6.7*8)"));
    }
    
    @Test public void subExpressionDivisionTest() throws Exception {
        assertEquals("2.7", runTest("(4/2)/(6/8.1)"));
    }
    
    @Test public void singleTermPolySquared2Test() throws Exception {
        assertEquals("-1*3.4*x^{2}", runTest("-3.4*x^2"));
    }
    
    @Test public void unaryPolyDivisionTest() throws Exception {
        assertEquals("\\frac{-1*x}{-1*x}", runTest("-x/-x"));
    }
    
}

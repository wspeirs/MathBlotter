package com.mathblotter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mathblotter.parser.ASTtoTermsVisitor;
import com.mathblotter.parser.PostOrderASTWalker;
import com.mathblotter.parser.javacc.SimpleNode;
import com.mathblotter.parser.terms.Term;

public class TermsTest {
    private static final Logger LOG = LoggerFactory.getLogger(TermsTest.class);

    public static Term toTerms(SimpleNode node) throws Exception {
        ASTtoTermsVisitor visitor = new ASTtoTermsVisitor();
        PostOrderASTWalker walker = new PostOrderASTWalker(visitor);
        Term term = walker.walk(node);

        return term;
    }

    protected String runTest(String line) throws Exception {
        SimpleNode node = ParserTest.runParser(line);
        Term term = toTerms(node);

        String ret = term.toLatexString();

        LOG.debug("TERMS: {} -> {}", line, ret);

        return ret;
    }

    @Test public void integerExponentiationTest() throws Exception {
        assertEquals("2^{3}", runTest("2^3"));
    }

    @Test public void variableExponentiationTest() throws Exception {
        assertEquals("x^{y}", runTest("x^y"));
    }

    @Test public void variableAdditionTest() throws Exception {
        assertEquals("x+y", runTest("x+y"));
    }

    @Test public void variableUnaryTest() throws Exception {
        assertEquals("-x", runTest("-x"));
    }

    @Test public void integerUnaryTest() throws Exception {
        assertEquals("-2", runTest("-2"));
    }

    @Test public void expressionExponentiationTest() throws Exception {
        assertEquals("(2+-4)^{(-x--y)}", runTest("(2+-4)^(-x--y)"));
    }

    @Test public void divisionTest() throws Exception {
        assertEquals("\\frac{2.3}{4}", runTest("2.3/4"));
    }

    @Test public void subExpressionMultiplicationTest() throws Exception {
        assertEquals("(2*4.5)*(6.7*8)", runTest("(2*4.5)*(6.7*8)"));
    }

    @Test public void subExpressionDivisionTest() throws Exception {
        assertEquals("\\frac{(\\frac{2}{4.5})}{(\\frac{6.7}{8})}", runTest("(2/4.5)/(6.7/8)"));
    }

    @Test public void singleTermPolySquared2Test() throws Exception {
        assertEquals("-3.4*x^{2}", runTest("-3.4*x^2"));
    }

    @Test public void unaryPolyDivisionTest() throws Exception {
        assertEquals("\\frac{-x}{-x}", runTest("-x/-x"));
    }

}

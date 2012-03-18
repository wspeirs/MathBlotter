package com.educatedsolutions.parser;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.educatedsolutions.parser.javacc.MathParser;
import com.educatedsolutions.parser.javacc.ParseException;
import com.educatedsolutions.parser.javacc.SimpleNode;

public class ParserTest {
    
    private MathParser parser = new MathParser(IOUtils.toInputStream(""));

    protected Object runParser(String line) throws ParseException {
        if(!line.endsWith("\n")) {
            line = line + "\n";
        }
        parser.ReInit(IOUtils.toInputStream(line));
        
        SimpleNode ret = parser.Start();
        
        ret.dump("");
        
        return ret;
    }
    
    @Test public void integerTest() throws Exception {
        assertNotNull(runParser("2"));
    }
    
    @Test public void realTest() throws Exception {
        assertNotNull(runParser("2.4"));
    }
    
    @Test public void integerUnaryTest() throws Exception {
        assertNotNull(runParser("-2"));
    }
    
    @Test public void realUnaryTest() throws Exception {
        assertNotNull(runParser("-2.4"));
    }
    
    @Test public void variableUnaryTest() throws Exception {
        assertNotNull(runParser("-x"));
    }
    
    @Test public void integerExponentiationTest() throws Exception {
        assertNotNull(runParser("2^3"));
    }
    
    @Test public void variableExponentiationTest() throws Exception {
        assertNotNull(runParser("x^y"));
    }
    
    @Test public void expressionExponentiationTest() throws Exception {
        assertNotNull(runParser("(2+-4)^(-x--y)"));
    }
    
    @Test public void additionTest() throws Exception {
        assertNotNull(runParser("2.3 + 4"));
    }
    
    @Test public void subtractionTest() throws Exception {
        assertNotNull(runParser("2 - 4.3"));
    }
    
    @Test public void multiplicationTest() throws Exception {
        assertNotNull(runParser("2 * 4.3"));
    }
    
    @Test public void divisionTest() throws Exception {
        assertNotNull(runParser("2.3/4"));
    }
    
    @Test public void unaryAdditionTest() throws Exception {
        assertNotNull(runParser("-2.3 + -4"));
    }
    
    @Test public void unarySubtractionTest() throws Exception {
        assertNotNull(runParser("-2 - -4.3"));
    }
    
    @Test public void unaryMultiplicationTest() throws Exception {
        assertNotNull(runParser("-2 * -4.3"));
    }
    
    @Test public void unaryDivisionTest() throws Exception {
        assertNotNull(runParser("-2.3/-4"));
    }
    
    @Test public void subExpressionAdditionTest() throws Exception {
        assertNotNull(runParser("(2+4.5)+(6.7+8)"));
    }
    
    @Test public void subExpressionSubtractionTest() throws Exception {
        assertNotNull(runParser("(2-4.5)-(6.7-8)"));
    }
    
    @Test public void subExpressionMultiplicationTest() throws Exception {
        assertNotNull(runParser("(2*4.5)*(6.7*8)"));
    }
    
    @Test public void subExpressionDivisionTest() throws Exception {
        assertNotNull(runParser("(2/4.5)/(6.7/8)"));
    }
    
    @Test public void singleTermPoly1Test() throws Exception {
        assertNotNull(runParser("x"));
    }
    
    @Test public void singleTermPoly2Test() throws Exception {
        assertNotNull(runParser("-3.5*x"));
    }
    
    @Test public void singleTermPolySquared1Test() throws Exception {
        assertNotNull(runParser("x^2"));
    }
    
    @Test public void singleTermPolySquared2Test() throws Exception {
        assertNotNull(runParser("-3.4*x^2"));
    }
    
    @Test public void polyAdditionTest() throws Exception {
        assertNotNull(runParser("x + x"));
    }
    
    @Test public void polySubtractionTest() throws Exception {
        assertNotNull(runParser("x - x"));
    }
    
    @Test public void polyMultiplicationTest() throws Exception {
        assertNotNull(runParser("x * x"));
    }
    
    @Test public void polyDivisionTest() throws Exception {
        assertNotNull(runParser("x/x"));
    }
    
    @Test public void unaryPolyAdditionTest() throws Exception {
        assertNotNull(runParser("-x + -x"));
    }
    
    @Test public void unaryPolySubtractionTest() throws Exception {
        assertNotNull(runParser("-x - -x"));
    }
    
    @Test public void unaryPolyMultiplicationTest() throws Exception {
        assertNotNull(runParser("-x * -x"));
    }
    
    @Test public void unaryPolyDivisionTest() throws Exception {
        assertNotNull(runParser("-x/-x"));
    }
    
    @Test public void subExpressionPolyAdditionTest() throws Exception {
        assertNotNull(runParser("(x+y)+(y+x)"));
    }
    
    @Test public void subExpressionPolySubtractionTest() throws Exception {
        assertNotNull(runParser("(x-y)-(y-x)"));
    }
    
    @Test public void subExpressionPolyMultiplicationTest() throws Exception {
        assertNotNull(runParser("(x*y)*(y*x)"));
    }
    
    @Test public void subExpressionPolyDivisionTest() throws Exception {
        assertNotNull(runParser("(x/y)/(x/y)"));
    }
    
/*    @Test public void assignmentTest() throws Exception {
        assertNotNull(runParser("A := 2 + 3"));
    }
    
    @Test (expected=ParseException.class) public void assignmentFailTest() throws Exception {
        assertNotNull(runParser("x := 2 + 3"));
    }
*/    
}

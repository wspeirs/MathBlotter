package com.educatedsolutions.parser;

import static org.junit.Assert.assertNotNull;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.educatedsolutions.parser.javacc.MathParser;
import com.educatedsolutions.parser.javacc.ParseException;
import com.educatedsolutions.parser.javacc.SimpleNode;

public class ParserTest {
    
    private MathParser parser = new MathParser(IOUtils.toInputStream(""));

    SimpleNode runParser(String line) throws ParseException {
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
    
    @Test public void singleTermPoly1Test() throws Exception {
        assertNotNull(runParser("x"));
    }
    
    @Test public void singleTermPoly2Test() throws Exception {
        assertNotNull(runParser("-3.5x"));
    }
    
    @Test public void singleTermPolySquared1Test() throws Exception {
        assertNotNull(runParser("x^2"));
    }
    
    @Test public void singleTermPolySquared2Test() throws Exception {
        assertNotNull(runParser("-3.4x^2"));
    }
    
}

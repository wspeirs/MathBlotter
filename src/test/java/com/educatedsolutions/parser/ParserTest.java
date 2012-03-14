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
        
        return parser.Start();
    }
    
    @Test public void integerTest() throws Exception {
        assertNotNull(runParser("2"));
    }
    
    @Test public void realTest() throws Exception {
        assertNotNull(runParser("2.4"));
    }
    
    @Test public void fractionTest() throws Exception {
        assertNotNull(runParser("2/4"));
    }
    
}

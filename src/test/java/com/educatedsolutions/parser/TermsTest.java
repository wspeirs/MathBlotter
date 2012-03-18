package com.educatedsolutions.parser;

import com.educatedsolutions.gui.TeXUtilities;
import com.educatedsolutions.parser.javacc.ParseException;

public class TermsTest extends ParserTest {
    
    private TeXUtilities utils = new TeXUtilities();

    protected Object runParser(String line) throws ParseException {
        String ret = utils.inputToLatex(line);
        
        return ret;
    }
}

package com.educatedsolutions;

import org.apache.commons.io.IOUtils;

import com.educatedsolutions.parser.javacc.MathParser;
import com.educatedsolutions.parser.javacc.ParseException;
import com.educatedsolutions.parser.javacc.SimpleNode;


public class Main {

    /**
     * @param args
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
        
//        new MainWindow();
        
        MathParser parser = new MathParser(IOUtils.toInputStream("5 * \\pi * (3 + 5) + 7x\n"));
        
        SimpleNode node = parser.Start();
        
        node.dump("");
    }

}

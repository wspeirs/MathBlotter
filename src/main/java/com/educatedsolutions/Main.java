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
        
        MathParser parser = new MathParser(IOUtils.toInputStream("3 + 4\n"));
        
        SimpleNode node = parser.Start();
        
        node.dump("");
    }

}

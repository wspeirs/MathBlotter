package com.educatedsolutions;

import com.educatedsolutions.gui.MainWindow;
import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.ParseException;


public class Main {

    /**
     * @param args
     * @throws ParseException 
     * @throws MathParserException 
     */
    public static void main(String[] args) throws ParseException, MathParserException {
        
        new MainWindow();

/*
        MathParser parser = new MathParser(IOUtils.toInputStream("5/4 + 3.0 / 7.4 * (3 + 4)\n"));
        
        SimpleNode node = parser.Start();
        
        node.dump("");
        
        ASTtoTermsVisitor visitor = new ASTtoTermsVisitor();
        PostOrderASTWalker walker = new PostOrderASTWalker(visitor);
        
        Term t = walker.walk(node);
        
        System.out.println(t.toLatexString());
*/
    }

}

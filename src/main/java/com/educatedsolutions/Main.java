package com.educatedsolutions;

import org.apache.commons.io.IOUtils;

import com.educatedsolutions.parser.PostOrderASTWalker;
import com.educatedsolutions.parser.javacc.MathParser;
import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.ParseException;
import com.educatedsolutions.parser.javacc.SimpleNode;
import com.educatedsolutions.parser.terms.ASTtoTermsVisitor;
import com.educatedsolutions.parser.terms.Term;


public class Main {

    /**
     * @param args
     * @throws ParseException 
     * @throws MathParserException 
     */
    public static void main(String[] args) throws ParseException, MathParserException {
        
//        new MainWindow();
        
        MathParser parser = new MathParser(IOUtils.toInputStream("3x + 2 * 5 + 7 + 5x^3 * 4/5 + 3\n"));
        
        SimpleNode node = parser.Start();
        
        node.dump("");
        
        ASTtoTermsVisitor visitor = new ASTtoTermsVisitor();
        PostOrderASTWalker walker = new PostOrderASTWalker(visitor);
        
        Term t = walker.walk(node);
    }

}

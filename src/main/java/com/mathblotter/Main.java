package com.mathblotter;

import com.mathblotter.gui.MainWindow;
import com.mathblotter.parser.MathParserException;
import com.mathblotter.parser.javacc.ParseException;


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

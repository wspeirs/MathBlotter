package com.mathblotter.parser;

import com.mathblotter.parser.javacc.MathParser;
import com.mathblotter.parser.javacc.SimpleNode;
import com.mathblotter.parser.javacc.Token;


public class MathNode extends SimpleNode {
    public MathNode(int i) {
        super(i);
    }

    public MathNode(MathParser p, int i) {
        super(p,i);
    }

    private String image = "";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImage(Token t) {
        setImage(t.image);
    }

    public void appendImage(String image) {
        this.image = this.image + image;
    }

    public void appendImage(Token t) {
        appendImage(t.image);
    }
}

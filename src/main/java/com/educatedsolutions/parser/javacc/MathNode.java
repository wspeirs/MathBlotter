package com.educatedsolutions.parser.javacc;


public class MathNode {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public void setImage(Token t) {
        this.image = t.image;
    }
}

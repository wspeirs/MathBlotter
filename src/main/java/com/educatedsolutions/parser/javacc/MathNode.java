package com.educatedsolutions.parser.javacc;


public class MathNode {
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

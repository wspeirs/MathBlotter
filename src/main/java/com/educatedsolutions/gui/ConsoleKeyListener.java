package com.educatedsolutions.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleKeyListener implements KeyListener {
    
    private static final Logger LOG = LoggerFactory.getLogger(ConsoleKeyListener.class);

    public static final String PROMPT = "> ";
    
    private final JTextPane pane;
    private final StyledDocument doc;
    private final Style regularStyle;
    
    private TeXUtilities texUtils = new TeXUtilities();
    
    private int iconNum = 0;
    private int startPos;
    private int caretPos;
    
    public ConsoleKeyListener(JTextPane pane, Style regularStyle) {
        this.pane = pane;
        this.doc = pane.getStyledDocument();
        this.regularStyle = regularStyle;
        
        try {
            doc.insertString(0, ConsoleKeyListener.PROMPT, regularStyle);
            pane.setCaretPosition(PROMPT.length());
        } catch (BadLocationException e) {
            LOG.error("Bad location: {}", e.getMessage());
        }

    }

    public void keyPressed(KeyEvent event) {
        startPos = doc.getParagraphElement(pane.getCaretPosition()).getStartOffset() + PROMPT.length();
        caretPos = pane.getCaretPosition();
    }

    public void keyReleased(KeyEvent event) {
        
    }

    public void keyTyped(KeyEvent event) {
        if(event.getKeyChar() == '\n') {
            
            try {
                final String iconName = "icon_" + iconNum;
                final Style iconStyle = doc.addStyle(iconName, regularStyle);
                final String line = doc.getText(startPos, caretPos - startPos);

                LOG.debug("Line: " + startPos + " " + caretPos + " |" + line + "|");

                final Icon texIcon = texUtils.generateTeXIcon(line);

                // set the style for the graphic
                StyleConstants.setIcon(iconStyle, texIcon);
                StyleConstants.setAlignment(iconStyle, StyleConstants.ALIGN_CENTER);
                pane.setParagraphAttributes(iconStyle, true);
                
                // insert the graphic
                doc.insertString(caretPos+1, "\n", iconStyle);

                // reset the style for the next paragraph
                pane.setParagraphAttributes(regularStyle, true);

                // insert our prompt
                doc.insertString(caretPos+2, PROMPT, regularStyle);

            } catch (BadLocationException e) {
                LOG.debug("Bad location exception", e);
            }
            
        }
    }

}

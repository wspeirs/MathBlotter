package com.mathblotter.gui;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.Utilities;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class MainWindow extends JFrame implements WindowListener {

    private static final long serialVersionUID = 1L;
    
    public MainWindow() {
        super("Math");
        
        this.addWindowListener(this);
        
        String math = "x^2 + 3x + 7";

        TeXFormula fomule = new TeXFormula(math);
        TeXIcon ti = fomule.createTeXIcon(TeXConstants.STYLE_DISPLAY, 30);
        BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);    

        JTextPane textPane = new JTextPane();
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
        Style regular = textPane.addStyle("regular", def);
        
        // set the font for the text
        StyleConstants.setFontFamily(regular, "Courier New");
        StyleConstants.setFontSize(regular, 14);
        textPane.setLogicalStyle(regular);
        
        textPane.setEditable(true);
        textPane.addKeyListener(new ConsoleKeyListener(textPane, regular));

        //Put the editor pane in a scroll pane.
        JScrollPane editorScrollPane = new JScrollPane(textPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));

        add(editorScrollPane);
        this.setBounds(getCenteredWindow(600, 550));
        setVisible(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static Rectangle getCenteredWindow(int width, int height) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle rec = ge.getScreenDevices()[0].getDefaultConfiguration().getBounds();
        Rectangle ret = new Rectangle();
        
        ret.x = (rec.width/2) - (width/2);
        ret.y = (rec.height/2) - (height/2);
        ret.width = width;
        ret.height = height;
    
        return ret;
    }
    
    public void windowActivated(WindowEvent arg0) {
    }

    public void windowClosed(WindowEvent arg0) {
    }

    public void windowClosing(WindowEvent arg0) {
        this.dispose();
    }

    public void windowDeactivated(WindowEvent arg0) {
    }

    public void windowDeiconified(WindowEvent arg0) {
    }

    public void windowIconified(WindowEvent arg0) {
    }

    public void windowOpened(WindowEvent arg0) {
    }

}

package com.educatedsolutions.gui;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class TeXUtilities {

    public static Icon generateTeXIcon(final String equation) {
        TeXFormula fomule = new TeXFormula(equation);
        TeXIcon ti = fomule.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
        BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);    

        return ti;
    }
}

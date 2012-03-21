package com.educatedsolutions.gui;

import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.educatedsolutions.parser.ASTtoTermsVisitor;
import com.educatedsolutions.parser.PostOrderASTWalker;
import com.educatedsolutions.parser.javacc.MathParser;
import com.educatedsolutions.parser.javacc.MathParserException;
import com.educatedsolutions.parser.javacc.ParseException;
import com.educatedsolutions.parser.javacc.SimpleNode;
import com.educatedsolutions.parser.terms.Term;

public class TeXUtilities {

    private static final Logger LOG = LoggerFactory.getLogger(TeXUtilities.class);

    private MathParser parser;
    private ASTtoTermsVisitor visitor = new ASTtoTermsVisitor();
    private PostOrderASTWalker walker = new PostOrderASTWalker(visitor);
    
    public Icon generateTeXIcon(final String equation) {
        
        TeXFormula fomule = new TeXFormula(inputToLatex(equation));
        TeXIcon ti = fomule.createTeXIcon(TeXConstants.STYLE_DISPLAY, 18);
        BufferedImage b = new BufferedImage(ti.getIconWidth(), ti.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        ti.paintIcon(new JLabel(), b.getGraphics(), 0, 0);    

        return ti;
    }
    
    public String inputToLatex(String input) {
        if(!input.endsWith("\n")) {
            input = input + "\n";
        }
        
        if(parser == null) {
            parser = new MathParser(IOUtils.toInputStream(input));
        } else {
            parser.ReInit(IOUtils.toInputStream(input));
        }
        
        Term term = null;
        
        try {
            SimpleNode node = parser.Start();

            if(LOG.isDebugEnabled()) {
                node.dump("");
            }
            
            term = walker.walk(node);
            
            LOG.debug("LATEX: " + term.toLatexString());
            
        } catch (ParseException e) {
            LOG.debug("Parser error: {}", e.getMessage(), e);
        } catch (MathParserException e) {
            LOG.debug("Term conversion error: {}", e.getMessage(), e);
        }
        
        return term.toLatexString();
    }
}

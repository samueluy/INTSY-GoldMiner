package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.*;

/** 
    * Represents a StyledLabelUI(Reason why JLabels can be customized)
    *
    * @reference   Walser, P.(2014, May 16). stack overflow. Retrieved 2021, August 27 from https://stackoverflow.com/a/23699047
*/
public class StyledLabelUI extends BasicLabelUI{
    private int x;
    private int y;
    private int x2;
    private int y2;
    /** 
    * Constructs a new StyledLabelUI
    *
    * 
    */
    public StyledLabelUI(int a, int b, int c, int d){
        x = a;
        y = b;
        x2 = c;
        y2 = d;
    }
    /** 
    * This function overrides the installUI method
    *
    * @param c        the JComponent to be customized
    */
    @Override
    public void installUI (JComponent c) {
        super.installUI(c);
        JLabel label = (JLabel) c;
        label.setOpaque(false);
        label.setBorder(new EmptyBorder(x,y,x2,y2));
    }
    /** 
    * This function overrides the paint method
    *
    * @param g       an instance of Graphics
    * @param c       the JComponent to be customized
    */
    @Override
    public void paint (Graphics g, JComponent c) {
        JLabel b = (JLabel) c;
        paintBackground(g, b, 2);
        super.paint(g, c);
    }
    /** 
    * This function paints the background of a JComponent
    *
    * @param g          an instance of Graphics
    * @param c          the JComponent object to be customized
    * @param yOffset    the int value that allows the customization of a JComponent object
    */
    private void paintBackground (Graphics g, JComponent c, int yOffset) {
        Dimension size = c.getSize();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(c.getBackground().darker());
        g.fillRoundRect(0, yOffset, size.width, size.height - yOffset, 10, 10);
        g.setColor(c.getBackground());
        g.fillRoundRect(0, yOffset, size.width, size.height + yOffset - 5, 10, 10);
    }
}
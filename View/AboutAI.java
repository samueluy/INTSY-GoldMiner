package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

//represents an AboutAI class (one shown when AboutGoldMiner is clicked on the MainMenu)
public class AboutAI extends JPanel
{
    private JPanel pnlTextInstructions;
    private JTextArea texts;

    public AboutAI(String strInstructions)
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(15,30,50,30)));
        this.setOpaque(false);
        this.setVisible(true);
        pnlTextInstructions = new JPanel();
        texts = new JTextArea(strInstructions, 30, 50);
        setUpAboutGoldMiner();
    }

    //customizes appearance of texts, scroll bar, and text area
    private void setUpAboutGoldMiner()
    {
        texts.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        texts.setBackground(GUI.SPRING_LEAVES);
        texts.setForeground(Color.white);
        texts.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlTextInstructions.add(new ModernScrollPane(texts));
        pnlTextInstructions.setOpaque(false);
        this.add(pnlTextInstructions);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }
}
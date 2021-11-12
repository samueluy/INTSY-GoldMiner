package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class ChooseLevel extends JPanel{
    private JLabel lblInstructions;
    private JButton btnRandom;
    private JButton btnIntelligent;
    private JPanel pnlChooseLevel;
    private ImageIcon imgRandom = new ImageIcon("Lib/utilities/RandomBtn.png");
    private ImageIcon imgIntelligent = new ImageIcon("Lib/utilities/IntelligentBtn.png");

    public ChooseLevel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(96, 96, 96, 96)));
        this.setOpaque(false);
        this.setVisible(true);
        lblInstructions = new JLabel("<html>What level of intelligent behavior would you like to explore?</html>", SwingConstants.CENTER);
        pnlChooseLevel = new JPanel();
        pnlChooseLevel.setLayout(new FlowLayout());
        pnlChooseLevel.setPreferredSize(new Dimension(480, 480));
        pnlChooseLevel.setOpaque(false);
        pnlChooseLevel.setVisible(true);
        btnRandom = new JButton("Random");
        btnIntelligent = new JButton("Intelligent");
        setUpChooseLevelButtons();
    }

    //getters

    private void setUpChooseLevelButtons(){
        lblInstructions.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 20));
        lblInstructions.setBackground(GUI.PINE_GLADE);
        lblInstructions.setForeground(Color.white);
        lblInstructions.setUI(new StyledLabelUI(25,20,25,20));
        lblInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblInstructions);
        this.add(Box.createRigidArea(new Dimension(0, 96)));
        alterButton(btnRandom, imgRandom);
        pnlChooseLevel.add(Box.createRigidArea(new Dimension(96, 0)));
        alterButton(btnIntelligent, imgIntelligent);
        this.add(pnlChooseLevel);
    }

    private void alterButton(JButton btn, ImageIcon img){
        btn.setIcon(img);
        btn.setBackground(GUI.MALIBU);
        btn.setPreferredSize(new Dimension(288,288));
        btn.setUI(new StyledButtonUI(95,100,95,100));
        btn.setForeground(GUI.MALIBU);
        pnlChooseLevel.add(btn);
    }
}

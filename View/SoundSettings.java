package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class SoundSettings extends JPanel{
    private JLabel lblInstructionsChooseSound;
    private JButton btnTrack1;
    private JButton btnTrack2;
    private JButton btnDefaultTrack;

    public SoundSettings(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(96, 96, 96, 96)));
        this.setOpaque(false);
        this.setVisible(true);

        lblInstructionsChooseSound = new JLabel("<html>Choose which track would you like to use<br/> as the backgroud music during your stay.</html>", SwingConstants.CENTER);
        btnTrack1 = new JButton("Track 1");
        btnTrack2 = new JButton("Track 2");
        btnDefaultTrack = new JButton("Default Track");
        setUpSoundSettings();
    }

    public JButton getBtnTrack1(){
        return btnTrack1;
    }

    public JButton getBtnTrack2(){
        return btnTrack2;
    }

    public JButton getBtnDefaultTrack(){
        return btnDefaultTrack;
    }

    private void setUpSoundSettings(){
        lblInstructionsChooseSound.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        lblInstructionsChooseSound.setBackground(GUI.COPPER);
        lblInstructionsChooseSound.setForeground(Color.white);
        lblInstructionsChooseSound.setUI(new StyledLabelUI(55,50,55,50));
        lblInstructionsChooseSound.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblInstructionsChooseSound);
        this.add(Box.createRigidArea(new Dimension(0, 96)));

        alterBtn(btnDefaultTrack, 10);
        alterBtn(btnTrack1, 10);
        alterBtn(btnTrack2, 96);
    }

    private void alterBtn(JButton btn, int yDimension){
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btn.setBackground(GUI.PINE_GLADE);
        btn.setForeground(GUI.MING);
        btn.setUI(new StyledButtonUI(10,15,10,15));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(btn);
        this.add(Box.createRigidArea(new Dimension(0, yDimension)));
    }
}
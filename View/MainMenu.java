package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class MainMenu extends JPanel{
    private JLabel  lblTitle;
    private ImageIcon imgTitle = new ImageIcon("Lib/utilities/Title.png");
    private ImageIcon imgStartBtn = new ImageIcon("Lib/utilities/StartBtn.png");
    private ImageIcon imgAboutBtn = new ImageIcon("Lib/utilities/AboutBtn.png");
    private ImageIcon imgSoundBtn = new ImageIcon("Lib/utilities/SoundBtn.png");
    private ImageIcon imgExitBtn = new ImageIcon("Lib/utilities/ExitBtn.png");
    private JButton btnStartAI;
    private JButton btnAboutGoldMiner;
    private JButton btnSoundSettings;
    private JButton btnExitAI;

    public MainMenu(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(96, 48, 48, 48)));
        this.setPreferredSize(GUI.SYSTEM_SIZE);
        this.setOpaque(false);
        this.setVisible(true);

        lblTitle = new JLabel(imgTitle);
        btnStartAI = new JButton("Start AI System");
        btnAboutGoldMiner = new JButton("About Gold Miner");
        btnSoundSettings = new JButton("Sound Settings");
        btnExitAI = new JButton("Exit AI System");

        setUpMainMenu();
    }

    public JButton getBtnStartAI(){
        return btnStartAI;
    }

    public JButton getBtnAboutGoldMiner(){
        return btnAboutGoldMiner;
    }

    public JButton getBtnSoundSettings(){
        return btnSoundSettings;
    }

    public JButton getBtnExitAI(){
        return btnExitAI;
    }

    private void setUpMainMenu(){
        lblTitle.setBackground(GUI.TARAWERA);
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblTitle);
        this.add(Box.createRigidArea(new Dimension(0, 96)));
        alterButton(btnStartAI, imgStartBtn);
        alterButton(btnAboutGoldMiner, imgAboutBtn);
        alterButton(btnSoundSettings, imgSoundBtn); 
        alterButton(btnExitAI, imgExitBtn);
    }

    private void alterButton(JButton btn, ImageIcon img){
        btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 2));
        btn.setIcon(img);
        btn.setBackground(GUI.SPRING_LEAVES);
        btn.setForeground(GUI.SPRING_LEAVES);
        btn.setUI(new StyledButtonUI(10,5,10,5));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(btn);
        this.add(Box.createRigidArea(new Dimension(0, 30)));
    }
}

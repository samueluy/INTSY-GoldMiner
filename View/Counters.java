package View;

import java.awt.*;
import javax.swing.*;

public class Counters{
    private JFrame frame;
    private JLabel lblLvlOfIntelligence;
    private JLabel lblScans;
    private JLabel lblMoves;
    private JLabel lblRotates;
    private JButton btnStart;

    public Counters(String lvl){
        frame = new JFrame(lvl);
        frame.setSize(new Dimension(288, 480));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(GUI.PICKLED_BLUEWOOD);
        lblScans = new JLabel("Scans: ");
        lblMoves = new JLabel("Moves: ");
        lblRotates = new JLabel("Rotates: ");
        btnStart = new JButton("Start Mining");
        setUpCounters();
        frame.setVisible(false);
    }

    public JButton getBtnStartMining(){
        return btnStart;
    }

    private void setUpCounters(){
        frame.add(Box.createRigidArea(new Dimension(0, 30)));
        alterLbl(lblMoves);
        alterLbl(lblRotates);
        alterLbl(lblScans);
        frame.add(Box.createRigidArea(new Dimension(0, 30)));
        btnStart.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        btnStart.setBackground(GUI.MING);
        btnStart.setForeground(Color.white);
        btnStart.setUI(new StyledButtonUI(15,20,15,20));
        btnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(btnStart);
    }

    //should be disabled later
    private void alterLbl(JLabel lbl){
        lbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        lbl.setBackground(GUI.GOLD_SAND);
        lbl.setForeground(GUI.PAARL);
        lbl.setUI(new StyledLabelUI(30,35,30,35));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(lbl);
        frame.add(Box.createRigidArea(new Dimension(0, 15)));
    }

    public void updateLvl(String str){
        frame.setTitle(str);
        frame.setVisible(true);
    }
    //update method

    /*public static void main(String[] args){
        Counters trial = new Counters("Random Behavior");
    }*/
}

package View;

import java.awt.*;
import javax.swing.*;

public class Counters{
    private JFrame frame;
    private JLabel lblScans;
    private JLabel lblMoves;
    private JLabel lblRotates;
    private JLabel lblPathCost;
    private JButton btnStart;
    private JButton btnGo;
    private JButton btnQuit;

    public Counters(String lvl){
        frame = new JFrame(lvl);
        frame.setSize(new Dimension(288, 768));
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(GUI.PICKLED_BLUEWOOD);
        lblMoves = new JLabel("Moves: ");
        lblScans = new JLabel("Scans: ");
        lblRotates = new JLabel("Rotates: ");
        lblPathCost = new JLabel("Path Cost = ");
        btnStart = new JButton("Start Mining");
        btnGo = new JButton("Go");
        //btnGo.setEnabled(false);
        btnQuit = new JButton("Quit");
        setUpCounters();
        frame.setVisible(false);
    }

    public JButton getBtnStartMining(){
        return btnStart;
    }

    public JButton getGoBtn(){
        return btnGo;
    }

    public JButton getQuitBtn(){
        return btnQuit;
    }

    private void setUpCounters(){
        frame.add(Box.createRigidArea(new Dimension(0, 30)));
        alterLbl(lblMoves);
        alterLbl(lblRotates);
        alterLbl(lblScans);
        alterLbl(lblPathCost);
        frame.add(Box.createRigidArea(new Dimension(0, 30)));
        alterButton(btnStart);
        alterButton(btnGo);
        alterButton(btnQuit);
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
        updateCounters(0, 0, 0, 0);
    }

    public void updateCounters(int nMoves, int nScans, int nRotates, int nPathCost)
    {
        lblMoves.setText("Moves: " + nMoves);
        lblScans.setText("Scans: " + nScans);
        lblRotates.setText("Rotates: " + nRotates);
        lblPathCost.setText("Path Cost = " + nPathCost);
    }

    //update method-test what will happen to frame after game finishes
    public void closeCounterFrame()
    {
        frame.setVisible(false);
    }

    private void alterButton(JButton btn)
    {
        btn.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        btn.setBackground(GUI.MING);
        btn.setForeground(Color.white);
        btn.setUI(new StyledButtonUI(15,20,15,20));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(btn);
        frame.add(Box.createRigidArea(new Dimension(0, 15)));
    }
}
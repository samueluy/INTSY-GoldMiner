package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class NumberOfCells extends JPanel{
    private JLabel lblInstructions;
    private JTextField txtfldNumberOfCells;
    private JButton btnSubmit;

    public NumberOfCells(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(96, 96, 96, 96)));
        this.setOpaque(false);
        this.setVisible(true);
        lblInstructions = new JLabel("<html>Enter the number of tiles you want to work with (8-64).</html>", SwingConstants.CENTER);
        txtfldNumberOfCells = new JTextField();
        btnSubmit = new JButton("Submit");
        setUpNumberOfCells();
    }

    //getters

    private void setUpNumberOfCells(){
        lblInstructions.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 50));
        lblInstructions.setBackground(GUI.PINE_GLADE);
        lblInstructions.setForeground(Color.white);
        lblInstructions.setUI(new StyledLabelUI(55,50,55,50));
        lblInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblInstructions);
        this.add(Box.createRigidArea(new Dimension(0, 96)));

        txtfldNumberOfCells.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        txtfldNumberOfCells.setBackground(GUI.SPRING_LEAVES);
        txtfldNumberOfCells.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(txtfldNumberOfCells);
        this.add(Box.createRigidArea(new Dimension(0, 192)));

        
        btnSubmit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        btnSubmit.setBackground(GUI.MALIBU);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setUI(new StyledButtonUI(20,100,20,100));
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(btnSubmit);
    }
}

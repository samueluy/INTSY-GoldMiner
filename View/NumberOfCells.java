package View;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

//represents a NumberofCells class (includes the textfield where user will enter desired Model.Board.MAX_DIMENSION)
public class NumberOfCells extends JPanel
{
    private JLabel lblInstructions;
    private JTextField txtfldNumberOfCells;
    private JButton btnSubmit;

    public NumberOfCells()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(new Insets(96, 96, 96, 96)));
        this.setOpaque(false);
        this.setVisible(true);
        lblInstructions = new JLabel("<html>Enter the number of tiles you want to work with (8-64).</html>", SwingConstants.CENTER);
        txtfldNumberOfCells = new JTextField();
        btnSubmit = new JButton("Submit");
        setUpNumberOfCells();
    }

    //returns textfield after user clicks the Submit button when entering desired Model.Board.MAX_DIMENSION
    public JTextField getTxtFldNumOfCells()
    {
        return txtfldNumberOfCells;
    }

    //returns the Submit button so an ActionListener cna be added to it
    public JButton getBtnSubmit()
    {
        return btnSubmit;
    }

    //customizes instructions label, textfield, and button that can be seen when user is entering
    //desired Model.Board.MAX_DIMENSION
    private void setUpNumberOfCells()
    {
        lblInstructions.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 40));
        lblInstructions.setBackground(GUI.PINE_GLADE);
        lblInstructions.setForeground(Color.white);
        lblInstructions.setUI(new StyledLabelUI(25,20,25,20));
        lblInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(lblInstructions);
        this.add(Box.createRigidArea(new Dimension(0, 96)));

        txtfldNumberOfCells.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        txtfldNumberOfCells.setBackground(GUI.SPRING_LEAVES);
        txtfldNumberOfCells.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(txtfldNumberOfCells);
        this.add(Box.createRigidArea(new Dimension(0, 192)));

        
        btnSubmit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        btnSubmit.setBackground(GUI.PINE_GLADE);
        btnSubmit.setForeground(GUI.MING);
        btnSubmit.setUI(new StyledButtonUI(10,15,10,15));
        btnSubmit.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(btnSubmit);
    }
}
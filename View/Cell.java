package View;

import java.awt.*;
import javax.swing.*;

public class Cell extends JPanel{
    private int size = 48;

    public Cell(Point temp, int n){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(size, size));
        this.setBackground(GUI.TALLOW);
        this.setVisible(true);
        assignImage(temp, n);
    }

    private void assignImage(Point temp, int n){
        String strTemp = "PlainCell";
        if((int)temp.getX() == 4 && (int)temp.getY() == 4){
            strTemp = "PlainCellPit"; 
        }
        else if((int)temp.getX() == 5 && (int)temp.getY() == 5){
            strTemp = "PlainCellGold";
        }
        else if((int)temp.getX() == 6 && (int)temp.getY() == 8){
            strTemp = "PlainCellBeacon";
        }
        else if((int)temp.getX() == 2){
            strTemp = "ScannedCell";
        }
        if(n < 12) strTemp +="19.png";
        else if (n < 10) strTemp += "9.png";
        else strTemp += "20.png";

        JLabel lblTemp = new JLabel(new ImageIcon("Lib/Cells/"+strTemp));
        lblTemp.setSize(new Dimension(size, size));
        this.add(lblTemp);
        this.setVisible(true);
    }
}

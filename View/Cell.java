package View;

import java.awt.*;
import javax.swing.*;

import Model.ModelMainMenu;

public class Cell extends JPanel
{
    private JLabel lblTemp;

    public Cell(Point temp, ModelMainMenu modelMainMenu){
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(GUI.CELL_DIMENSION, GUI.CELL_DIMENSION));
        this.setBackground(GUI.TALLOW);
        this.setVisible(true);
        lblTemp = new JLabel();
        this.add(lblTemp);
        assignImage(temp, modelMainMenu);
    }

    private void assignImage(Point temp, ModelMainMenu modelMainMenu){
        int nCells = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION();
        String strTemp = "PlainCell";
        if(modelMainMenu.getGame().getGameBoard().getCell(temp).isPit())
        {
            strTemp = "PlainCellPit"; 
        }
        else if(modelMainMenu.getGame().getGameBoard().getCell(temp).isBeacon() != -1){
            strTemp = "PlainCellBeacon";
        }
        else if((modelMainMenu.getGame().getGameBoard().getCell(temp).isGold())){
            strTemp = "PlainCellGold";
        }
        if(nCells < 12) strTemp +="19.png";
        else if (nCells < 10) strTemp += "9.png";
        else strTemp += "20.png";

        lblTemp.setIcon(new ImageIcon("Lib/Cells/"+strTemp));
        lblTemp.setSize(new Dimension(GUI.CELL_DIMENSION, GUI.CELL_DIMENSION));
        this.setVisible(true);
    }

}
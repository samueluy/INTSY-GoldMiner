package View;

import javax.swing.*;
import java.awt.*; 

import Model.ModelMainMenu;

public class BoardView extends JPanel
{
    private Point locTempLocation;

    public BoardView(ModelMainMenu modelMainMenu){
        int nCells = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION();
        int dimension = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION() * GUI.CELL_DIMENSION;
        this.setLayout(new GridLayout(nCells, nCells, 3, 3));
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setBackground(GUI.TALLOW);
        this.setVisible(true);
        locTempLocation = new Point();

        for(int i = 0; i < nCells; i++){
            for(int j = 0; j < nCells; j++){
                locTempLocation.setLocation(i, j);
                Cell tempTile = new Cell(locTempLocation, modelMainMenu);
                this.add(tempTile);
            }
        }
    }
}
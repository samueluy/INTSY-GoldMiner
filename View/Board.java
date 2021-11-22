package View;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel{
    private Point locTempLocation;

    public Board(int numberOfCells){
        int dimension = numberOfCells * GUI.CELL_DIMENSION;
        this.setLayout(new GridLayout(numberOfCells, numberOfCells, 3, 3));
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setBackground(GUI.TALLOW);
        this.setVisible(true);
        locTempLocation = new Point();

        for(int i = 0; i < numberOfCells; i++){
            for(int j = 0; j < numberOfCells; j++){
                locTempLocation.setLocation(i, j);
                Cell tempTile = new Cell(locTempLocation, numberOfCells);
                this.add(tempTile);
            }
        }
    }
}
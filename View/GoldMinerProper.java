package View;

import javax.swing.*;
import java.awt.*;

public class GoldMinerProper extends JLayeredPane{
    private Board board;
    //private Miner miner;

    public GoldMinerProper(int numberOfCells){
        int dimension = numberOfCells * 48;
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(dimension, dimension));
        this.setBounds(0, 0, dimension, dimension);
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        board = new Board(numberOfCells);

        board.setBounds(48, 48, dimension, dimension);
        this.add(board);
        //this.add(board, Integer.valueOf(0), 0);
    }

    //getters
}

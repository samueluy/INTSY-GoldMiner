package View;

import javax.swing.*;
import java.awt.*;

public class GoldMinerProper extends JPanel{
    private Board board;
    private BoardMiner boardMiner;

    public GoldMinerProper(int numberOfCells){
        int dimension = numberOfCells * 48;
        this.setLayout(new OverlayLayout(this));
        this.setSize(new Dimension(dimension, dimension));
        this.setBounds(0, 0, dimension, dimension);
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        board = new Board(numberOfCells);
        boardMiner = new BoardMiner(numberOfCells);

        //board.setBounds(0, 0, dimension, dimension);
        this.add(boardMiner);
        this.add(board);
        
        //boardMiner.setBounds(0, 0, dimension, dimension);
        
    }

    //getters
    public Board getBoard(){
        return board;
    }

    public BoardMiner getBoardMiner(){
        return boardMiner;
    }
}

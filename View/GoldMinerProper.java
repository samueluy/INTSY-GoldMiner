package View;

import javax.swing.*;
import java.awt.*;

import Model.*;

public class GoldMinerProper extends JPanel
{
    private BoardView board;
    private BoardMiner boardMiner;

    public GoldMinerProper(ModelMainMenu modelMainMenu)
    {
        int dimension = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION() * GUI.CELL_DIMENSION;
        this.setLayout(new OverlayLayout(this));
        this.setSize(new Dimension(dimension, dimension));
        this.setBounds(0, 0, dimension, dimension);
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        board = new BoardView(modelMainMenu);
        boardMiner = new BoardMiner(modelMainMenu);

        //board.setBounds(0, 0, dimension, dimension);
        this.add(boardMiner);
        this.add(board);
        
        //boardMiner.setBounds(0, 0, dimension, dimension);
    }

    public BoardView getBoard()
    {
        return board;
    }

    public BoardMiner getBoardMiner(){
        return boardMiner;
    }

    public void updateGoldMiner(Board gameBoard)
    {
        boardMiner.updateMiner(gameBoard);
    }

    public void updateBoardAndBoardMiner(ModelMainMenu modelMainMenu)
    {
        board = null;
        boardMiner = null;
        board = new BoardView(modelMainMenu);
        boardMiner = new BoardMiner(modelMainMenu);
        this.add(boardMiner);
        this.add(board);
    }
}
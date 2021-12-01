package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Model.*;

public class BoardMiner extends JPanel
{
    private Miner miner[][];
    ImageIcon imgRight = new ImageIcon("Lib/Agent/MinerRIGHT9.png");
    ImageIcon imgLeft = new ImageIcon("Lib/Agent/MinerLEFT9.png");
    ImageIcon imgUp = new ImageIcon("Lib/Agent/MinerUP9.png");
    ImageIcon imgDown = new ImageIcon("Lib/Agent/MinerDOWN9.png");
    ImageIcon imgMiner = imgRight;
    int nCells;

    public BoardMiner(ModelMainMenu modelMainMenu)
    {
        nCells = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION();
        int dimension = modelMainMenu.getGame().getGameBoard().getMAX_DIMENSION() * GUI.CELL_DIMENSION;
        this.setLayout(new GridLayout(nCells, nCells, 3, 3));
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setOpaque(false);
        this.setVisible(true);
        miner = new Miner[nCells][nCells];
        
        for(int i = 0; i < nCells; i++)
        {
            for(int j = 0; j < nCells; j++)
            { 
                miner[i][j] = new Miner();
                this.add(miner[i][j]);
            }
        }
        miner[0][0].setLblMinerImage(imgMiner);
    }

    public void updateMiner(Board gameBoard)
    {   
        for(int i = 0; i < nCells; i++)
        {
            for(int j = 0; j < nCells; j++)
            { 
                miner[i][j].setLblMinerVisible(false);
            }
        }

        switch(gameBoard.getMinerDirection()) 
        {
            case "RIGHT":
                imgMiner = imgRight;
                break;
            case "LEFT":
                imgMiner = imgLeft;
                break;
            case "UP":
                imgMiner = imgUp;
                break;
            case "DOWN":
                imgMiner = imgDown;
                break;
        }
        miner[(int)gameBoard.getMinerCoordinate().getX()][(int)gameBoard.getMinerCoordinate().getY()].setLblMinerImage(imgMiner);
    }
}
package View;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BoardMiner extends JPanel{
    private Miner miner[][];
    ImageIcon sample = new ImageIcon("Lib/Agent/MinerU9.png");

    public BoardMiner(int numberOfCells){
        int dimension = numberOfCells * GUI.CELL_DIMENSION;
        this.setLayout(new GridLayout(numberOfCells, numberOfCells, 3, 3));
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setOpaque(false);
        this.setVisible(true);
        miner = new Miner[numberOfCells][numberOfCells];
        
        for(int i = 0; i < numberOfCells; i++){
            for(int j = 0; j < numberOfCells; j++){
                miner[i][j] = new Miner();
                this.add(miner[i][j]);
            }
        }

        miner[0][0].setLblMinerImage(sample);
    }

    public Miner getMiner(int i, int j){
        return miner[i][j];
    }
    //reset
}

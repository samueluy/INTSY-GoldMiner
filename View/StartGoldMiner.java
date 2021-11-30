package View;

import javax.swing.*;
import java.awt.*;

import Model.*;

public class StartGoldMiner extends JPanel
{
    private GoldMinerProper systemProper;

    public StartGoldMiner(ModelMainMenu modelMainMenu){
        this.setSize(new Dimension(3120, 3120));
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        systemProper = new GoldMinerProper(modelMainMenu);
        this.add(systemProper, BorderLayout.CENTER);
        
    }

    public void updateGoldMinerProper(Board gameBoard)
    {
        systemProper.updateGoldMiner(gameBoard);
    }

    public GoldMinerProper getGoldMinerProper()
    {
        return systemProper;
    }
}
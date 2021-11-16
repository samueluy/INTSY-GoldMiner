package View;

import javax.swing.*;
import java.awt.*;

public class StartGoldMiner extends JPanel{
    private GoldMinerProper systemProper;
    //private NorthDisplay systemProperNorthDisplay;

    public StartGoldMiner(int numberOfCells){
        this.setSize(new Dimension(3120, 3120));
        this.setLayout(new BorderLayout());
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        systemProper = new GoldMinerProper(numberOfCells);
        this.add(systemProper, BorderLayout.CENTER);
        
    }

    //getter
    //update method
}

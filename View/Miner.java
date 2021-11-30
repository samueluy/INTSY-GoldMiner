package View;

import javax.swing.*;
import java.awt.*;

public class Miner extends JPanel
{ 
    private JLabel lblMiner;

    public Miner()
    {
        lblMiner = new JLabel();
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(GUI.CELL_DIMENSION, GUI.CELL_DIMENSION));
        this.setOpaque(false);
        this.setVisible(true);
        this.add(lblMiner);
    }

    public JLabel getLblMiner()
    {
        return lblMiner;
    }

    public void setLblMinerImage(ImageIcon newImage)
    {
        this.lblMiner.setIcon(newImage);
        this.lblMiner.setVisible(true);
    }

    public void setLblMinerVisible(boolean bVisible)
    {
        this.lblMiner.setVisible(bVisible);
    }
}

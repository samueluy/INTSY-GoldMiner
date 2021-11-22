package View;

import javax.swing.*;
import java.awt.*;

public class Miner extends JPanel{
    private JLabel lblMiner;

    public Miner(){
        lblMiner = new JLabel();
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(48, 48));
        this.setOpaque(false);
        this.setVisible(true);
        this.add(lblMiner);
    }

    public JLabel getLblMiner(){
        return lblMiner;
    }

    public void setLblMinerImage(ImageIcon newImage){
        this.lblMiner.setIcon(newImage);
    }

    public void setLblMinerVisible(boolean bVisible){
        this.lblMiner.setVisible(bVisible);
    }
}

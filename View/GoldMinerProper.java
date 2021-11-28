package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GoldMinerProper extends JPanel implements ActionListener{
    private Board board;
    private BoardMiner boardMiner;
    Image sample = new ImageIcon("Lib/Agent/MinerR9.png").getImage();
    Timer timer;
    int x = 0;
    int y = 0;
    int xVelocity = 1;
	int yVelocity = 1;

    public GoldMinerProper(int numberOfCells){
        int dimension = numberOfCells * 48;
        this.setLayout(new OverlayLayout(this));
        this.setSize(new Dimension(dimension, dimension));
        this.setBounds(0, 0, dimension, dimension);
        this.setBackground(GUI.PICKLED_BLUEWOOD);
        this.setOpaque(false);
        this.setVisible(true);
        board = new Board(numberOfCells);
        //boardMiner = new BoardMiner(numberOfCells);

        //board.setBounds(0, 0, dimension, dimension);
        this.add(board);
        
        //boardMiner.setBounds(0, 0, dimension, dimension);
        timer = new Timer(10, this);
		timer.start();
    }

    //getters
    public Board getBoard(){
        return board;
    }

    public BoardMiner getBoardMiner(){
        return boardMiner;
    }

    public void paint(Graphics g) {
		
		super.paint(g); // paint background
		
		Graphics2D g2D = (Graphics2D) g;
		
		//g2D.drawImage(backgroundImage, 0, 0, null);
		g2D.drawImage(sample, x, y, null);
        g2D.rotate(Math.toRadians(45));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(x >= 192 - sample.getWidth(null) || x < 0){
			xVelocity = xVelocity * -1;
		}
		x = x + xVelocity;
		
		/*if(y >= 960 - sample.getHeight(null) || y < 0){
			yVelocity = yVelocity * -1;
		}*/
		//y = y + yVelocity;
		repaint();
	}
}

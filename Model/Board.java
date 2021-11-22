package Model;
import java.util.Random;
import java.awt.Point;

class Board
{
    private Cell[][] arrCells; // BOARD
    private static int MAX_DIMENSION = 8;

    /** Constructs the board class
     * 
     * @param nDimension int MAX dimension the coard will have
     */
    public Board (int nDimension)
    {
        MAX_DIMENSION = nDimension;
        arrCells = new Cell[MAX_DIMENSION][MAX_DIMENSION];
        for(int i=0; i<MAX_DIMENSION; i++){
            for(int y=0; y<MAX_DIMENSION; y++){
                arrCells[i][y] = new Cell();
            }
        }
        setMiner();
        setBeacon(setGold());
        setPit();

    }

    /**
     * Sets the location of the miner in the (0,0) coordinates
     */
    private void setMiner ()
    {
        arrCells[0][0].setMiner();
    }

    /**
     * Set the pit location randomly
     */
    private void setPit()
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        // Loop until the right count of pits is place in the board
        for (int i=0; i< Math.round(MAX_DIMENSION * 0.25); i++)
        {
            // Loop until valid place to place PIT
            do
            {
                //generate random values from 0-24
                pCoordinate.x = rRand.nextInt(nUpperbound); 
                pCoordinate.y = rRand.nextInt(nUpperbound);
                if (   arrCells[pCoordinate.x][pCoordinate.y].isFreeTerrain() 
                    &&  !arrCells[pCoordinate.x][pCoordinate.y].isMiner())  //If the miner is not in the cell
                {
                    arrCells[pCoordinate.x][pCoordinate.y].setPit();
                    bValid = true; // STOP the loop
                }
            }while(!bValid);
        }
    }

    /**
     * Set the Gold location randomly
     * @return Point where the Gold location is located. Possible coordinate is from 0-MAX DIMENSION Col and Row
     */
    private Point setGold()
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        // Loop until valid coordinates to place GOLD
        do
        {
            //generate random values from 0-24
            pCoordinate.x = rRand.nextInt(nUpperbound); 
            pCoordinate.y = rRand.nextInt(nUpperbound);
            if (   arrCells[pCoordinate.x][pCoordinate.y].isFreeTerrain() 
                &&  !arrCells[pCoordinate.x][pCoordinate.y].isMiner())  //If the miner is not in the cell
            {
                arrCells[pCoordinate.x][pCoordinate.y].setGold();
                bValid = true; // STOP the loop
            }
        }while(!bValid);

        return pCoordinate;
    }


    /**
     * Set the beacon randomly
     * 
     * @param pGoldCoordinate Point coordinates where the gold is placed
     */
    private void setBeacon(Point pGoldCoordinate)
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        // Loop until the right count of BEACONS is place in the board
        for (int i=0; i< Math.round(MAX_DIMENSION * 0.1); i++)
        {
            // Loop until valid coordinates to place BEACON
            do
            {
                //generate random values from 0-24
                pCoordinate.x = rRand.nextInt(nUpperbound); 
                pCoordinate.y = rRand.nextInt(nUpperbound);
                if (   arrCells[pCoordinate.x][pCoordinate.y].isFreeTerrain() 
                    &&  !arrCells[pCoordinate.x][pCoordinate.y].isMiner())  //If the miner is not in the cell
                {
                    arrCells[pCoordinate.x][pCoordinate.y].setBeacon(pGoldCoordinate);
                    bValid = true; // STOP the loop
                }
            }while(!bValid);
    
        }
    }
    /**
     * Gets the MAX_DIMENSION of the board 
     * @return
     */
    public int getMAX_DIMENSION ()
    {
        return MAX_DIMENSION;
    }

    // To delete. For testing
    //public static void main(String[] args) {
    //    Board board = new Board(8);
    //    int max = board.getMAX_DIMENSION();
    //    System.out.println(max);
    //}
}

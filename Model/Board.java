package Model;
import java.util.Random;
import java.awt.Point;

class Board
{
    private Cell[][] arrCells; // BOARD
    private static int MAX_DIMENSION = 8;

    /**
     * 
     * @param nDimension 
     */
    public Board (int nDimension)
    {
        MAX_DIMENSION = nDimension;
        arrCells = new Cell[MAX_DIMENSION][MAX_DIMENSION];
        setMiner();
        setBeacon(setGold());
        setPit();

    }

    private void setMiner ()
    {
        arrCells[0][0].setMiner();
    }

    private void setPit()
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        
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

    private Point setGold()
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        
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

    private void setBeacon(Point pGoldCoordinate)
    {
        boolean bValid = false;
        Random rRand = new Random(); //instance of random class
        int nUpperbound = MAX_DIMENSION;
        Point pCoordinate = new Point();
        
        
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

    public int getMAX_DIMENSION ()
    {
        return MAX_DIMENSION;
    }
}
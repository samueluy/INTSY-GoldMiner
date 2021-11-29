package Model;
import java.util.Random;
import java.awt.Point;

class Board
{
    private Cell[][] arrCells; // BOARD
    private static int MAX_DIMENSION = 8;
    private static Point pMinerCurrCoordinate; //Miner Coordinate Tracker
    private String minerDirection;

    /** Constructs the board class
     *
     * @param nDimension int MAX dimension the coard will have
     */
    public Board (int nDimension)
    {
        MAX_DIMENSION = nDimension;
        pMinerCurrCoordinate = new Point();
        arrCells = new Cell[MAX_DIMENSION][MAX_DIMENSION];
        for(int i=0; i<MAX_DIMENSION; i++){
            for(int y=0; y<MAX_DIMENSION; y++){
                arrCells[i][y] = new Cell();
            }
        }
        minerDirection = "RIGHT";

        setMiner();
        setBeacon(setGold());
        setPit();

    }

    /**
     * Sets the location of the miner in the (0,0) coordinates
     */
    private void setMiner ()
    {
        pMinerCurrCoordinate.x = 0;
        pMinerCurrCoordinate.y = 0;
        arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner();
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
        System.out.println("Gold: "+pCoordinate.x + " " + pCoordinate.y);
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
                arrCells[pCoordinate.x][pCoordinate.y].setBeacon(pMinerCurrCoordinate, pGoldCoordinate);
                bValid = true; // STOP the loop
            }
        }while(!bValid);

    }
    }
    /**
     * Gets the MAX_DIMENSION of the board
     * @return int
     */
    public int getMAX_DIMENSION ()
    {
        return MAX_DIMENSION;
    }

    public void display()
    {
        for(int i = 0; i<MAX_DIMENSION;i++)
        {
            for(int j = 0; j<MAX_DIMENSION;j++)
                System.out.print(arrCells[i][j]+" ");
            System.out.println();
        }
    }

    /*
    * This method checks the table if the exact cell
        contains a specific element.
    * @param x - x-Coordinate
    * @param y - y-Coordinate
    * @param toFind - String to check.
    * */
    public boolean cellContains(int x,int y, String toFind)
    {
        return arrCells[x][y].getStrSymbol().contains(toFind);
    }

    /*Finds it in arrCell, returns Point.
     * This Method checks the board for a specific element.
     * If the element is found, return the Point coordinate.
     * Else, returns a coordinate with -1 as its x-Coordinate.
     *
     * @param toFind - String to find.
     * */
    public Point boardFind(String toFind)
    {
        Point coord = new Point();
        coord.x = -1;
        for(int i = 0; i<MAX_DIMENSION;i++)
            for (int j = 0; j < MAX_DIMENSION; j++)
                if (arrCells[i][j].getStrSymbol().contains(toFind)) {
                    coord.x = i;
                    coord.y = j;
                    break;
                }
        return coord;
    }

    //Problem: 1. The Miner Object in Cell.
    /*
     * Given the coordinates and String, This method updates
     * the position of an object on the table.
     *
     * @param x - x-Coordinate
     * @param y - y-Coordinate
     * @param toUpdate - String to replace.
     *
     * */
    public void updatePos(int x, int y,String toUpdate)
    {
        //For Miner
        arrCells[x][y].setStrSymbol(toUpdate);
    }

    public void setpMinerCurrCoordinate(Point update)
    {
        pMinerCurrCoordinate = update;
    }
    /*
     * This method checks the table
     * if the Agent is currently in the pit.
     * @return true if current coordinate of player is pit.
     * */
    public boolean isPit()
    {
        for(int i = 0; i<MAX_DIMENSION;i++)
            for (int j = 0; j < MAX_DIMENSION; j++)
                if (arrCells[i][j].getStrSymbol().contains("A")) {
                    if(arrCells[i][j].isPit())
                        return true;
                }
        return false;
    }
    /*
     * This method checks the table
     * if the Agent is currently on the Beacon.
     *
     * */
    public boolean isBeacon()
    {
        for(int i = 0; i<MAX_DIMENSION;i++)
            for (int j = 0; j < MAX_DIMENSION; j++)
                if (arrCells[i][j].getStrSymbol().contains("A")) {
                    if(arrCells[i][j].cellBeacon())
                        return true;
                }
        return false;
    }

    /*
     * Given the Coordiantes, This method checks
     * if the current cell is a beacon. If it is,
     * return the helpful hint. Else, return -1.
     *
     * @param x - x-Coordinate
     * @param y - y-Coordinate
     * */
    public int getHint(int x,int y)
    {
        return arrCells[x][y].isBeacon();
    }

    /**
     * Scans the Row OR COLUMN based on the location of the miner and direction where the miner is facing
     * @return String:  P if it scanned pit tile,
     *                  B if it scanned BEACON,
     *                  G if it scanned GOLD,
     *                  Otherwise, None
     */
    public String scan ()
    {
        String strReturn = "None";
        switch(minerDirection)
        {
            // SCANS RIGHT
            case "RIGHT":
                for (int i  = pMinerCurrCoordinate.y; i < MAX_DIMENSION; i++)
                {
                    // Checks if the tile is a SPECIAL Tile
                    if(!arrCells[pMinerCurrCoordinate.x][i].getStrSymbol().contains("A") && !arrCells[pMinerCurrCoordinate.x][i].getStrSymbol().contains("*")) {
                        strReturn = arrCells[pMinerCurrCoordinate.x][i].getStrSymbol();
                        break;
                    }
                }
                break;
            // SCANS LEFT
            case "LEFT":
                for (int i = pMinerCurrCoordinate.y; i >= 0; i--)
                {
                    if(!arrCells[pMinerCurrCoordinate.x][i].getStrSymbol().contains("A") && !arrCells[pMinerCurrCoordinate.x][i].getStrSymbol().contains("*")) {
                        strReturn = arrCells[pMinerCurrCoordinate.x][i].getStrSymbol();
                        break;
                    }
                }
                break;
            // SCANS UPWARD
            case "UP":
                for (int i = pMinerCurrCoordinate.x; i >= 0; i--)
                {
                    if(!arrCells[i][pMinerCurrCoordinate.y].getStrSymbol().contains("A") && !arrCells[i][pMinerCurrCoordinate.y].getStrSymbol().contains("*")) {
                        strReturn = arrCells[i][pMinerCurrCoordinate.y].getStrSymbol();
                        break;
                    }
                }
                break;
            case "DOWN":
                for (int i = pMinerCurrCoordinate.x; i < MAX_DIMENSION; i++)
                {
                    if(!arrCells[i][pMinerCurrCoordinate.y].getStrSymbol().contains("A") && !arrCells[i][pMinerCurrCoordinate.y].getStrSymbol().contains("*")) {
                        strReturn = arrCells[i][pMinerCurrCoordinate.y].getStrSymbol();
                        break;
                    }
                }
                break;
        }
        return strReturn;
    }
    /**
     * Rotates the direction of the Miner Clockwise.
     */
    public void rotate(){
        switch(minerDirection){
            case "RIGHT":
                minerDirection = "DOWN";
                break;
            case "DOWN":
                minerDirection = "LEFT";
                break;
            case "LEFT":
                minerDirection = "UP";
                break;
            case "UP":
                minerDirection = "RIGHT";
                break;
        }
    }


    public String getMinerDirection() {return minerDirection;}
}
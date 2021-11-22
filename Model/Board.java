package Model;
import java.util.Random;
import java.awt.Point;

class Board
{
    private Cell[][] arrCells; // BOARD
    private static int MAX_DIMENSION = 8;
    private static Point pMinerCurrCoordinate; //Miner Coordinate Tracker

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

    /**
     * Executes the wanted action to the MAIN BOARD
     * @param charAction action to be done. M == Move, R == Rotate, S == Scan
     * @return String:  M,<number> if moved in beacon Tile, 
     *                  M,PIT if moved in pit tile, 
     *                  M,GOLD if moved in gold tile, 
     * 
     *                  S,BEACON if scanned a beacon tile
     *                  S,PIT if scanned a pit tile
     *                  S,GOLD if scanned a gold tile
     * 
     *                  otherwise NULL
     */
    private String executeAction (char charAction)
    {
        String strReturn = new String();
        // If valid action
        // Check if correct parameters
        if(isValidAction(charAction, arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y]))
        {
            switch(charAction)
            {
                case 'M':
                    Miner tempMiner = arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner();
                    arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeMiner(); // Removes miner from intial cell
                    switch(tempMiner.getDirection())
                    {
                        case "RIGHT":
                            pMinerCurrCoordinate.x = getMinerCoordinate().x+1;
                            break;
                        case "LEFT":
                            pMinerCurrCoordinate.x = getMinerCoordinate().x-1;
                            break;
                        case "UP":
                            pMinerCurrCoordinate.y = getMinerCoordinate().y-1;
                            break;
                        case "DOWN":
                            pMinerCurrCoordinate.y = getMinerCoordinate().y+1;
                            break;
                    }
                    // Place new miner
                    arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner(tempMiner);

                    // Check if BEACON
                    if (arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].isBeacon())
                    {
                        // Convert int to String
                        strReturn = "M," + arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getBeacon();
                    }
                    // Check if PIT
                    else if (arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].isPit())
                    {
                        strReturn = "M,PIT";
                    }
                    // Check if GOLD
                    else if (arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].isGold())
                    {
                        strReturn = "M,GOLD";
                    }
                    break;
                case 'S':
                    strReturn = scan();
                    break;
                case 'R':
                    rotate();
                    break;
            }
        }

        return strReturn;
    }

    /**
     * Scans the COLUMN OR COLUMN base on the location of the miner and direction where the miner is facing
     * @return String:  PIT if it scanned pit tile,
     *                  BEACON if it scanned BEACON,
     *                  GOLD if it scanned GOLD,
     *                  Otherwise, NULL
     */
    private String scan ()
    {   
        String strReturn = new String(); 
        switch(arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().getDirection())
        {
            // SCANS RIGHT
            case "RIGHT":
                for (int i = pMinerCurrCoordinate.x + 1; i < MAX_DIMENSION; i++)
                {
                    strReturn = getTileName(arrCells[i][pMinerCurrCoordinate.y]);
                    // Checks if the tile is a SPECIAL Tile 
                    if (strReturn!= null)
                    {
                        // END FOR-LOOP
                        i = MAX_DIMENSION;
                    }
                }
                break;
            // SCANS LEFT
            case "LEFT":
                for (int i = pMinerCurrCoordinate.x - 1; i > 0; i--)
                {
                    strReturn = getTileName(arrCells[i][pMinerCurrCoordinate.y]);
                    // Checks if the tile is a SPECIAL Tile 
                    if (strReturn!= null)
                    {
                        // END FOR-LOOP
                        i = 0;
                    }
                }
                break;
            // SCANS UPWARD
            case "UP":
                for (int i = pMinerCurrCoordinate.y - 1; i > 0; i--)
                {
                    strReturn = getTileName(arrCells[pMinerCurrCoordinate.x][i]);
                    // Checks if the tile is a SPECIAL Tile 
                    if (strReturn!= null)
                    {
                        // END FOR-LOOP
                        i = 0;
                    }
                }
                break;
            case "DOWN":
                for (int i = pMinerCurrCoordinate.y + 1; i < MAX_DIMENSION; i++)
                {
                    strReturn = getTileName(arrCells[pMinerCurrCoordinate.x][i]);
                    // Checks if the tile is a SPECIAL Tile 
                    if (strReturn!= null)
                    {
                        // END FOR-LOOP
                        i = MAX_DIMENSION;
                    }
                }
                break;
        }

        return strReturn;
    }

    /**
     * Gets the String value of what tile kind of tile is placed in the cell
     * @param cCell Cell to be checked
     * @return String:  BEACON if cell is set to be beacon
     *                  PIT if cell is set to be pit
     *                  GOLD if cell is set to be gold
     */
    private String getTileName(Cell cCell)
    {
        if(cCell.isBeacon() != 0)
        {
            return "BEACON";
        }
        else if (cCell.isPit())
        {
            return "PIT";
        }
        else if (cCell.isGold())
        {
            return "GOLD";
        }
        else
        {
            return null;
        }
    } 

    /**
     * Rotates the direction of the Miner
     */
    private void rotate ()
    {
        arrCells[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().rotate();
    }

    /**
     * Checks if the inputted Action is valid action from the current state
     * @param charAction Action to be done (M == Move, R == Rotate, S == Scan)
     * @param cMinerCurrCell The cell where Miner is currently IN
     * @return TRUE if the action is valid, otherwise FALSE
     */
    private boolean isValidAction (char charAction, Cell cMinerCurrCell)
    {
        boolean bValidAction = true;
        // No Miner in the Curr Cell
        if(cMinerCurrCell.getMiner() == null)
        {
            bValidAction = false;
        }
        // Check if the action is move
        else if (charAction == 'M')
        {
            switch(cMinerCurrCell.getMiner().getDirection())
            {

                case "RIGHT":
                    bValidAction = getMinerCoordinate().x-1 < MAX_DIMENSION;
                    break;
                case "LEFT":
                    bValidAction = getMinerCoordinate().x+1 < MAX_DIMENSION;
                    break;
                case "UP":
                    bValidAction = getMinerCoordinate().y-1 < MAX_DIMENSION;
                    break;
                case "DOWN":
                    bValidAction = getMinerCoordinate().y+1 < MAX_DIMENSION;
                    break;
            }
        }
        else if (   charAction != 'R' 
                ||  charAction != 'S')
        {
            bValidAction = false;
        }

        return bValidAction;
    }

    /**
     * Gets the coordinates of the cell where Miner is currently in
     * @return Point: Coordinates of the cell where Miner is currently in
     */
    private Point getMinerCoordinate ()
    {
        return pMinerCurrCoordinate;
    }

}

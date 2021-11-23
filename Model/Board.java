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
        for(int i=0; i<MAX_DIMENSION; i++){
            for(int y=0; y<MAX_DIMENSION; y++){
                arrCells[i][y] = new Cell();
            }
        }
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
        System.out.println("Pit: "+pCoordinate.x + " " + pCoordinate.y);

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
        System.out.println("Gold: "+pCoordinate.x + " " + pCoordinate.y);
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

    /*
     * This method checks the table
     * if the Agent is currently in the pit.
     *
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

    // To delete. For testing
    public static void main(String[] args) {
        Board board = new Board(8);
        int max = board.getMAX_DIMENSION();
        System.out.println(max);
    }
}
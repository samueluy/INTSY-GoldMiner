package Model;

import java.awt.Point;

public class HeuristicBoard implements Cloneable{
    private HeuristicCell[][] arrHeuristicCell;
    private int nMaxDimension;
    private Point pMinerCurrCoordinate;
    private int nActionHeuristic;

    public HeuristicBoard (int nMaxDimension)
    {
        this.nMaxDimension = nMaxDimension;
        arrHeuristicCell =  new HeuristicCell[this.nMaxDimension][this.nMaxDimension];
        for (int i = 0; i< nMaxDimension; i++)
        {
            for (int j=0; j<nMaxDimension;j++)
            {
                arrHeuristicCell[i][j] = new HeuristicCell();
            }
        }
    }

    /**
     * For Deep Copy
     * @param arrHeuristicCell
     * @param nMaxDimension
     * @param pMinerCurrCoordinate
     * @param nActionHeuristic
     */
    public HeuristicBoard(HeuristicBoard hbHeuristicBoard)
    {
        this.nMaxDimension = hbHeuristicBoard.getMaxDimension();
        this.pMinerCurrCoordinate = hbHeuristicBoard.getMinerCurrCoordinate();
        this.nActionHeuristic = hbHeuristicBoard.getAccumulatedActionHeuristic();

        arrHeuristicCell =  new HeuristicCell[this.nMaxDimension][this.nMaxDimension];
        for (int i = 0; i< this.nMaxDimension; i++)
        {
            for (int j=0; j<this.nMaxDimension;j++)
            {
                this.arrHeuristicCell[i][j] = new HeuristicCell();
                this.arrHeuristicCell[i][j] = hbHeuristicBoard.getArrCell()[i][j];
            }
        }
    }

    public HeuristicCell[][] getArrCell ()
    {
        return this.arrHeuristicCell;
    }

    public int getMaxDimension ()
    {
        return this.nMaxDimension;
    }

    public Point getMinerCurrCoordinate ()
    {
        return this.pMinerCurrCoordinate;
    }

    public int getAccumulatedActionHeuristic ()
    {
        return this.nActionHeuristic;
    }


    public void tryAction (char cAction)
    {
        switch(cAction)
        {
            case 'M':
                this.nActionHeuristic = heuristicMove();
                break;
            case 'R':
                heuristicRotate();
                break;
            case 'S':
                this.nActionHeuristic = heuristicScan();
                break;
            default:
                System.out.println("ERROR: tryAction");
                break;
        }
    }

    private int  heuristicMove ()
    {
        int nAccumulatedHeuristic = 0;
        Miner tempMiner = arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner();
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeMiner(); // Removes miner from intial cell
        switch(tempMiner.getDirection())
        {
            case "RIGHT":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x+1;
                break;
            case "LEFT":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x-1;
                break;
            case "UP":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y-1;
                break;
            case "DOWN":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y+1;
                break;
        }

        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner(tempMiner); // Place new miner

        nAccumulatedHeuristic = arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getTotalWeight(); // Get Total Weight of the Heuristic Cell
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeAllWeight(); // Removes the weight of the heuristic Cell
        return nAccumulatedHeuristic;
    }

    private void heuristicRotate ()
    {
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().rotate();
    }

    private int heuristicScan ()
    {
        int nAccumulatedHeuristic = 0;
        switch(arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().getDirection())
        {
            // SCANS RIGHT
            case "RIGHT":
                for (int i = pMinerCurrCoordinate.x + 1; i < nMaxDimension; i++)
                {
                    nAccumulatedHeuristic = arrHeuristicCell[i][pMinerCurrCoordinate.y].getTotalWeight();
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].removeScanWeight();
                }
                break;
            // SCANS LEFT
            case "LEFT":
                for (int i = pMinerCurrCoordinate.x - 1; i > 0; i--)
                {
                    nAccumulatedHeuristic = arrHeuristicCell[i][pMinerCurrCoordinate.y].getTotalWeight();
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].removeScanWeight();
                }
                break;
            // SCANS UPWARD
            case "UP":
                for (int i = pMinerCurrCoordinate.y - 1; i > 0; i--)
                {
                    nAccumulatedHeuristic = arrHeuristicCell[pMinerCurrCoordinate.x][i].getTotalWeight();
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].removeScanWeight();
                    
                }
                break;
            case "DOWN":
                for (int i = pMinerCurrCoordinate.y + 1; i < nMaxDimension; i++)
                {
                    nAccumulatedHeuristic = arrHeuristicCell[pMinerCurrCoordinate.x][i].getTotalWeight();
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].removeScanWeight();
                }
                break;
        }

        return nAccumulatedHeuristic;
    }

    public HeuristicCell setHeuristicCell (int nRow, int nCol)
    {
        if(nRow < 0 || nRow > nMaxDimension || nCol < 0 || nCol > nMaxDimension)
        {
            return null;
        }
        return arrHeuristicCell[nRow][nCol];
    }

    @Override
    public Object clone() 
    {
        HeuristicBoard hbNewHeuristicBoard = null;
        hbNewHeuristicBoard = new HeuristicBoard(this);
        
        return hbNewHeuristicBoard;
    }
}

package Model;

import java.awt.Point;

public class HeuristicBoard implements Cloneable{
    private HeuristicCell[][] arrHeuristicCell;
    private int nMaxDimension;
    private Point pMinerCurrCoordinate;
    private double nActionHeuristic;

    public HeuristicBoard (int nMaxDimension)
    {
        pMinerCurrCoordinate = new Point();
        nActionHeuristic = 0;

        this.nMaxDimension = nMaxDimension;
        arrHeuristicCell =  new HeuristicCell[this.nMaxDimension][this.nMaxDimension];
        for (int i = 0; i< nMaxDimension; i++)
        {
            for (int j=0; j<nMaxDimension;j++)
            {
                arrHeuristicCell[i][j] = new HeuristicCell();
            }
        }
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner();
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeAllWeight();

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
        this.pMinerCurrCoordinate = (Point) hbHeuristicBoard.getMinerCurrCoordinate().clone();
        this.nActionHeuristic = 0;

        arrHeuristicCell = null;
        arrHeuristicCell =  new HeuristicCell[this.nMaxDimension][this.nMaxDimension];
        for (int i = 0; i< this.nMaxDimension; i++)
        {
            for (int j=0; j<this.nMaxDimension;j++)
            {
                this.arrHeuristicCell[i][j] = null;
                this.arrHeuristicCell[i][j] = new HeuristicCell();
                if(hbHeuristicBoard.getArrCell()[i][j].getMiner() != null)
                {
                    this.arrHeuristicCell[i][j].setMiner((Miner) hbHeuristicBoard.getArrCell()[i][j].getMiner().clone());
                }
                this.arrHeuristicCell[i][j].setScanWeight(hbHeuristicBoard.getArrCell()[i][j].getScanWeight());
                this.arrHeuristicCell[i][j].setMoveWeight(hbHeuristicBoard.getArrCell()[i][j].getMoveWeight());


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

    public double getAccumulatedActionHeuristic ()
    {
        return this.nActionHeuristic;
    }

    // DEBUG
    public String display ()
    {
        String strReturn = new String();
        for (int i = 0; i< nMaxDimension; i++)
        {
            for(int j=0;j<nMaxDimension;j++)
            {
                strReturn += arrHeuristicCell[i][j].getMoveWeight() + "+"+arrHeuristicCell[i][j].getScanWeight() + " | ";
            }
            strReturn += "\n";
        }

        return strReturn;
    }

    public void tryAction (char cAction)
    {
        
        switch(cAction)
        {
            case 'M':
                this.nActionHeuristic += heuristicMove();
                break;
            case 'R':
                heuristicRotate();
                this.nActionHeuristic++; // 1 Heuristic to not be stucked in negative tile
                break;
            case 'S':
                this.nActionHeuristic += heuristicScan();
                break;
            default:
                System.out.println("ERROR: tryAction");
                break;
        }
    }

    private double  heuristicMove ()
    {
        double nAccumulatedHeuristic = 0;
        Miner tempMiner = (Miner) arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().clone();
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeMiner(); // Removes miner from intial cell
        switch(tempMiner.getDirection())
        {
            case "DOWN":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x+1;
                break;
            case "UP":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x-1;
                break;
            case "LEFT":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y-1;
                break;
            case "RIGHT":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y+1;
                break;
        }

        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner(tempMiner); // Place new miner

        nAccumulatedHeuristic = arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMoveWeight(); // Get Total Weight of the Heuristic Cell
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeAllWeight(); // Removes the weight of the heuristic Cell
        return nAccumulatedHeuristic;
    }

    private void heuristicRotate ()
    {
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().rotate();
    }

    private double heuristicScan ()
    {
        double nAccumulatedHeuristic = 0;
        switch(arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().getDirection())
        {
            // SCANS DOWNWARD
            case "DOWN":
                for (int i = pMinerCurrCoordinate.x + 1; i < nMaxDimension; i++)
                {
                    nAccumulatedHeuristic += arrHeuristicCell[i][pMinerCurrCoordinate.y].getScanWeight();
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].removeAllWeight();
                }
                break;
            // SCANS UPWARD
            case "UP":
                for (int i = pMinerCurrCoordinate.x - 1; i >= 0; i--)
                {
                    nAccumulatedHeuristic += arrHeuristicCell[i][pMinerCurrCoordinate.y].getScanWeight();
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].removeAllWeight();
                }
                break;
            // SCANS LEFTWARD
            case "LEFT":
                for (int i = pMinerCurrCoordinate.y - 1; i >= 0; i--)
                {
                    nAccumulatedHeuristic += arrHeuristicCell[pMinerCurrCoordinate.x][i].getScanWeight();
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].removeAllWeight();
                    
                }
                break;
            case "RIGHT":
                for (int i = pMinerCurrCoordinate.y + 1; i < nMaxDimension; i++)
                {
                    nAccumulatedHeuristic += arrHeuristicCell[pMinerCurrCoordinate.x][i].getScanWeight();
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].removeAllWeight();
                }
                break;
        }

        return nAccumulatedHeuristic;
    }

    public HeuristicCell getHeuristicCell (int nRow, int nCol)
    {
        if(nRow < 0 || nRow > nMaxDimension || nCol < 0 || nCol > nMaxDimension)
        {
            return null;
        }
        return arrHeuristicCell[nRow][nCol];
    }

    public void updateHeuristicBoard(String strActionOutput) 
    {
        String strTokens[] = strActionOutput.split(",");

        switch(strTokens[0])
        {
            case "M":
                moveUpdate(strTokens[1]);
                break;
            case "R":
                rotateUpdate();
                break;
            case "S":
                scanUpdate(strTokens[1]);
                break;
            default:
                System.out.println("ERROR: updateHeuristicBoard");
                break;
        }
    }

    private void scanLineUpdate (double nNewMoveWeight,double nNewScanWeight )
    {
        switch(arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().getDirection())
        {
            // SCANS DOWN
            case "DOWN":
                for (int i = pMinerCurrCoordinate.x + 1; i < nMaxDimension; i++)
                {
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setScanWeight(nNewScanWeight);
                }
                break;
            // SCANS UP
            case "UP":
                for (int i = pMinerCurrCoordinate.x - 1; i >= 0; i--)
                {
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setScanWeight(nNewScanWeight);
                }
                break;
            // SCANS LEFT
            case "LEFT":
                for (int i = pMinerCurrCoordinate.y - 1; i >= 0; i--)
                {
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setScanWeight(nNewScanWeight);
                    
                }
                break;
            case "RIGHT":
                for (int i = pMinerCurrCoordinate.y + 1; i < nMaxDimension; i++)
                {
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setScanWeight(nNewScanWeight);
                }
                break;
        }
    }

    private void moveToBeaconUpdate (int nGoldDistance)
    {
        for(int i=0; i< nMaxDimension; i++)
        {
            for(int j=0; j< nMaxDimension; j++)
            {
                // Return the scanned beacon line to original state
                // 10 scanned beacon state
                if(arrHeuristicCell[i][j].getMoveWeight() == 10)
                {
                    
                    arrHeuristicCell[i][j].setMoveWeight(1); // Move State Original Value  =1
                    arrHeuristicCell[i][j].setScanWeight(5); // Scan Weight ORGINAL VALUE = 5
                }
                if(nGoldDistance == Math.abs(pMinerCurrCoordinate.x - i) + Math.abs(pMinerCurrCoordinate.y - j))
                {

                    // To prevent adding weight to already scanned tile
                    if(arrHeuristicCell[i][j].getScanWeight() <= 0)
                    {
                        // If negative, LET IT BE DANGEROUS TILE
                    }
                    else
                    {
                        arrHeuristicCell[i][j].setScanWeight(2000);
                    }

                    // To prevent setting new weight to already decided tile( to be a Pit Track )
                    if(arrHeuristicCell[i][j].getMoveWeight() <= 0)
                    {
                        arrHeuristicCell[i][j].removeMoveWeight();
                    }
                    else
                    {
                        arrHeuristicCell[i][j].setMoveWeight(1000);
                    }
                }
                // remove Weghts of the rest, discourage going to still unknown heuristic tile
                else
                {
                    //if(arrHeuristicCell[i][j].getScanWeight() > 0)
                    //{
                    //    arrHeuristicCell[i][j].removeScanWeight();
                    //}
                    //if(arrHeuristicCell[i][j].getMoveWeight() > 0)
                    //{
                    //    arrHeuristicCell[i][j].removeMoveWeight();
                    //}
                }
                

                
            }
        }
    }
    private void scanUpdate (String strScanInfo)
    {
        if(strScanInfo.equals("GOLD"))
        {
            scanLineUpdate(5000,0);
        }
        else if (strScanInfo.equals("PIT"))
        {
            scanLineUpdate(-10,(double)(1.0/nMaxDimension));
        }
        else if (strScanInfo.equals("BEACON"))
        {
            scanLineUpdate(10,0);
        }
        else
        {
            // No New Information Scan
            scanLineUpdate(0, 0);
        }
    }

    private void moveUpdate (String strMoveInfo)
    {
        Miner tempMiner = arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner();
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeMiner(); // Removes miner from intial cell
        switch(tempMiner.getDirection())
        {
            case "DOWN":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x+1;
                break;
            case "UP":
                pMinerCurrCoordinate.x = pMinerCurrCoordinate.x-1;
                break;
            case "LEFT":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y-1;
                break;
            case "RIGHT":
                pMinerCurrCoordinate.y = pMinerCurrCoordinate.y+1;
                break;
        }
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].setMiner(tempMiner); // Place new miner
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeAllWeight(); // REmove All weights, bec. already discoverd

        // Check if Beacon Tile
        if(isNumeric(strMoveInfo) && Integer.parseInt(strMoveInfo) != -1)
        {
            System.out.println("strMoveInfo "+ strMoveInfo);
            moveToBeaconUpdate(Integer.parseInt(strMoveInfo));
        }
    }
    private static boolean isNumeric(String string) {
        int intValue;
            
            
        if(string == null || string.equals("")) {
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
    
    private void rotateUpdate ()
    {
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().rotate();
    }

    @Override
    public Object clone() 
    {
        HeuristicBoard hbNewHeuristicBoard = null;
        hbNewHeuristicBoard = new HeuristicBoard(this);
        
        return hbNewHeuristicBoard;
    }
}

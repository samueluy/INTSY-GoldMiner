package Model;

import java.awt.Point;

public class HeuristicBoard implements Cloneable{
    private HeuristicCell[][] arrHeuristicCell;
    private int nMaxDimension;
    private Point pMinerCurrCoordinate;
    private double nActionHeuristic;

    /**
     * Constructs the HeuristicBoard 
     * @param int nMaxDimension -MaxDimension to be created in arrHeuristicCell
     */
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
     * For Deep Copy, Constructs HeuristicBoard based on the parameter
     *
     * @param HeuristicBoard hbHeuristicBoard HeuristicBoard to be Hard Copied
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

    /**
     * Used method to execute Action in the HeuristicBoard
     * @param char cAction Action to be executed
     */
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

    /**
     * Method to do heuristic move for A* Search
     * @return double accumulated heuristic from the move action
     */
    private double  heuristicMove ()
    {
        double nAccumulatedHeuristic = 0;
        Miner tempMiner = (Miner) arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().clone(); // Clones the Miner from the Main HeuristicBoard to avoid obstructing the main Miner
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].removeMiner(); // Removes miner from intial cell
        
        // Key is the direction of the tempMiner
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

    /**
     * Rotates the Miner in arrCell using its current Miner coordinate in the heuristicBoard
     */
    private void heuristicRotate ()
    {
        arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().rotate();
    }

    /**
     * Scans the HeuristicBoard by accumulating the scanWieght then after removes all the weights
     * @return double the accumulated heuristic score from the scan move 
     */
    private double heuristicScan ()
    {
        double nAccumulatedHeuristic = 0;
        
        // Key is the direction of the Miner
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

    /**
     * Gets the Heuristic Cell from arrHeuristicCell
     * @param int nCol to access the 1st dimension of the arrHeurisCell NOTE: arrHeuristicCell[1st][2nd]
     * @param int nRow to access the 2nd dimension of the arrHeurisCell NOTE: arrHeuristicCell[1st][2nd]
     * @return HeuristicCell the arrHeuristicCell[nCol][nRow]
     */
    public HeuristicCell getHeuristicCell (int nCol, int nRow)
    {
        if(nRow < 0 || nRow > nMaxDimension || nCol < 0 || nCol > nMaxDimension)
        {
            return null;
        }
        return arrHeuristicCell[nCol][nRow];
    }

    /**
     * Updates the MAIN Heuristic Board with the actual action used in the Board Game
     * @param String strActionOutput    M, No new info Move
     *                                  M,<number> if moved in beacon Tile, 
     *                                  M,PIT if moved in pit tile, 
     *                                  M,GOLD if moved in gold tile, 
     * 
     *                                  S, No new info Scan
     *                                  S,BEACON if scanned a beacon tile
     *                                  S,PIT if scanned a pit tile
     *                                  S,GOLD if scanned a gold tile
     * 
     *                                  R, Rotate
     * 
     *                                  otherwise NULL
     */
    public void updateHeuristicBoard(String strActionOutput) 
    {
        // Splits the String
        // Example M,GOLD => strToken[0] = "M", strToken[1] = "GOLD"
        String strTokens[] = strActionOutput.split(",");

        // Key is the Action, Example "M" = Move, "S" = Scan, "R" = Rotate
        switch(strTokens[0])
        {
            case "M":
                moveUpdate(strTokens[1]); 
                break;
            case "R":
                rotateUpdate(); // Didn't need strToken[1] since it NO new information will be attained by rotating
                break;
            case "S":
                scanUpdate(strTokens[1]);
                break;
            default:
                System.out.println("ERROR: updateHeuristicBoard");
                break;
        }
    }

    /**
     * Update the MAIN HeuristicBoard base on the scan move with new Move and Scan weight values.
     * @param double nNewMoveWeight new weight move
     * @param double nNewScanWeight new weight scan
     */
    private void scanLineUpdate (double nNewMoveWeight,double nNewScanWeight )
    {
        
        switch(arrHeuristicCell[pMinerCurrCoordinate.x][pMinerCurrCoordinate.y].getMiner().getDirection())
        {
            // UPDATE DONE SCANS DOWN
            case "DOWN":
                for (int i = pMinerCurrCoordinate.x + 1; i < nMaxDimension; i++)
                {
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setScanWeight(nNewScanWeight);
                }
                break;
            // UPDATE DONE SCANS UP
            case "UP":
                for (int i = pMinerCurrCoordinate.x - 1; i >= 0; i--)
                {
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[i][pMinerCurrCoordinate.y].setScanWeight(nNewScanWeight);
                }
                break;
            // UPDATE DONE SCANS LEFT
            case "LEFT":
                for (int i = pMinerCurrCoordinate.y - 1; i >= 0; i--)
                {
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setScanWeight(nNewScanWeight);
                    
                }
                break;
            // UPDATE DONE SCANS RIGHT
            case "RIGHT":
                for (int i = pMinerCurrCoordinate.y + 1; i < nMaxDimension; i++)
                {
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setMoveWeight(nNewMoveWeight);
                    arrHeuristicCell[pMinerCurrCoordinate.x][i].setScanWeight(nNewScanWeight);
                }
                break;
        }
    }

    /**
     * If the action move done in the main Board landed on a beacon
     * @param int nGoldDistance Manhattan Distance from Gold Tile from the Beacon Tile being landed
     */
    private void moveToBeaconUpdate (int nGoldDistance)
    {
        // COLUMN LOOP
        for(int i=0; i< nMaxDimension; i++)
        {
            // ROW LOOP
            for(int j=0; j< nMaxDimension; j++)
            {
                // Return other scanned beacon line to original state
                // 10 = move heuristic weight, when scanned beacon state
                if(arrHeuristicCell[i][j].getMoveWeight() == 10)
                {
                    arrHeuristicCell[i][j].setMoveWeight(1); // Move State Original Value  =1
                    arrHeuristicCell[i][j].setScanWeight(5); // Scan Weight ORGINAL VALUE = 5
                }
                if(nGoldDistance == Math.abs(pMinerCurrCoordinate.x - i) + Math.abs(pMinerCurrCoordinate.y - j))
                {

                    // ScanWeight = 2000 is greater than MoveWeight = 1000 to encourage the smart agent to SCAN first before MOVING, to prevent falling into a PIT
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
            }
        }
    }
    
    
    /**
     * Update the HeuristicBoard when a scan action is done in the GAME BOARD
     * @param String strScanInfo GOLD, PIT, BEACON, else will be treated as no new info scan
     */
    private void scanUpdate (String strScanInfo)
    {
        if(strScanInfo.equals("GOLD"))
        {
            scanLineUpdate(5000,0); // 5000 Move Weight = Highest Weight to receive. To prioritize going to Gold Tile
                                    // 0 Scan Weight = to discourage scanning since heuristic tells GOLD is already ahead
        }
        else if (strScanInfo.equals("PIT"))
        {
            scanLineUpdate(-10,(double)(1.0/nMaxDimension));    // -10 Move Weight = to discourage falling into a PIT
                                                                // 1.0/nMaxDimension = we need to encourafe scan weight since heuristic is still not sure if the next tile is PIP TILE
                                                                //                   = 0 < scan_weight < 1 : since IF 0 may never discover beacon or gold behind the PIT
                                                                //                   =                       since IF 1 will always execute the scan action first
        }
        else if (strScanInfo.equals("BEACON"))
        {
            scanLineUpdate(10,0); // 10 move weight = to encourage moving forward to beacon tile
                                  // 0 scan weight = to discourage scanning since heauristic already tells beacon is ahead
        }
        else
        {
            // No New Information Scan
            scanLineUpdate(0, 0); // 0 scan and move weight = to discourage moving or scanning to already discovered tile
                                  // Way to remember already discovered(scanned or been moved into) tiles
        }
    }

    /**
     * Update the main heuristicBoard to accommodate done action to the MAIN BOARD GAME
     * @param String strMoveInfo    M,<number> if moved in beacon Tile, 
     *                              M,PIT if moved in pit tile, 
     *                              M,GOLD if moved in gold tile, 
     * 
     *                              Otherwise, NULL
     */
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
        // AND NOT -1 since -1 is NO Beacon TIle (For safety measures)
        if(isNumeric(strMoveInfo) && Integer.parseInt(strMoveInfo) != -1)
        {
            moveToBeaconUpdate(Integer.parseInt(strMoveInfo)); // Encourage to discover undicovered possible GOLD TILES
        }
    }
    
    /**
     * Checks if the string being inputted is a numeric
     * @param String string, string to be checked if numeric
     * @param static boolean, true if string is numeric, otherwise False
     */
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
    
    /**
     * Rotates the miner in located in the arrHeuristicCell
     */
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

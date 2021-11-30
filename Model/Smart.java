package Model;

import java.io.IOException;
import java.io.PrintWriter;

public class Smart {
    private String strActionHistory; // History of all actions made. For verifiability.For Debugging.
    private int nPathCostCtr; // Path cost by the miner -Documentation Purposes
    private int nDepthCtr; // Depth of the tree for documentation purposes
    private int nActionsCombinedCount;
    private static final int MAX_POSSIBLE_ACTIONS = 3;
    private int nMaxDimension;
    private HeuristicBoard hbCurrHeuristicBoard; // Current heuristic board to be proccessed.
    private PrintWriter fFileWriter;

    /**
     * Constructs a Smart Class
     * @param nMaxDimension the dimension of the HeuristicBoard smart will generate 
     */
    public Smart(int nMaxDimension)
    {
        this.strActionHistory = new String();
        this.nPathCostCtr = 0;
        this.nDepthCtr = 0;
        this.nMaxDimension = nMaxDimension;
        this.nActionsCombinedCount = 7;  // Estimate count of actions needed to scan the while board ;(this.nMaxDimension)
        this.hbCurrHeuristicBoard = new HeuristicBoard(this.nMaxDimension);
    }

    /**
     * Get the Accumulated Path Cost of the all moves 
     * @return int count of accumulated path cost
     */
    public int getPathCost ()
    {
        return this.nPathCostCtr;
    }

    /**
     * Get current depth of the A* Search
     * @return int current depth of A* Search
     */
    public int getDepth ()
    {
        return this.nDepthCtr;
    }

    /**
     * Get number of actions to be combined
     * @return int number of actions to be combined
     */
    public int getActionsCombinedCount ()
    {
        return this.nActionsCombinedCount;
    }

    /**
     * Generates the best action basing in the current state of the HeuristicBoard by choosing the best first action in the combination of nActionsCombinedCount 
     * @return char the action in a given HeuristicBoard
     */
    public char aStar()
    {
        char cTriedAction; 
        char cBestAction= 'R';// Default Action to be retured is Rotate. Requires by java to have default value
        double nAccumulatedHeuristicCtr;
        double nBestAccumulatedHeuristic = -99999; // -999999 since there will be negative tiles later after scanning a PIT Tile
        int rows = (int) Math.pow(MAX_POSSIBLE_ACTIONS,nActionsCombinedCount);
        boolean bValid;
        char cTestBestFirstAction = 'R'; // R because Non-desruptive deault action. Requires by java to have default value
        
        // Loop to generate all combinations
        for (int i=0; i<rows ; i++) 
        {
            // DEBUG
            String strActionHistory = "";

            // Prepare Cloned Version of Latest Heuristic Board
            HeuristicBoard hbTempHeuristicBoard = new HeuristicBoard(this.hbCurrHeuristicBoard);

            bValid = true;
            nAccumulatedHeuristicCtr = 0;

            // Loop to generate the combinations of the node
            // NOTE: Always loops nActionsCombinedCount times
            for (int j=nActionsCombinedCount-1; j>=0; j--) 
            {                   
                // Formula to generate the combinations (Almost similar formula to generate the truth table)
                switch ((i/(int) Math.pow(MAX_POSSIBLE_ACTIONS, j))%MAX_POSSIBLE_ACTIONS ) 
                {
                    // 0 = Move, 1 = Rotate, 2 = Scan
                    case 0:
                        cTriedAction ='S';
                        break;
                    case 1:
                        cTriedAction ='M';
                        break;
                    case 2:
                        cTriedAction ='R';
                        break;
                    default:
                        cTriedAction ='R'; // Non-disruptive default action. Requires by java to have default value
                        System.out.println("ERROR: A* Switch Case");
                        break;
                };
                
                // Check if valid Action
                if(     cTriedAction != 'M' // If NOT Move Action no need to check
                    ||  isValidAction(hbTempHeuristicBoard))  // If Move Action, check if valid
                {
                    hbTempHeuristicBoard.tryAction(cTriedAction); // Execute Action

                    // If first Action
                    if(j == nActionsCombinedCount-1)
                    {
                        cTestBestFirstAction = cTriedAction; // Store to set later as the best first move if it belongs to the best combination
                    }

                    // Compute Accumulated Heuristic
                    // Cell Heuristic + (Fewer Steps = Additional Heuristics)
                    if((hbTempHeuristicBoard.getAccumulatedActionHeuristic() + j) > nAccumulatedHeuristicCtr)
                    {
                        nAccumulatedHeuristicCtr = hbTempHeuristicBoard.getAccumulatedActionHeuristic() + j; 
                    }
                    // DEBUG
                    strActionHistory += cTriedAction;
                }
                // If NOT VALID
                else{
                    bValid = false; // Disregard Combination of Moves
                    j = -1; // To exit Loop
                }
            }

            // If Higher Accumulated Heuristics 
            // AND valid combinations
            if(     nAccumulatedHeuristicCtr > nBestAccumulatedHeuristic
                &&  bValid)
            {
                nBestAccumulatedHeuristic = nAccumulatedHeuristicCtr; // Store the NEW HIGHEST Accumulated Heuristic
                cBestAction = cTestBestFirstAction; // Store the first move of the combination
            }
            
            strActionHistory = "";
            hbTempHeuristicBoard = null;
        }

        nDepthCtr++; // Increment the Depth of A* Search
        nPathCostCtr++; // Increment the Path Cost\
        
        return cBestAction;
    }

    /**
     * NOTE: This method assumes that the acion to be done is MOVE
     * Checks if the move action will place the Miner inside the Board nMaxDimension 
     * AND there is a miner to be moved
     * @param HeuristicBoard hbTempHeuristicBoard board to be check where if the miner move is valid
     * @return boolean true if valid, otherwise false
     */
    private boolean isValidAction (HeuristicBoard hbTempHeuristicBoard)
    {
        boolean bValidAction = true;
        
        // No Miner in the Curr Cell
        if(hbTempHeuristicBoard.getArrCell()[hbTempHeuristicBoard.getMinerCurrCoordinate().x][hbTempHeuristicBoard.getMinerCurrCoordinate().y].getMiner() == null)
        {
            bValidAction = false;
        }
        // Checks if move will place miner within the max dimension of the board
        else
        {
            // key is the Direction of the Miner
            switch(hbTempHeuristicBoard.getArrCell()[hbTempHeuristicBoard.getMinerCurrCoordinate().x][hbTempHeuristicBoard.getMinerCurrCoordinate().y].getMiner().getDirection())
            {

                case "DOWN":
                    bValidAction = hbTempHeuristicBoard.getMinerCurrCoordinate().x+1 < nMaxDimension;
                    break;
                case "UP":
                    bValidAction = hbTempHeuristicBoard.getMinerCurrCoordinate().x-1 >= 0;
                    break;
                case "LEFT":
                    bValidAction = hbTempHeuristicBoard.getMinerCurrCoordinate().y-1 >= 0;
                    break;
                case "RIGHT":
                    bValidAction = hbTempHeuristicBoard.getMinerCurrCoordinate().y+1 < nMaxDimension;
                    break;
                default:
                    System.out.println("[NODE CLASS] Invalid Direction");
            }
        }

        return bValidAction;
    }
    
    /**
     * Updates the MAIN Heuristic Board base from the action DONE of the SMART AGENT
     */
    public void updateHeuristicBoard(String strActionOutput) 
    {
        hbCurrHeuristicBoard.updateHeuristicBoard(strActionOutput);
    }

    

    


}

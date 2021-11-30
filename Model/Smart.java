package Model;

import java.io.IOException;
import java.io.PrintWriter;

public class Smart {
    private String strActionHistory; // History of all actions made. For verifiability.
    private int nPathCostCtr; // Path cost by the miner -Documentation Purposes
    private int nDepthCtr; // Depth of the tree for documentation purposes
    private int nActionsCombinedCount;
    private static final int MAX_POSSIBLE_ACTIONS = 3;
    private int nMaxDimension;
    private HeuristicBoard hbCurrHeuristicBoard; // Current heuristic board to be proccessed.
    private PrintWriter fFileWriter;

    public Smart(int nMaxDimension)
    {
        this.strActionHistory = new String();
        this.nPathCostCtr = 0;
        this.nDepthCtr = 0;
        this.nMaxDimension = nMaxDimension;
        this.nActionsCombinedCount = 7;  // Estimate count of actions needed to scan the while board ;(this.nMaxDimension)
        this.hbCurrHeuristicBoard = new HeuristicBoard(this.nMaxDimension);
    }

    public String getActionHistory ()
    {
        return this.strActionHistory;
    }

    public int getPathCost ()
    {
        return this.nPathCostCtr;
    }

    public int getDepth ()
    {
        return this.nDepthCtr;
    }

    public int getActionsCombinedCount ()
    {
        return this.nActionsCombinedCount;
    }

    public char aStar()
    {
        char cTriedAction; 
        char cBestAction= 'R';// Default Action to be retured is Rotate. Requires by java to have default value
        double nAccumulatedHeuristicCtr;
        double nBestAccumulatedHeuristic = -99999;
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
                nBestAccumulatedHeuristic = nAccumulatedHeuristicCtr;
                cBestAction = cTestBestFirstAction; // Store the first move of the combination
            }
            
            strActionHistory = "";
            hbTempHeuristicBoard = null;
        }

        nDepthCtr++; // Increment the Depth of A* Search
        nPathCostCtr++; // Increment the Path Cost\
        
        return cBestAction;
    }

    private boolean isValidAction (HeuristicBoard hbTempHeuristicBoard)
    {
        boolean bValidAction = true;
        // No Miner in the Curr Cell
        if(hbTempHeuristicBoard.getArrCell()[hbTempHeuristicBoard.getMinerCurrCoordinate().x][hbTempHeuristicBoard.getMinerCurrCoordinate().y].getMiner() == null)
        {
            bValidAction = false;
        }
        // Check if the action is move
        else
        {
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
    public void updateHeuristicBoard(String strActionOutput) 
    {
        hbCurrHeuristicBoard.updateHeuristicBoard(strActionOutput);
    }

    

    


}

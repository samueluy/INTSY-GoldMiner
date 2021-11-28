package Model;

public class Smart {
    private String strActionHistory; // History of all actions made. For verifiability.
    private int nPathCostCtr; // Path cost by the miner -Documentation Purposes
    private int nDepthCtr; // Depth of the tree for documentation purposes
    private int nActionsCombinedCount;
    private static final int MAX_POSSIBLE_ACTIONS = 3;
    private int nMaxDimension;
    private HeuristicBoard hbCurrHeuristicBoard; // Current heuristic board to be proccessed.

    public Smart(int nMaxDimension)
    {
        this.strActionHistory = new String();
        this.nPathCostCtr = 0;
        this.nDepthCtr = 0;
        this.nMaxDimension = nMaxDimension;
        this.nActionsCombinedCount = 3 * (this.nMaxDimension); // Estimate count of actions needed to scan the while board 
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
        this.hbCurrHeuristicBoard = hbCurrHeuristicBoard; // Stores the latest configuration of the heuristics board
        char cTriedAction; 
        char cBestAction= 'R';// Default Action to be retured is Rotate. Requires by java to have default value
        int nAccumulatedHeuristicCtr;
        int nBestAccumulatedHeuristic = 0;
        int rows = (int) Math.pow(MAX_POSSIBLE_ACTIONS,nActionsCombinedCount);
        boolean bValid;
        char cTestBestFirstAction = 'R'; // R because Non-desruptive deault action. Requires by java to have default value
        // Loop to generate all combinations
        for (int i=0; i<rows; i++) {
            // Prepare Cloned Version of Latest Heuristic Board
            HeuristicBoard hbTempHeuristicBoard = (HeuristicBoard) hbCurrHeuristicBoard.clone();

            bValid = true;
            nAccumulatedHeuristicCtr = 0;

            // Loop to generate the combinations of the node
            // NOTE: Always loops nActionsCombinedCount times
            for (int j=nActionsCombinedCount-1; j>=0; j--) 
            {
                char tempAction = 'R'; // Default Action: Move
                
                // Check if valid Action
                if(     tempAction != 'M' // If NOT Move Action no need to check
                    ||  isValidAction())  // If Move Action, check if valid
                {
                    
                    
                    // Formula to generate the combinations (Almost similar formula to generate the truth table)
                    switch ((i/(int) Math.pow(MAX_POSSIBLE_ACTIONS, j))%MAX_POSSIBLE_ACTIONS) 
                    {
                        // 0 = Move, 1 = Rotate, 2 = Scan
                        case 0:
                            cTriedAction ='M';
                            break;
                        case 1:
                            cTriedAction ='R';
                            break;
                        case 2:
                            cTriedAction ='S';
                            break;
                        default:
                            cTriedAction ='R'; // Non-disruptive default action. Requires by java to have default value
                            System.out.println("ERROR: A* Switch Case");
                            break;
                    };

                    hbTempHeuristicBoard.tryAction(cTriedAction); // Execute Action

                    // If first Action
                    if(j == nActionsCombinedCount-1)
                    {
                        cTestBestFirstAction = cTriedAction; // Store to set later as the best first move if it belongs to the best combination
                    }

                    
                }
                // If NOT VALID
                else{
                    bValid = false; // Disregard Combination of Moves
                    j = -1; // To exit Loop
                }
            }

            nAccumulatedHeuristicCtr = hbTempHeuristicBoard.getAccumulatedActionHeuristic(); // Get

            // If Higher Accumulated Heuristics 
            // AND valid combinations
            if(     nAccumulatedHeuristicCtr > nBestAccumulatedHeuristic
                &&  bValid)
            {
                cBestAction = cTestBestFirstAction; // Store the first move of the combination
            }
            
            
        }

        nDepthCtr++; // Increment the Depth of A* Search
        nPathCostCtr++; // Increment the Path Cost\

        return cBestAction;
    }

    public void computeNewHeuristicBoard ()
    {
        
    }
    private boolean isValidAction ()
    {
        boolean bValidAction = true;
        // No Miner in the Curr Cell
        if(hbCurrHeuristicBoard.getArrCell()[hbCurrHeuristicBoard.getMinerCurrCoordinate().x][hbCurrHeuristicBoard.getMinerCurrCoordinate().y].getMiner() == null)
        {
            bValidAction = false;
        }
        // Check if the action is move
        else
        {
            switch(hbCurrHeuristicBoard.getArrCell()[hbCurrHeuristicBoard.getMinerCurrCoordinate().x][hbCurrHeuristicBoard.getMinerCurrCoordinate().y].getMiner().getDirection())
            {

                case "RIGHT":
                    bValidAction = hbCurrHeuristicBoard.getMinerCurrCoordinate().x+1 < nMaxDimension;
                    break;
                case "LEFT":
                    bValidAction = hbCurrHeuristicBoard.getMinerCurrCoordinate().x-1 < nMaxDimension;
                    break;
                case "UP":
                    bValidAction = hbCurrHeuristicBoard.getMinerCurrCoordinate().y-1 < nMaxDimension;
                    break;
                case "DOWN":
                    bValidAction = hbCurrHeuristicBoard.getMinerCurrCoordinate().y+1 < nMaxDimension;
                    break;
                default:
                    System.out.println("[NODE CLASS] Invalid Direction");
            }
        }

        return bValidAction;
    }


    


}

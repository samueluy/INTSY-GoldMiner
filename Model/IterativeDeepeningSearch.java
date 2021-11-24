package Model;

import java.util.PriorityQueue;
import java.util.Queue;
import java.awt.Point;



public class IterativeDeepeningSearch {
    private Node ndRoot;
    private int nIncrement;
    private Queue<Node> qFringe;
    
    /**
     * Constructs the Iterative Deepening Search
     * @param boardRoot intial state of the game
     * @param pMinerCurrCoordinates coordanate where the miner is located at the start of the game
     * @param nIncrement count of increment of depth limit
     */
    public IterativeDeepeningSearch (Cell[][] boardRoot, Point pMinerCurrCoordinates,int nIncrement)
    {
        ndRoot = new Node('E', boardRoot, pMinerCurrCoordinates, null);
        this.nIncrement = nIncrement;
        qFringe = new PriorityQueue<Node>();

    }

    /**
     * Searches and constructs the tree to find the goal state (Miner in Gold tile)
     * @return the train of action to be done in the game to reach the goal state from left-to-right (first_action-to-last_action)
     */
    public String ids ()
    {
        boolean bFoundGoal = false; // Goal state not yet found
        int nCurrDepthLimit = 2; 
        int nCurrDepth = 1;
        Node ndGoal = null;

        // MAIN IDS LOOP
        do  // Loop until goal state is found
        {
            qFringe.clear(); // RESETS the fringe
            qFringe.add(ndRoot); // Add the Root Node

            // Depth Limited Search
            do
            {
                // Check if Goal State
                if (isGoalState(qFringe.peek().getState()[qFringe.peek().getMinerCoordinates().x][qFringe.peek().getMinerCoordinates().y]))
                {
                    bFoundGoal = true;
                    ndGoal = qFringe.remove(); // Remove the head of the queue
                    qFringe.clear(); // Clear fringe to exit loop and free memory
                }
                // Check if NOT yet reach the DEPTH LIMIT
                else if(nCurrDepth < nCurrDepthLimit)
                {
                    // Produce children nodes
                    produceChildren();

                    // Add produced child to Fringe
                    int nChildrentCount = qFringe.peek().getChildrenCount(); // Remove the head of the queue
                    Node tempParent = qFringe.remove();
                    for(int i=0;i < nChildrentCount; i++)
                    {
                        qFringe.add(tempParent.getChild(i));
                    }

                    nCurrDepth++;

                }
                

            }while(!qFringe.isEmpty()); // Loop until qFringe is empty
            

            nCurrDepthLimit = nCurrDepthLimit + nIncrement; // Increment the max Depth Limit
        }while (!bFoundGoal);

        // Backtrack
        return backtrack(ndGoal);
    }

    /**
     * Records the searched train of action to reach the goal state
     * @param ndGoalNode The final state/action done to reach the goal state.
     *                   Start of backtracking.
     * @return the train of action to be done in the game to reach the goal state from left-to-right (first_action-to-last_action)
     */
    private String backtrack (Node ndGoalNode)
    {
        String strActions = "";

        Node ndChild;
        Node ndParent = ndGoalNode;
        do
        {
            ndChild = ndParent;
            strActions = ndChild.getAction() + strActions;
            ndParent = ndChild.getParent();
        }while(ndParent != null);

        return strActions;
    }

    /**
     * Constructs the children of the head of the qFringe
     */
    private void produceChildren ()
    {
        // Add Move Child
        qFringe.peek().setChildState('M');

        // Add Rotate Child
        qFringe.peek().setChildState('S');

        // Add Scan Child
        qFringe.peek().setChildState('R');
    }

    /**
     * Checks if the miner is currently in the Goal tile.
     * Checks if the goal state is sufficed
     * @param cMinerCell the Cell where miner is currently located
     * @return True if miner is in Gold tile, otherwise false
     */
    private boolean isGoalState(Cell cMinerCell)
    {
        return cMinerCell.isMiner() && cMinerCell.isGold();
    }

}

package Model;

import java.util.LinkedList;
import java.util.Queue;

public class IterativeDeepeningSearch {
    private Queue<Node> qFringe;
    private Node ndCurrent;
    private final static int nStartDepth = 3;
    private final static int nDepthIncrement = 5;
    private boolean bGoalFound = false;

    public IterativeDeepeningSearch()
    {
        qFringe = new LinkedList<>();
    }
    
    private void ids(Node nStart)
    {
        ndCurrent = nStart;
        qFringe.add(nStart);
        // Loop while not in Goal State
        while (!bGoalFound) 
            // Loop while the fringe is not yet empth 
            // AND the Goal is NOT yet FOUND
            while (     !qFringe.isEmpty() 
                    ||  !bGoalFound)
            {
            //    if (isGoalState(curr.))
           //     {

              //  }

            }
    }


    /**
     * Checks if the current cell of the miner is the GOLD tile
     * @param cMinerCurrCell Cell : current cell of the miner
     * @return TRUE if the miner current cell is the Gold tile, otherwise FALSE;
     */
    private boolean isGoalState(Cell cMinerCurrCell)
    {
        boolean bGoalState = false;

        if (cMinerCurrCell.isGold())
        {
            bGoalState = true;
        }

        return bGoalState;
    }
}

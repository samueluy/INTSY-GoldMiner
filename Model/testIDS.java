package Model;


import java.awt.*;

public class testIDS
{
    public static int MAX_DEPTH;
    public Board currentBoard;

    public testIDS(Board board,int depth)
    {
        currentBoard = board;
        MAX_DEPTH = depth;
    }

    //IDS
    public Point IDS(testNode currentNode)
    {
        for(int i = 0;i<MAX_DEPTH;i++) {
            //INSIDE THE DLS, AS IT RECURSIVELY CALLS, do the move.
            //And before it returns false, do the Move.

        }
        return null;
    }

    public Point DLS(testNode currentNode,int depth)
    {
        Point nodeFound;
        if(currentBoard.cellContains(currentNode.getXcoord(),currentNode.getYcoord(),"G")) {
            nodeFound = new Point(currentNode.getXcoord(), currentNode.getYcoord());
            return nodeFound;
        }
        //Max Depth reached.
        if(depth<=MAX_DEPTH)
            return null;

        //Recursive Call.
        //PROBLEM: How will it find the Adjacent, EXCLUDING THE PARENT.
        return null;
    }
}

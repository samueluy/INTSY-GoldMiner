package Model;


import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class testIDS
{
    public static int MAX_DEPTH;
    public Board currentBoard;
    public boolean flagForPath;
    ArrayList<Point> pathChosen;

    public testIDS(Board board,int depth)
    {
        currentBoard = board;
        pathChosen = new ArrayList<>();
        flagForPath = false;
        MAX_DEPTH = depth;
    }

    /* Given the initial node, this method performs DLS
    *  with increasing depth.
    *
    *  @param initialnode - Agent's initial position.
    *  @returns ArrayList of Points.
    * */
    public ArrayList IDS(testNode initialNode)
    {
        Point returnThis = null;
        //Recursive DLS
        for(int i = 0;i<MAX_DEPTH;i++) {
            System.out.println("Entering Depth "+i);
            DLS(initialNode,i);
            System.out.println("Exiting Depth "+i);
            System.out.println();
            if(flagForPath)
                break;
        }
        return pathChosen;
    }

    /* Given the current node and depth, this method performs
    *  a recursive DLS until it reaches it reaches maximum depth.
    *
    * @param currentNode - current Node.
    * @param depth - Current depth.
    * */
    public void DLS(testNode currentNode,int depth)
    {
        testNode nextNode;

        //Check if path is on to Gold, or a Pit. Either way, searching should stop.
        if(currentBoard.cellContains(currentNode.getXcoord(),currentNode.getYcoord(),"G") || currentBoard.cellContains(currentNode.getXcoord(),currentNode.getYcoord(),"P") ) {
            pathChosen.add(currentNode.getNodeLocation());
            flagForPath = true;
            return;
        }

        //If maximum depth is reached, return to caller.
        if(depth<=0)
            return;

        //Set the Parent node of the initialNode.
        if(currentNode.getParentNode() == null)
        {
            currentNode.setParentNode(currentNode.getNodeLocation());
        }

        /* For each adjacent node of the Current node,
            recursively call DLS.
        * */
        for (int j = 0; j < currentNode.getSizeOfAdjacentNode(); j++) {
            nextNode = getAdjacent(currentNode.getAdjacentNode(j),currentNode.getNodeLocation());
            nextNode.setParentNode(currentNode.getNodeLocation());

            //Condition to avoid continuously including initialnode in path.
            if(!(nextNode.getNodeLocation().x == 0 && nextNode.getNodeLocation().y ==0))
                pathChosen.add(nextNode.getNodeLocation());

            DLS(nextNode,depth-1);
            backtrack(currentNode);

            //If a path has already been found.
            if(flagForPath)
                return;
        }
    }

    /* Given the currentNode, this method ensures
        that the Agent will move back to its previous position
        before going to another adjacent node.

        @param currentNode - currentNode.
    * */
    public void backtrack(testNode currentNode)
    {
        if(currentNode.getParentNode()!= null)
            pathChosen.add(currentNode.getNodeLocation());
    }
    /* Given the current location of the agent,
        find the adjacent nodes possible. Excluding the previous location.

        @param currentLocation - Current point of Agent.
        @param pastLocation - Previous Point coordinate of agent.
    * */
    public testNode getAdjacent(Point currentLocation,Point pastLocation)
    {
        ArrayList<Point> adjacentNodes = new ArrayList();
        testNode currentNode = new testNode(pastLocation,currentLocation);
        if((currentLocation.x - 1 < currentBoard.getMAX_DIMENSION() && currentLocation.x-1 >= 0) && currentLocation.x - 1 != pastLocation.x)
            adjacentNodes.add(new Point(currentLocation.x-1,currentLocation.y));
        if((currentLocation.x + 1 < currentBoard.getMAX_DIMENSION() && currentLocation.x + 1 >= 0 )&& currentLocation.x + 1 != pastLocation.x)
            adjacentNodes.add(new Point(currentLocation.x+1,currentLocation.y));
        if((currentLocation.y - 1 < currentBoard.getMAX_DIMENSION() && currentLocation.y-1 >= 0) && currentLocation.y - 1 != pastLocation.y)
            adjacentNodes.add(new Point(currentLocation.x,currentLocation.y-1));
        if((currentLocation.y + 1 < currentBoard.getMAX_DIMENSION() && currentLocation.y + 1 >= 0) && currentLocation.y + 1 != pastLocation.y)
            adjacentNodes.add(new Point(currentLocation.x,currentLocation.y+1));
        setAdjacentNodes(adjacentNodes,currentNode);
        return currentNode;
    }

    /*Given the list of Adjacent nodes, store the list
    within the currentNode of the Agent. This method is used in the Recursive DLS.

    @param adjacentNodes - List of adjacent Nodes.
    @param currentNode - current Node.
    * */
    public void setAdjacentNodes(ArrayList<Point> adjacentNodes,testNode currentNode)
    {
        currentNode.setAdjacentNodes(adjacentNodes);
    }
}

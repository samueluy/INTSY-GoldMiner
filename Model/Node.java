package Model;

import java.awt.Point;
import java.util.ArrayList;

public class Node implements Cloneable{
    private Cell[][] boardState;
    private Point pMinerCurrCoordinates;

    private Node ndParent;
    private ArrayList<Node> arrChild;
    private char cAction;


    /**
     * Constructs a Node
     * @param cAction describes what action have been done to the created new node: M = Move, R = Rotate, S = Scan
     * @param arrCells the arrCells or Board
     * @param pMinerCurrCoordinates the current coordinates of the miner of easy access
     * @param ndParent the Parent node of this created new node
     */
    public Node(char cAction, Cell[][] arrCells, Point pMinerCurrCoordinates, Node ndParent){
        this.boardState = arrCells;
        this.pMinerCurrCoordinates = pMinerCurrCoordinates;
        this.arrChild = new ArrayList<Node>();
        this.cAction = cAction;
    }

    /**
     * Set/Add a child state of this node
     * @param charAction action to be done to the created new node: M = Move, R = Rotate, S = Scan
     */
    public void setChildState (char charAction)
    {
        // Prohibit more than 3 children
        if(arrChild.size() <= 2 ) 
        {
            switch(charAction)
            {
                // FOR MOVE Action
                case 'M':
                    // Check if correct parameters
                    if(isValidAction(boardState[pMinerCurrCoordinates.x][pMinerCurrCoordinates.y]))
                    {
                        Point tempPoint = computeMinerMoveCoordinate (boardState[pMinerCurrCoordinates.x][pMinerCurrCoordinates.y].getMiner());
                        Cell[][] childBoard = boardState.clone();
                        // Move the miner to NEW Coordinates
                        childBoard[tempPoint.x][tempPoint.y].setMiner(boardState[pMinerCurrCoordinates.x][pMinerCurrCoordinates.y].getMiner());

                        // Remove the miner from past Coordinates
                        childBoard[pMinerCurrCoordinates.x][pMinerCurrCoordinates.y].removeMiner();

                        // add the move child to queue
                        arrChild.add(new Node(charAction, childBoard, (Point)tempPoint.clone(), this));
                    }
                    break;
                // For SCAN Action
                case 'S':
                    //arrChild.add(new Node(charAction, boardState.clone(), (Point) pMinerCurrCoordinates.clone(), (Node) this.clone()));
                    break;
                // For ROTATE Action
                case 'R':
                    //arrChild.add(new Node(charAction, boardState.clone()[pMinerCurrCoordinates.x][pMinerCurrCoordinates.y].getMiner().rotate(), pMinerCurrCoordinates, this));
                    break;

                // Incase there is other letter input
                default:
                    System.out.println("[NODE CLASS] Invalid Action");
            }  
            
        }
    }

    /**
     * Checks if the "MOVE" is a valid action
     * @param cMinerCurrCell the Cell where the miner is currently located
     * @return True if valid move, otherwise false
     */
    private boolean isValidAction ( Cell cMinerCurrCell)
    {
        boolean bValidAction = true;
        // No Miner in the Curr Cell
        if(cMinerCurrCell.getMiner() == null)
        {
            bValidAction = false;
        }
        // Check if will result to a GAMEOVER move
        else if (cMinerCurrCell.isPit())
        {
            bValidAction = false;
        }
        // Check if the action is move
        else
        {
            switch(cMinerCurrCell.getMiner().getDirection())
            {

                case "RIGHT":
                    bValidAction = pMinerCurrCoordinates.x-1 < boardState.length;
                    break;
                case "LEFT":
                    bValidAction = pMinerCurrCoordinates.x+1 < boardState.length;
                    break;
                case "UP":
                    bValidAction = pMinerCurrCoordinates.y-1 < boardState.length;
                    break;
                case "DOWN":
                    bValidAction = pMinerCurrCoordinates.y+1 < boardState.length;
                    break;
                default:
                    System.out.println("[NODE CLASS] Invalid Direction");
            }
        }

        return bValidAction;
    }

    /**
     * Outputs the coordinate of the miner after the MOVE action
     * @param mMiner the miner to get its direction facing
     * @return the coordinate of the miner after the MOVE action
     */
    private Point computeMinerMoveCoordinate (Miner mMiner)
    {
        Point tempPoint = (Point) pMinerCurrCoordinates.clone();
        switch(mMiner.getDirection())
        {
            case "RIGHT":
                tempPoint.x = pMinerCurrCoordinates.x+1;
                break;
            case "LEFT":
                tempPoint.x = pMinerCurrCoordinates.x-1;
                break;
            case "UP":
                tempPoint.y = pMinerCurrCoordinates.y-1;
                break;
            case "DOWN":
                tempPoint.y = pMinerCurrCoordinates.y+1;
                break;
        }

        return tempPoint;

    }
    
    /**
     * Gets the action done on this node
     * @return action that have been done to this node: M = Move, R = Rotate, S = Scan
     */
    public char getAction ()
    {
        return cAction;
    }

    /**
     * Gets the parent of this node
     * @return the node of the parent of this node
     */
    public Node getParent()
    {
        return ndParent;
    }

    /**
     * Get the state/board/arrCell of this node
     * @return
     */
    public Cell[][] getState ()
    {
        return boardState;
    }

    /**
     * Get the specific child of this node
     * @param nNthChild the place of the child in the arraylist (possible input: 0-2) (3 children atmost)
     * @return the nth child chosen in the arraylist
     */
    public Node getChild (int nNthChild)
    {
        return arrChild.get(nNthChild);
    }

    /**
     * Gets how many children this node has
     * @return count of children this node has
     */
    public int getChildrenCount ()
    {
        return arrChild.size();
    }

    /**
     * GEts the coordinates of the miner of this node
     * @return the coordinates of the miner of this node
     */
    public Point getMinerCoordinates()
    {
        return pMinerCurrCoordinates;
    }

    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    


}

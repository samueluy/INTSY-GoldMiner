package Model;

import java.awt.*;
import java.util.ArrayList;

/**
 * Used to perform the Iterative Deepening Depth-First Search (DFS) Algorithm to find the shortest path from a start to a target node.
 */
public class IterativeDeepeningDFS {
    private static boolean bottomReached = false; // Variable to keep track if we have reached the bottom of the tree
    private ArrayList<Point> path;
    public boolean flagForPath;
    public Board currentBoard;

    /**
     * Implementation of iterative deepening DFS (depth-first search).
     * Given a start node, this returns the node in the tree below the start node with the target value (or null if it doesn't exist)
     * Runs in O(n), where n is the number of nodes in the tree, or O(b^d), where b is the branching factor and d is the depth.
     *
     * @param start  the node to start the search from
     * @param target the value to search for
     * @return The node containing the target value or null if it doesn't exist.
     */

    public Node iterativeDeepeningDFS(Board board, Node start, int target) {
        // Start by doing DFS with a depth of 1, keep doubling depth until we reach the "bottom" of the tree or find the node we're searching for
        int depth = 1;
        path = new ArrayList<>();
        currentBoard = board;
        flagForPath = false;

        while (!bottomReached) {
            bottomReached = true; // One of the "end nodes" of the search with this depth has to still have children and set this to false again
            Node result = iterativeDeepeningDFS(start, target, 0, depth);
            if (result != null) {
                // We've found the goal node while doing DFS with this max depth
                return result;
            }

            // We haven't found the goal node, but there are still deeper nodes to search through
            depth *= 2;
            System.out.println("Increasing depth to " + depth);
        }

        // Bottom reached is true.
        // We haven't found the node and there were no more nodes that still have children to explore at a higher depth.
        return null;
    }

    private Node iterativeDeepeningDFS(Node node, int target, int currentDepth, int maxDepth) {
        System.out.println("Visiting Node " + node.value.x + " " + node.value.y);
        if (currentBoard.cellContains(node.value.x, node.value.y, "G") || currentBoard.cellContains(node.value.x, node.value.y, "P")) {
            // We have found the goal node we we're searching for
            System.out.println("Found the node we're looking for!");
            path.add(new Point(node.value.x, node.value.y));
            return node;
        }

        if (currentDepth == maxDepth) {
            System.out.println("Current maximum depth reached, returning...");
            // We have reached the end for this depth...
            if (node.children.length > 0) {
                //...but we have not yet reached the bottom of the tree
                bottomReached = false;
            }
            return null;
        }

        // Recurse with all children
        for (int i = 0; i < node.children.length; i++) {
            Node result = iterativeDeepeningDFS(node.children[i], target, currentDepth + 1, maxDepth);
            if (result != null) {
                // We've found the goal node while going down that child
                return result;
            }
        }

        // We've gone through all children and not found the goal node
        return null;
    }

    public testNode getAdjacent(Point currentLocation, Point pastLocation) {
        ArrayList<Point> adjacentNodes = new ArrayList();
        testNode currentNode = new testNode(pastLocation, currentLocation);
        if ((currentLocation.x - 1 < currentBoard.getMAX_DIMENSION() && currentLocation.x - 1 >= 0) && currentLocation.x - 1 != pastLocation.x)
            adjacentNodes.add(new Point(currentLocation.x - 1, currentLocation.y));
        if ((currentLocation.x + 1 < currentBoard.getMAX_DIMENSION() && currentLocation.x + 1 >= 0) && currentLocation.x + 1 != pastLocation.x)
            adjacentNodes.add(new Point(currentLocation.x + 1, currentLocation.y));
        if ((currentLocation.y - 1 < currentBoard.getMAX_DIMENSION() && currentLocation.y - 1 >= 0) && currentLocation.y - 1 != pastLocation.y)
            adjacentNodes.add(new Point(currentLocation.x, currentLocation.y - 1));
        if ((currentLocation.y + 1 < currentBoard.getMAX_DIMENSION() && currentLocation.y + 1 >= 0) && currentLocation.y + 1 != pastLocation.y)
            adjacentNodes.add(new Point(currentLocation.x, currentLocation.y + 1));
        //setAdjacentNodes(adjacentNodes, currentNode);
        return currentNode;
    }

    public void setAdjacentNodes(ArrayList<Point> adjacentNodes,Node currentNode)
    {
        //currentNode.setAdjacentNodes(adjacentNodes);
    }
}
// used to store a tree datastructure
class Node
{
    Node[] children;
    Point value;
}

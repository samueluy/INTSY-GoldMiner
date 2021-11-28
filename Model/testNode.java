package Model;
import java.util.*;
import java.awt.*;

public class testNode
{
    private Point parentNode;
    private Point nodeLocation;
    //private int nDepth;
    private ArrayList<Point> adjacentNodes;

    public testNode(Point pNode, Point nLocation)
    {
        parentNode = pNode;
        nodeLocation = nLocation;
        adjacentNodes = new ArrayList<>();
    }
    public testNode(Point pNode, int xCoord,int yCoord)
    {
        parentNode = pNode;
        nodeLocation = new Point(xCoord,yCoord);
        adjacentNodes = new ArrayList<>();

    }
    public testNode(int xCoord,int yCoord)
    {
        nodeLocation = new Point(xCoord,yCoord);
        adjacentNodes = new ArrayList<>();

    }

    public int getXcoord(){
        return nodeLocation.x;
    }
    public int getYcoord(){
        return nodeLocation.y;
    }
    public Point getNodeLocation(){ return nodeLocation;}

    public int getSizeOfAdjacentNode()
    {
        return adjacentNodes.size();
    }

    public Point getAdjacentNode(int index)
    {
        return adjacentNodes.get(index);
    }

    public Point getParentNode(){ return parentNode;}

    public void setParentNode(Point parentNode) {
        this.parentNode = parentNode;
    }

    public void setAdjacentNodes(ArrayList<Point> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}

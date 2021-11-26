package Model;
import java.util.*;
import java.awt.*;

public class testNode
{
    private Point parentNode;
    private Point nodeLocation;
    private int nDepth;

    public testNode(Point pNode, Point nLocation, int nDepth)
    {
        parentNode = pNode;
        nodeLocation = nLocation;
        this.nDepth = nDepth;
    }
    public testNode(Point pNode, int xCoord,int yCoord, int nDepth)
    {
        parentNode = pNode;
        nodeLocation = new Point(xCoord,yCoord);
        this.nDepth = nDepth;
    }

    public int getnDepth() {
        return nDepth;
    }
    public int getXcoord(){
        return nodeLocation.x;
    }
    public int getYcoord(){
        return nodeLocation.y;
    }

}

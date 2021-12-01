package Model;

public class RandomAgent extends testIDS
{
    private int nPathCost = 0;

    public RandomAgent(Board gameBoard, int nDepth)
    {
        super(gameBoard, nDepth);
    }

    public int getPathCost()
    {
        return nPathCost;
    }

    public void addToPathCost()
    {
        nPathCost++;
    }

    public String move()
    {
        return "M";
    }

    public String scan()
    {
        return "S";
    }

    public String rotate()
    {
        return "R";
    }
}
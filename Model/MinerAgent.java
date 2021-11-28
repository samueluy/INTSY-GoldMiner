package Model;

//Represents a MinerAgent class(Can be Random or Smart)
public abstract class MinerAgent 
{
    //private String strDirection;
    //private int nPathCost; 
    private String strFaceDirection = "RIGHT";
    private int nPathCostCtr = 0;

    //returns the direction where 
    //the miner is currently facing
    public String getDirectionFacing()
    {
        return this.strFaceDirection;
    }

    //returns total path cost
    public int getPathCost()
    {
        return this.nPathCostCtr;
    }

    //increments path cost when an action is made (scan or rotate or move)
    public void increasePathCost()
    {
        this.nPathCostCtr++;
    }

    //miner will move from one tile to another
    //I think there's no need for up, down, left, nor right because miner will only move based on where it's facing
    public String move()
    {
        return "M";
    }

    //miner will scan the tiles in front of it
    public String scan()
    {
        return "S";
    }

    //miner will rotate 90 degrees clockwise based on current value of strFaceDirection
    public String rotate()
    {
        switch(this.strFaceDirection){
            case "RIGHT":
                this.strFaceDirection = "DOWN";
                break;
            case "DOWN":
                this.strFaceDirection = "LEFT";
                break;
            case "LEFT":
                this.strFaceDirection = "UP";
                break;
            case "UP":
                this.strFaceDirection = "RIGHT";
                break;
        }
        return "R"; //to indicate that miner rotated, this might be helpful later when connecting to GUI
    }

    //public void move(){}
    //public void scan(){}
    //public void isValidMove(){} I don't think this should be here 

    //public Miner(){
    //    this.strFaceDirection = "RIGHT";
    //}
}
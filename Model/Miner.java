package Model;

public class Miner {
    private String strDirection;

    public Miner(){
        this.strDirection = "RIGHT";
    }
    
    public Miner(String strDirection)
    {
        this.strDirection =strDirection;
    }
    
    public String getDirection(){
        return this.strDirection;
    }
    
    public void rotate(){
        switch(strDirection){
            case "RIGHT":
                strDirection = "DOWN";
                break;
            case "DOWN":
                strDirection = "LEFT";
                break;
            case "LEFT":
                strDirection = "UP";
                break;
            case "UP":
                strDirection = "RIGHT";
                break;
        }
    }
    
    @Override
    public Object clone()
    {
        Miner mNewMiner = new Miner(this.strDirection);

        return mNewMiner;
    }


}

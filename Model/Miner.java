package Model;

public class Miner {
    private String strDirection;
    private int nCount;

    public void move(){}
    public void scan(){}
    public void isValidMove(){}

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

    public Miner(){
        this.strDirection = "RIGHT";
    }


}
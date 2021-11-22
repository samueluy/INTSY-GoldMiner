package Model;

public class Miner {
    private String strDirection;
    private int nCount;

    public void rotate(){
        switch(this.strDirection){
            case "RIGHT":
                this.strDirection = "DOWN";
                break;
            case "DOWN":
                this.strDirection = "LEFT";
                break;
            case "LEFT":
                this.strDirection = "UP";
                break;
            case "UP":
                this.strDirection = "RIGHT";
                break;
        }
    }
    public void move(){}
    public void scan(){}
    public void isValidMove(){}

    public String getDirection(){
        return this.strDirection;
    }

    public Miner(){
        this.strDirection = "RIGHT";
    }


}

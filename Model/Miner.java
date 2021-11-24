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

    public Miner(){
        this.strDirection = "RIGHT";
    }


}
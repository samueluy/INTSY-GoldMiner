package Model;

import java.awt.*;
import java.awt.geom.Point2D; // for distance formula


public class Cell {
    private int nWeight;
    private int nBeacon;
    private boolean bGold;
    private boolean bPit;
    private Miner mMiner;

    public void setWeight(int weight){
        this.nWeight=weight;
    }

    public void setBeacon(Point pGoldCoordinates){
        this.nBeacon= (int) Point2D.distance(pGoldCoordinates.x, pGoldCoordinates.x,
                pGoldCoordinates.y, pGoldCoordinates.y);
    }

    public void setGold(){
        this.bGold=true;
    }

    public void setPit(){
        this.bPit=true;
    }

    public void setMiner(){
        this.mMiner = new Miner();
    }

    public int getWeight(){
        return nWeight;
    }

    public int isBeacon(){
        return nBeacon;
    }

    public boolean isGold(){
        return bGold;
    }

    public boolean isPit(){
        return bPit;
    }

    public boolean isMiner(){
        if(this.mMiner!=null)
            return true;
        return false;
    }

    public boolean isFreeTerrain(){
        if(!this.bGold && !this.bPit)
            return true;

        return false;
    }

    Cell(){ // Default
        this.nBeacon=-1;
        this.bGold=false;
        this.bPit=false;
        this.mMiner=null;
    }
}

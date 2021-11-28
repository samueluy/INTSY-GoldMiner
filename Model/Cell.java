package Model;

import java.awt.*;
//import java.awt.geom.Point2D; // for distance formula
import java.lang.Math;

public class Cell {
    //private int nWeight;
    private int nBeacon;
    private boolean bGold;
    private boolean bPit;
    private MinerAgent mMiner;
    //private boolean bMiner;
    private String strSymbol;

    public Cell() { // Default
        this.nBeacon = -1;
        this.bGold = false;
        this.bPit = false;
        this.mMiner = null;
        //bMiner = false;
        strSymbol = "*";
    }

    //public void setWeight(int weight) {
    //    this.nWeight = weight;
    //}

    public void setBeacon(Point pBeaconCoordinates, Point pGoldCoordinates) {
        this.nBeacon = Math.abs(pBeaconCoordinates.x-pGoldCoordinates.x) + Math.abs(pBeaconCoordinates.y-
                pGoldCoordinates.y);

        strSymbol = "B";
    }

    public void setGold() {
        this.bGold = true;
        strSymbol = "G";
    }

    public void setPit() {
        this.bPit = true;
        strSymbol = "P";
    }

    //will set bMiner to TRUE if miner is in THIS instance of Cell
    //will set bMiner to  FALSE otherwise
    public void setMiner(boolean bIsMiner) 
    {
        this.mMiner = new Miner();
        //this.bMiner = bIsMiner;
        //strSymbol = "A";
    }

    //public void setMiner(Miner miner) {
    //    this.mMiner = miner;
    //}
    //
    //public void removeMiner() {
    //    this.mMiner = null;
    //}

    //public int getWeight() {
    //    return nWeight;
    //}

    public int isBeacon() {
        return nBeacon;
    }

    public boolean isGold() {
        return bGold;
    }

    public boolean isPit() {
        return bPit;
    }

    //public Miner getMiner() {
    //    return this.mMiner;
    //}
    ///For the hints.
    public boolean cellBeacon () {
        if (nBeacon > 0) {
            System.out.println("Beacon Hint:" + nBeacon);
            return true;
        }
        return false;
    }

    //will return TRUE if cell has miner
    //otherwise, FALSE
    public boolean isMiner() 
    {
        //if (this.mMiner != null) return true;
        //return false;
        return bMiner;
    }

    public boolean isFreeTerrain () {
        if (!this.bGold && !this.bPit) return true;

        return false;
    }

    //public String getStrSymbol() {
    //    return strSymbol;
    //}
    //
    //public void setStrSymbol (String symbol){
    //    strSymbol = symbol;
    //}
    //
    //@Override public String toString () {
    //     return strSymbol;
    //}
}

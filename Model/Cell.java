package Model;

import java.awt.*;
import java.awt.geom.Point2D; // for distance formula


public class Cell {
    private int nWeight;
    private int nBeacon;
    private boolean bGold;
    private boolean bPit;
    private Miner mMiner;

    private String strSymbol;

    public Cell() { // Default
        this.nBeacon = -1;
        this.bGold = false;
        this.bPit = false;
        this.mMiner = null;
        strSymbol = "*";
    }

    public void setWeight(int weight) {
        this.nWeight = weight;
    }

    public void setBeacon(Point pGoldCoordinates) {
        this.nBeacon = (int) Point2D.distance(pGoldCoordinates.x, pGoldCoordinates.x, pGoldCoordinates.y, pGoldCoordinates.y);

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

    public void setMiner() {
        this.mMiner = new Miner();
    }

    public void setMiner(Miner miner) {
        this.mMiner = miner;
        strSymbol = "A";
    }

    public void removeMiner() {
        this.mMiner = null;
        strSymbol = "A";
    }

    public int getWeight() {
        return nWeight;
    }

    public int isBeacon() {
        return nBeacon;
    }

    public boolean isGold() {
        return bGold;
    }

    public boolean isPit() {
        return bPit;
    }

    public Miner getMiner() {
        return this.mMiner;
    }
    ///For the hints.
    public boolean cellBeacon () {
        if (nBeacon > 0) {
            System.out.println("Beacon Hint:" + nBeacon);
            return true;
        }
        return false;
    }

    public boolean isMiner() {
        if (this.mMiner != null) return true;
        return false;
    }

    public boolean isFreeTerrain () {
        if (!this.bGold && !this.bPit) return true;

        return false;
    }

    public String getStrSymbol() {
        return strSymbol;
    }

    public void setStrSymbol (String symbol){
        strSymbol = symbol;
    }

    @Override public String toString () {
        return strSymbol;
    }
}

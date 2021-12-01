package Model;

import java.awt.*;
import java.awt.geom.Point2D; // for distance formula
import java.lang.Math;

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

    public void setMiner() {
        this.mMiner = new Miner();
        strSymbol = "A";
    }

    public void setGold(String update) {
        this.bGold = true;
        this.nBeacon = -1;
        this.bPit = false;
        this.mMiner = null;
    }

    public void setPit(String update) {
        this.bPit = true;
        this.nBeacon = -1;
        this.bGold = false;
        this.mMiner = null;
    }

    public void setMiner(String update) {
        this.mMiner = new Miner();
        this.nBeacon = -1;
        this.bGold = false;
        this.bPit = false;
    }

    public void setMiner(Miner miner) {
        this.mMiner = miner;
        strSymbol = "*";
    }

    public void newCell()
    {
        this.nBeacon = -1;
        this.bGold = false;
        this.bPit = false;
        this.mMiner = null;
        strSymbol = "*";
    }

    public void removeMiner() {
        this.mMiner = null;
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
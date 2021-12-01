package Model;

public class HeuristicCell {
    private double nScanWeight;
    private double nMoveWeight;

    private Miner mMiner;

    public HeuristicCell ()
    {
        this.nScanWeight = 5;
        this.nMoveWeight = 1;
    }

    public void setScanWeight (double nNewScanWeight)
    {
        this.nScanWeight = nNewScanWeight;
    }

    public void setMoveWeight (double nNewMoveWeight)
    {
        this.nMoveWeight = nNewMoveWeight;
    }

    public void setMiner (Miner mNewMiner)
    {
        this.mMiner = mNewMiner;
    }
    public void setMiner()
    {
        this.mMiner = new Miner();
    }
    public double getScanWeight ()
    {
        return this.nScanWeight;
    }

    public double getMoveWeight ()
    {
        return this.nMoveWeight;
    }

    public double getTotalWeight ()
    {
        return this.nScanWeight + nMoveWeight;
    }

    public Miner getMiner ()
    {
        return this.mMiner;
    }

    public void removeMiner ()
    {
        this.mMiner = null;
    }

    public void removeScanWeight ()
    {
        this.nScanWeight = 0;
    }

    public void removeMoveWeight ()
    {
        this.nMoveWeight = 0;
    }

    public void removeAllWeight ()
    {
        removeScanWeight();
        removeMoveWeight();
    }



}
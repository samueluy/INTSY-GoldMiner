package Model;

public class HeuristicCell {
    private int nScanWeight;
    private int nMoveWeight;

    private Miner mMiner;

    public HeuristicCell ()
    {
        this.nScanWeight = 1;
        this.nMoveWeight = 1;
    }

    public void setScanWeight (int nNewScanWeight)
    {
        this.nScanWeight = nNewScanWeight;
    }

    public void setMoveWeight (int nNewMoveWeight)
    {
        this.nMoveWeight = nNewMoveWeight;
    }

    public void setMiner (Miner mNewMiner)
    {
        this.mMiner = mNewMiner;
    }
    public int getScanWeight ()
    {
        return this.nScanWeight;
    } 

    public int getMoveWeight ()
    {
        return this.nMoveWeight;
    }

    public int getTotalWeight ()
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

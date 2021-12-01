package Model;

import java.awt.*;
import java.util.*;
import View.*;

public class Game
{
    private RandomAgent randomAgent;
    private Smart smartAgent;
    private Board gameBoard;
    private ArrayList<String> finalSetRandom;
    private int beaconFlag = -1;
    public static int nMoves = 0;
    public static int nScans = 0;
    public static int nRotates = 0;

    public Game(int nDimension) {
        gameBoard = new Board(nDimension);
        finalSetRandom = new ArrayList<>();
    }

    //will either make aMiner a LEVEL R or LEVEL S MinerAgent depending
    //on the button clicked during start up game (Random or Smart Button) 
    public void setLevel(String strLvlOfRationalBehavior)
    {
        resetCounters();
        switch (strLvlOfRationalBehavior)
        {
            case "Random":
                randomAgent = new RandomAgent(gameBoard, 16);
                smartAgent = null;
                break;
            case "Smart":
                smartAgent = new Smart(gameBoard.getMAX_DIMENSION());
                randomAgent = null;
                break;
        }
    }

    //will create a new board depending on nDimension's value
    public void setBoardDimension(int nDimension)
    {
        gameBoard = null; //will reset gameBoard when game is finished
        gameBoard = new Board(nDimension);
    }


    //returns the current instance of Board
    public Board getGameBoard()
    {
        return this.gameBoard;
    }

    public RandomAgent getRandomAgent()
    {
        return randomAgent;
    }

    public Smart getSmartAgent()
    {
        return smartAgent;
    }

    public ArrayList<String> getFinalSetOfActions()
    {
        return finalSetRandom;
    }

    /*
    * This Method is the current controller of the game.
    * */
    //public void play()
    //{
    //    testIDS randomAgent = new testIDS(gameBoard, 16);
    //
    //    ArrayList<Point> pathChosen;
    //    int pitFlag = 0;
    //    gameBoard.display();
    //
    //    testNode initialNode = new testNode(0,0);
    //        initializeAdjacent(randomAgent,initialNode); ///
    //        pathChosen = randomAgent.IDS(initialNode);  ///Path for the IDS of Random.
    //    for (int i = 0; i < pathChosen.size(); i++) {
    //        System.out.println(pathChosen.get(i).x+" "+pathChosen.get(i).y);
    //        action(pathChosen.get(i));
    //
    //        //If you want to see it Slowly.
    //        /*try {
    //            TimeUnit.SECONDS.sleep(1);
    //        }
    //        catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }*/
    //
    //        if (!isGameOver())
    //            break;
    //
    //        if (gameBoard.isPit()) {
    //            pitFlag = 1;
    //            break;
    //        }
    //    }
    //    if(pitFlag == 1)
    //        System.out.println("Russian Man: You Failed me. *Shoots*");
    //    else {
    //        try {
    //            System.out.println("You: *Hands Over Gold*");
    //            TimeUnit.SECONDS.sleep(5);
    //            System.out.println("Russian Man: Good work, Soldier.");
    //            TimeUnit.SECONDS.sleep(2);
    //            System.out.println("Russian Man: *Shoots*");
    //            TimeUnit.SECONDS.sleep(2);
    //            System.out.println("Russian Man: Nothing personal…");
    //            TimeUnit.SECONDS.sleep(3);
    //            System.out.println("Russian Man: Comrade.");
    //            TimeUnit.SECONDS.sleep(1);
    //            System.out.println("Russian Man: *Shoots*");
    //        } catch (InterruptedException e) {
    //            System.err.format("IOException: %s%n", e);
    //        }
    //    }
    //}

    /* Given the desired direction, check if the move is valid
        if it is, update the board. All change in positions
        must be accounted for and reflected in the Board.

        @param dir - Direction
        */
    public void actionRandom(String dir, GUI gui)
    {

        int xCoord = -1,yCoord = -1,flag = 0;

        //Find the Current Position of the Agent.
        Point coords = gameBoard.boardFind("A");

        // Take the x and y
        if(coords.x != -1) {
            xCoord = coords.x;
            yCoord = coords.y;
        }

        //Check if the move is valid.
        if(isValidMoveRandom(dir,xCoord,yCoord))
        {
            gui.updateMiner(gameBoard);
            //Beaconflag is for restoring the beacon when player leaves it.
            if(beaconFlag == 0)
            {
                gameBoard.updatePos(xCoord, yCoord, "B");
                beaconFlag = -1;
                flag = 1;
            }

            if (dir.contains("U")) {
                /*If the desired cell has a Beacon. Display the hint.
                Note: getHint returns an integer -- useful for giving it to agent.
                * */
                if(gameBoard.cellContains(xCoord - 1,yCoord,"B")) {
                    //System.out.println("Beacon Hint: " + gameBoard.getHint(xCoord - 1, yCoord));
                    gui.showJOptionPane(GUI.imgMessage, "Beacon Hint: " + gameBoard.getHint(xCoord - 1, yCoord));
                    beaconFlag = 0;
                }
                gameBoard.updatePos(xCoord, yCoord, xCoord - 1, yCoord, dir);
            }

            else if (dir.contains("D")) {
                if(gameBoard.cellContains(xCoord + 1,yCoord,"B")) {
                    //System.out.println("Beacon Hint: " + gameBoard.getHint(xCoord + 1, yCoord));
                    gui.showJOptionPane(GUI.imgMessage, "Beacon Hint: " + gameBoard.getHint(xCoord + 1, yCoord));
                    beaconFlag = 0;
                }
                gameBoard.updatePos(xCoord, yCoord, xCoord + 1, yCoord, dir);
            }

            else if (dir.contains("L")) {
                if(gameBoard.cellContains(xCoord,yCoord - 1,"B")) {
                    //System.out.println("Beacon Hint: " + gameBoard.getHint(xCoord, yCoord - 1));
                    gui.showJOptionPane(GUI.imgMessage, "Beacon Hint: " + gameBoard.getHint(xCoord, yCoord - 1));
                    beaconFlag = 0;
                }
                gameBoard.updatePos(xCoord, yCoord, xCoord, yCoord - 1, dir);
            }

            else if (dir.contains("R")) {
                if(gameBoard.cellContains(xCoord,yCoord + 1,"B")) {
                    //System.out.println("Beacon Hint: " + gameBoard.getHint(xCoord, yCoord + 1));
                    gui.showJOptionPane(GUI.imgMessage, "Beacon Hint: " + gameBoard.getHint(xCoord, yCoord + 1));
                    beaconFlag = 0;
                }
                gameBoard.updatePos(xCoord, yCoord, xCoord, yCoord + 1, dir);
            }

            if(flag != 1)
                gameBoard.updatePos(xCoord, yCoord, "*");

            finalSetRandom.add(randomAgent.move());
            nMoves++;
            randomAgent.addToPathCost();
            //Updates the Current Location of Miner.
            gameBoard.setpMinerCurrCoordinate(gameBoard.boardFind("A"));
            //gameBoard.display();
            
        }
        //Agent uses scan.
        else if(dir.contains("S") || dir.contains("Scan") || dir.contains("scan")) {
            //System.out.println(gameBoard.scan());
            gui.showJOptionPane(GUI.imgMessage, "Scanned: " + gameBoard.scan());
            nScans++;
            gui.updateMiner(gameBoard);
            finalSetRandom.add(randomAgent.scan());
            randomAgent.addToPathCost();
        }
        //Agent uses rotate.
        // if(dir.contains("R") || dir.contains("rotate")) {
        //    //gameBoard.rotate();
        //    gameBoard.rotate();
        //    gameBoard.smartRotate();
        //    //System.out.println("Current Direction "+gameBoard.getMinerDirection());
        //    finalSetRandom.add(randomAgent.rotate());
        //    randomAgent.addToPathCost();
        //}
        //System.out.println("==============="+dir);
        //else
        //    System.out.println("Russian Man: Invalid Move, Comrade.");
    }

    /* Given the desired Point, Find the right direction/action
        and use the previous definition of action().
        @param dir - desired Point
     */
    public void actionRandom(Point dir, GUI gui)
    {
        gui.updateMiner(gameBoard);
        int counter = 0;
        int xCoord = -1,yCoord = -1;
        String direction;

        //Find the Current Position of the Agent.
        Point coords = gameBoard.boardFind("A");

        // Take the x and y
        if(coords.x != -1) {
            xCoord = coords.x;
            yCoord = coords.y;
        }

        //Determine direction of point
        if(dir.x == xCoord - 1)
            direction = "U";
        else if(dir.x == xCoord + 1)
            direction = "D";
        else if(dir.y == yCoord - 1)
            direction = "L";
        else if(dir.y == yCoord + 1)
            direction = "R";
        else
        {
            direction = "S";
            //System.out.println("outsideeeeeeeeeeeeeeee" + dir);
        }

        while(isValidMoveRandom(direction,xCoord,yCoord)) {
            gameBoard.rotate();
            gameBoard.smartRotate();
            //System.out.println("insideeeeeeeeeeeeee" + direction);
            gui.updateMiner(gameBoard);
            finalSetRandom.add(randomAgent.rotate());
            randomAgent.addToPathCost();
            nRotates++;
            counter++;      //To prevent an infinite loop.
            if(counter>6)
                break;
        }
        actionRandom(direction, gui);
    }

    /*
     * Given the Direction and Coordinates of Agent, this method
     *  checks if the resulting table will not produce any outofbounds exception
     * the desired move.
     *
     * @param dir - Desired Direction
     * @param x - x Coordinate
     * @param y - y Coordinate
     * */
    public boolean isValidMoveRandom(String dir,int x,int y)
    {
        boolean flag = false;
        if(dir.contains("U") && gameBoard.getMinerDirection().contains("U")) {
            if((x - 1 < gameBoard.getMAX_DIMENSION() && x-1 >= 0))
                flag = true;
        }
        else if(dir.contains("D") && gameBoard.getMinerDirection().contains("D")){
            if((x + 1 < gameBoard.getMAX_DIMENSION() && x + 1 >= 0))
                flag = true;
        }
        else if(dir.contains("L") && gameBoard.getMinerDirection().contains("L")){
            if((y - 1 < gameBoard.getMAX_DIMENSION() && y-1 >= 0))
                flag = true;
        }
        else if(dir.contains("R") && gameBoard.getMinerDirection().contains("R") && dir.compareToIgnoreCase("rotate") != 0){
            if((y + 1 < gameBoard.getMAX_DIMENSION() && y + 1 >= 0))
                flag = true;
        }
        else if(dir.contains("Scan"))
            flag = true;

        return flag;
    }

    /*
    This method checks if the player/agent has already reached
    the Gold pit.
     */
    public Boolean isGameOver()
    {
        for (int i = 0;i< gameBoard.getMAX_DIMENSION();i++) {
            for (int j = 0; j < gameBoard.getMAX_DIMENSION(); j++)
                if (gameBoard.cellContains(i,j,"G"))
                    return true;
        }
        return false;
    }

    /*Purpose of this method is to solely initialize the adjacent points of the initial node.
    *
    * @param thisAgent - Random Agent using IDS.
    * @param initialNode - initial position of the player.
    * */
    public void initializeAdjacentRandom(testIDS thisAgent, testNode initialNode)
    {
        ArrayList<Point> adjacentNodes = new ArrayList<>();
        adjacentNodes.add(new Point(0,1));
        adjacentNodes.add(new Point(1,0));
        thisAgent.setAdjacentNodes(adjacentNodes,initialNode);
    }

    private void resetCounters()
    {
        Game.nMoves = 0;
        Game.nScans = 0;
        Game.nRotates = 0;
    }

    /*public static void main(String[] args) {
        Game thisGame = new Game(8);
        thisGame.play();
    }*/
}

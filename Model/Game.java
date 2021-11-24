package Model;

import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game
{
    private Board tempBoard;
    private int beaconFlag = -1;

    public Game(int nDimension) {            //Agent PARAMETER soon.
        tempBoard = new Board(nDimension);
    }

    /*
    * This Method is the current controller of the game.
    * */
    public void play()
    {
        Scanner kb = new Scanner(System.in);

        int pitFlag = 0;
        tempBoard.display();

        String input = "";
        while( !(input.contains("QUIT")) )
        {
            input = kb.nextLine();
            if(input.length() < 2 || input.compareToIgnoreCase("rotate") == 0 || input.compareToIgnoreCase("scan") == 0)
                action(input);
            if(!isGameOver())
                break;
            //If yung natapakan ni Player is true yung pit: Game over.
            if(tempBoard.isPit()){
                pitFlag = 1;
                break;
            }
        }

        if(pitFlag == 1)
            System.out.println("Russian Man: You Failed me. *Shoots*");
        else {
            try {
                System.out.println("You: *Hands Over Gold*");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Russian Man: Good work, Soldier.");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Russian Man: *Shoots*");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Russian Man: Nothing personal…");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("Russian Man: Comrade.");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Russian Man: *Shoots*");
            } catch (InterruptedException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }
    /* Given the desired direction, check if the move is valid
        if it is, update the board. All change in positions
        must be accounted for and reflected in the Board.

        @param dir - Direction
        */
    public void action(String dir)
    {

        int xCoord = -1,yCoord = -1,flag = 0;

        //Find the Current Position of the Agent.
        Point coords = tempBoard.boardFind("A");

        // Take the x and y
        if(coords.x != -1) {
            xCoord = coords.x;
            yCoord = coords.y;
        }

        //Check if the move is valid.
        if(validMove(dir,xCoord,yCoord))
        {
            //Beaconflag is for restoring the beacon when player leaves it.
            if(beaconFlag == 0)
            {
                tempBoard.updatePos(xCoord, yCoord, "B");
                beaconFlag = -1;
                flag = 1;
            }

            if (dir.contains("U")) {
                /*If the desired cell has a Beacon. Display the hint.
                Note: getHint returns an integer -- useful for giving it to agent.
                * */
                if(tempBoard.cellContains(xCoord - 1,yCoord,"B")) {
                    System.out.println("Beacon Hint: " + tempBoard.getHint(xCoord - 1, yCoord));
                    beaconFlag = 0;
                }
                tempBoard.updatePos(xCoord - 1, yCoord, "A");
            }

            else if (dir.contains("D")) {
                if(tempBoard.cellContains(xCoord + 1,yCoord,"B")) {
                    System.out.println("Beacon Hint: " + tempBoard.getHint(xCoord + 1, yCoord));
                    beaconFlag = 0;
                }
                tempBoard.updatePos(xCoord + 1, yCoord, "A");
            }

            else if (dir.contains("L")) {
                if(tempBoard.cellContains(xCoord,yCoord - 1,"B")) {
                    System.out.println("Beacon Hint: " + tempBoard.getHint(xCoord, yCoord - 1));
                    beaconFlag = 0;
                }
                tempBoard.updatePos(xCoord, yCoord - 1, "A");
            }

            else if (dir.contains("R")) {
                if(tempBoard.cellContains(xCoord,yCoord + 1,"B")) {
                    System.out.println("Beacon Hint: " + tempBoard.getHint(xCoord, yCoord + 1));
                    beaconFlag = 0;
                }
                tempBoard.updatePos(xCoord, yCoord + 1, "A");
            }

            if(flag != 1)
                tempBoard.updatePos(xCoord, yCoord, "*");

            //Updates the Current Location of Miner.
            tempBoard.setpMinerCurrCoordinate(tempBoard.boardFind("A"));
            tempBoard.display();
        }
        //Agent uses scan.
        else if(dir.contains("S") || dir.contains("Scan") || dir.contains("scan"))
            System.out.println(tempBoard.scan());
        //Agent uses rotate.
        else if(dir.contains("Rotate") || dir.contains("rotate")) {
            tempBoard.rotate();
            System.out.println("Current Direction "+tempBoard.getMinerDirection());
        }
        else
            System.out.println("Russian Man: Invalid Move, Comrade.");
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
    public boolean validMove(String dir,int x,int y)
    {
        boolean flag = false;
        if(dir.contains("U") && tempBoard.getMinerDirection().contains("U")) {
            if((x - 1 < 8 && x-1 >= 0))
                flag = true;
        }
        else if(dir.contains("D") && tempBoard.getMinerDirection().contains("D")){
            if((x + 1 < 8 && x + 1 >= 0))
                flag = true;
        }
        else if(dir.contains("L") && tempBoard.getMinerDirection().contains("L")){
            if((y - 1 < 8 && y-1 >= 0))
                flag = true;
        }
        else if(dir.contains("R") && tempBoard.getMinerDirection().contains("R") && dir.compareToIgnoreCase("rotate") != 0){
            if((y + 1 < 8 && y + 1 >= 0))
                flag = true;
        }
        return flag;
    }

    /*
       This method checks if the player/agent has already reached
       the Gold pit.
     */
    public Boolean isGameOver()
    {
        for (int i = 0;i< tempBoard.getMAX_DIMENSION();i++) {
            for (int j = 0; j < tempBoard.getMAX_DIMENSION(); j++)
                if (tempBoard.cellContains(i,j,"G"))
                    return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Game thisGame = new Game(8);
        thisGame.play();
    }
}

import java.util.*;
import java.util.concurrent.TimeUnit;


public class Game
{
    //private Agent agent
    private boolean gameOver;
    private String[][] tempBoard;
    //private board gameBoard;

    public Game() {            //Agent agent)
        tempBoard = new String[8][8];
        initBoard();
    }
    private void initBoard()
    {
        int i,j;
        int Min = 0;
        int Max = 7;
        boolean exitFlag = false;
        boolean flagG = false, flagB = false,flagD = false;

        for (i = 0;i< tempBoard.length;i++)
        {
            for(j = 0; j< tempBoard[0].length;j++)
                tempBoard[i][j] = "*";
        }
        tempBoard[0][0] = "A";

        while(!exitFlag)
        {
            i = Min + (int)(Math.random() * ((Max - Min) + 1));
            j = Min + (int)(Math.random() * ((Max - Min) + 1));
            if(tempBoard[i][j].contains("*") && !flagG) {
                tempBoard[i][j] = "G";
                flagG = true;
            }
            if(tempBoard[i][j].contains("*") && !flagB) {
                tempBoard[i][j] = "B";
                flagB = true;
            }
            ///Will modify this one soon.
            if(tempBoard[i][j].contains("*") && !flagD) {
                tempBoard[i][j] = "D";
                flagD = true;
            }
            if(flagB && flagD && flagG)
                exitFlag = true;
        }
    }

    private void display()
    {
        for (int i = 0;i< tempBoard.length;i++)
        {
            for(int j = 0; j< tempBoard[0].length;j++)
                System.out.print(tempBoard[i][j]+" ");
            System.out.println();
        }

    }

    /*Given the desired direction, if valid,
    * change and update the array.*/
    public void action(String dir)
    {
        /*
        * Check if move is valid
        * Get old and new coords.
        * Switch.(For now).
        * */
        int i,j,xCoord = -1,yCoord = -1;
        for(i=0; i< tempBoard.length; i++)
        {
            for(j=0;j<tempBoard[0].length;j++)
                if(tempBoard[i][j].contains("A")){
                    xCoord = i;
                    yCoord = j;
                }
        }
        //If Valid yung Move.
        if(validMove(dir,xCoord,yCoord)) {
            if (dir.contains("U"))
                tempBoard[xCoord - 1][yCoord] = "A";
            else if (dir.contains("D"))
                tempBoard[xCoord + 1][yCoord] = "A";
            else if (dir.contains("L"))
                tempBoard[xCoord][yCoord - 1] = "A";
            else if (dir.contains("R"))
                tempBoard[xCoord][yCoord + 1] = "A";

            tempBoard[xCoord][yCoord] = "*";
            display();
        }
        else
            System.out.println("Invalid Move Comrade.");


    }

    /*
    * Given the Direction and Coordinates of Agent, Validate
    * the desired move.
    * */
    public boolean validMove(String dir,int x,int y)
    {
        boolean flag = false;
        if(dir.contains("U")) {
            if((x - 1 < 8 && x-1 >= 0) && tempBoard[x - 1][y].contains("*"))
                flag = true;
        }
        else if(dir.contains("D")){
            if((x + 1 < 8 && x + 1 >= 0) && tempBoard[x + 1][y].contains("*"))
                flag = true;
        }
        else if(dir.contains("L")){
            if((y - 1 < 8 && y-1 >= 0) && tempBoard[x][y - 1].contains("*"))
                flag = true;
        }
        else if(dir.contains("R")){
            if((y + 1 < 8 && y+1 >= 0) && tempBoard[x][y + 1].contains("*"))
                flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        Game gg = new Game();
        Scanner kb = new Scanner(System.in);

        String input = "";
        gg.display();
        while( !(input.contains("QUIT")) )
        {
            input = kb.nextLine();
            if(input.length() < 2) {
                gg.action(input);
            }
        }

        /*Keep it for now HAHA*/
        try {
            System.out.println("Russian Man: Nothing personal...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("Russian Man: Comrade.");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Russian Man: *Shoots*");
        } catch(InterruptedException e){
            System.err.format("IOException: %s%n", e);
        }
    }
}
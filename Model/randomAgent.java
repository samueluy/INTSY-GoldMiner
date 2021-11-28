package Model;


import java.util.*;

//public class randomAgent
public class randomAgent extends MinerAgent
{
    //creates an instance of randomAgent IF 
    //level of rational behavior chosen is Random
    public randomAgent()
    {
        super();
    }

    //generates random action (Move - M, Rotate - R, or Scan - S)
    //public String randomMove()
    public String generateRandomAction()
    {
        //0 - Move 1 - Rotate 2 - Scan
        String thisMove;
        //int moveDirection = 0;
        Random randoInt = new Random();
        int randomizer = randoInt.nextInt(3);
        if(randomizer == 0)
        {
            //moveDirection = randoInt.nextInt(4);
            //if(moveDirection == 0)
            //    thisMove = "U";
            //else if(moveDirection == 1)
            //    thisMove = "D";
            //else if(moveDirection == 2)
            //    thisMove = "L";
            //else
            //    thisMove = "R";
            thisMove = move();
        }
        else if(randomizer == 1) thisMove = rotate();
        else thisMove = scan();
        return thisMove;
    }
}

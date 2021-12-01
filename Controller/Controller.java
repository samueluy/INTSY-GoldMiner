package Controller;

import java.io.IOException;
import javax.sound.sampled.*;
import java.io.*;
import java.awt.event.*;

import Model.*;
import View.*;

public class Controller implements ActionListener
{
    private ModelMainMenu modelMainMenu;
    private GUI gui;
    private Clip sound;
    private AudioInputStream audio;
    private FloatControl gainControl;

    private int i = 0;
    private String strTemp = "Stop";

    public Controller()
    {
        modelMainMenu = new ModelMainMenu();

        initializeSoundFiles();
        controlSoundVolume(-30);//control sound


        gui = new GUI(modelMainMenu);
        gui.setActionListeners(this);
    }

    /** 
    * This function listens to the GUI buttons with an ActionListener and
    * specifies the activities to be done if a particular button is pressed
    *
    * @param e      the ActionEvent that that is passed to every GUI button with an ActionListener
    */
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Start AI System"))
        {
            //reset methods
            gui.showNumOfCellsMenu();
        }
        if(e.getActionCommand().equals("Submit"))
        {
            getTotalCellNumber();
        }
        if(e.getActionCommand().equals("Random"))
        {
            gui.showStartGoldMiner(modelMainMenu, "Random");
            modelMainMenu.getGame().setLevel("Random");
        }
        if(e.getActionCommand().equals("Smart"))
        {
            gui.showStartGoldMiner(modelMainMenu, "Smart");
            modelMainMenu.getGame().setLevel("Smart");
        }
        if(e.getActionCommand().equals("Start Mining"))
        {
            strTemp = "Stop";
            gui.updateStartMiningBtn(false);
            if(modelMainMenu.getGame().getRandomAgent() != null) randomLevel();
            else strTemp = "GoSmart";
        }
        if(e.getActionCommand().equals("About Gold Miner"))
        { 
            gui.showAboutGoldMiner();
        }
        if(e.getActionCommand().equals("Sound Settings"))
        {
            gui.showSoundSettings();
        }
        if(e.getActionCommand().equals("Track 1"))
        {
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(1));
        }
        if(e.getActionCommand().equals("Track 2"))
        {
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(2));
        }
        if(e.getActionCommand().equals("Default Track"))
        {
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(0));
        }
        if(e.getActionCommand().equals("Go back to Main Menu"))
        {
            gui.showMainMenu();
        }
        if(e.getActionCommand().equals("Exit AI System"))
        {
            System.exit(0);
        }
        if(e.getActionCommand().equals("Go"))
        {
            if(strTemp == "GoRandom")
            {
                if(i < modelMainMenu.getGame().getRandomAgent().getPathChosen().size())
                {
                    modelMainMenu.getGame().actionRandom(modelMainMenu.getGame().getRandomAgent().getPathChosen().get(i), gui);
                    gui.updateCounterScreen(Game.nMoves, Game.nScans, Game.nRotates, modelMainMenu.getGame().getRandomAgent().getPathCost());
                    i++;
                }
                if(!modelMainMenu.getGame().isGameOver())
                {
                    gui.showJOptionPane(GUI.imgCongrats, "Congratulations! Gold found!!! :))");
                    gui.showJOptionPane(GUI.imgMessage, "Total number of backtracks made: " + modelMainMenu.getGame().getRandomAgent().getBacktrack());
                    System.exit(0);
                    //gui.updateStartMiningBtn(true);
                    //gui.makeCounterInvisible();
                    //strTemp = "Stop";
                    //gui.showMainMenu();
                    //i = 0;
                }           
                if(modelMainMenu.getGame().getGameBoard().isPit())
                {
                    gui.showJOptionPane(GUI.imgLoser, "Oopsie! You fell into a pit :((");
                    gui.showJOptionPane(GUI.imgMessage, "Total number of backtracks made: " + modelMainMenu.getGame().getRandomAgent().getBacktrack());
                    System.exit(0);
                    //gui.updateStartMiningBtn(true);
                    //gui.makeCounterInvisible();
                    //strTemp = "Stop";
                    //gui.showMainMenu();
                    //i = 0;
                }
            }
            else
            {
                smartLevel();
            }
        }
        if(e.getActionCommand().equals("Quit"))
        {
            System.exit(0);
        }
    }

    /** 
    * This function initializes the .wav files to be used in the game
    *
    */
    private void initializeSoundFiles()
    {
        try 
        {
            updateAudioInputStream(modelMainMenu.getSoundSettings().getFile(0));
        } catch (UnsupportedAudioFileException e2) 
        {
            e2.printStackTrace();
        } catch (IOException e2) 
        {
            e2.printStackTrace();
        }
        try 
        {
            sound = AudioSystem.getClip();
        } catch (LineUnavailableException e1) 
        {
            e1.printStackTrace();
        }
        try 
        {
            sound.open(audio);
        } catch (LineUnavailableException e) 
        {
            e.printStackTrace();
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
    }


    /** 
    * This function gets the audio input stream given a File object
    *
    * @param f     the File object to get the audio input stream from
    */
    private void updateAudioInputStream(File f) throws UnsupportedAudioFileException, IOException
    {
        audio = AudioSystem.getAudioInputStream(f);
    }

    /** 
    * This function controls the sound volume given a float value
    *
    * @param f     the float value to be set as the new volume of a Clip object
    */
    private void controlSoundVolume(float f)
    {
        gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(f);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /** 
    * This function changes the soundtrack of the game given a File object
    *
    */
    private void changeSoundTrack(File fileToPlay)
    {
        sound.stop();
        sound.close();
        try 
        {
            updateAudioInputStream(fileToPlay);
        } catch (UnsupportedAudioFileException e2) 
        {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try 
        {
            try 
            {
                sound.open(audio);
                sound.start();
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (IOException e1) 
            {
                e1.printStackTrace();
            }
        } catch (LineUnavailableException e1) 
        {
            e1.printStackTrace();
        }
    }

    //gets the desired Model.Board.MAX_DIMENSION of a user triggered by clicking the Submit button
    private void getTotalCellNumber()
    {
        int nCells;
        //checks if input is a number
        //input is a number
        if(gui.getNumOfCellsMenu().getTxtFldNumOfCells().getText().matches("[0-9]+"))
        {
            //converts input string to int
            nCells = Integer.parseInt(gui.getNumOfCellsMenu().getTxtFldNumOfCells().getText());
            //checks if input is not from 8 to 64
            if(!(nCells >= 8 && nCells <= 64))
            {
                gui.showJOptionPane(GUI.imgMessage, "Oopsie! Invalid value submitted :(("); //pop invalid message
            }
            else
            {
                modelMainMenu.getGame().setBoardDimension(nCells); //set Model.Board.MAX_DIMENSION = nCells
                gui.showChooseLevelMenu(); //makes NumberOfCells JPanel invisible and ChooseLevel JPanel visible
            }
        }
        //input is not a number
        else gui.showJOptionPane(GUI.imgError, "Oopsie! Invalid value submitted :((");
    }

    // lets the the Random Agent play the game
    private void randomLevel()
    {
        testNode initialNode = new testNode(0, 0);
        modelMainMenu.getGame().initializeAdjacentRandom(modelMainMenu.getGame().getRandomAgent(), initialNode);
        modelMainMenu.getGame().getRandomAgent().IDS(initialNode);
        strTemp = "GoRandom";
    }


    // lets the the Smart Agent play the game
    public void smartLevel()
    {
        String strActionOutput = new String();
        char cAction;

        cAction = modelMainMenu.getGame().getSmartAgent().aStar(); // Store Best Action
        
        strActionOutput = modelMainMenu.getGame().getGameBoard().executeAction(cAction); // Execute Action and Store Action return information
        modelMainMenu.getGame().getSmartAgent().setStrActionHistory(cAction); // Store Action Done to The history of actions
        
        //update counters per action based on action done
        if(cAction == 'M') Game.nMoves++;
        else if(cAction == 'R') Game.nRotates++;
        else Game.nScans++;

        String strTokens[] = strActionOutput.split(",");

        // Key is the Action, Example "M" = Move, "S" = Scan, "R" = Rotate
        switch(strTokens[0])
        {
            case "M":
                gui.updateMiner(modelMainMenu.getGame().getGameBoard()); //update GUI based on executed action
                // If Win
                if(strTokens[1].equals("GOLD")) 
                {
                    gui.showJOptionPane(GUI.imgCongrats, "Congratulations! Gold found!!! :))");
                    System.exit(0);
                }
                // If Lose
                else if (strTokens[1].equals("PIT"))
                {
                    gui.showJOptionPane(GUI.imgLoser, "Oopsie! You fell into a pit :((");
                    System.exit(0);
                }

                // Check if Beacon Tile
                // AND NOT -1 since -1 is NO Beacon TIle (For safety measures)
                else if(isNumeric(strTokens[1]) && Integer.parseInt(strTokens[1]) != -1)
                {
                    gui.showJOptionPane(GUI.imgMessage, "Beacon Hint: " + strTokens[1]); //show hint
                }
                break;
            case "R":
                gui.updateMiner(modelMainMenu.getGame().getGameBoard()); //update GUI based on executed action
                break;
            case "S":
                gui.showJOptionPane(GUI.imgMessage, "Scanned: " + strTokens[1]); //show scanned
                break;
            default:
                System.out.println("ERROR: updateHeuristicBoard");
                break;
        }
        gui.updateCounterScreen(Game.nMoves, Game.nScans, Game.nRotates, modelMainMenu.getGame().getSmartAgent().getPathCost()); //update counters (move, rotate, scan, path cost)

        modelMainMenu.getGame().getSmartAgent().updateHeuristicBoard(strActionOutput); // Update the Heuristic board with the return information of the action
    }

    /**
     * Checks if the string being inputted is a numeric
     * @param String string, string to be checked if numeric
     * @param static boolean, true if string is numeric, otherwise False
     */
    private static boolean isNumeric(String string) {
        int intValue;
            
            
        if(string == null || string.equals("")) {
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }
}


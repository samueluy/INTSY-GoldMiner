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
            randomLevel();
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
            if(i < modelMainMenu.getGame().getRandomAgent().getPathChosen().size() && strTemp == "Go")
            {
                modelMainMenu.getGame().actionRandom(modelMainMenu.getGame().getRandomAgent().getPathChosen().get(i), gui);
                gui.updateCounterScreen(Game.nMoves, Game.nScans, Game.nRotates, modelMainMenu.getGame().getRandomAgent().getPathCost());
                i++;
            }
            if(!modelMainMenu.getGame().isGameOver())
            {
                gui.showJOptionPane(GUI.imgCongrats, "Congratulations! Gold found!!! :))");
                gui.showJOptionPane(GUI.imgMessage, "Total number of backtracks made: " + modelMainMenu.getGame().getRandomAgent().getNumBackTracks());
                gui.updateStartMiningBtn(true);
                gui.makeCounterInvisible();
                strTemp = "Stop";
                gui.showMainMenu();
                i = 0;
            }           
            if(modelMainMenu.getGame().getGameBoard().isPit())
            {
                gui.showJOptionPane(GUI.imgLoser, "Oopsie! You fell into a pit :((");
                gui.showJOptionPane(GUI.imgMessage, "Total number of backtracks made: " + modelMainMenu.getGame().getRandomAgent().getNumBackTracks());
                gui.updateStartMiningBtn(true);
                gui.makeCounterInvisible();
                strTemp = "Stop";
                gui.showMainMenu();
                i = 0;
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

    private void randomLevel()
    {
        testNode initialNode = new testNode(0, 0);
        int tempPitFlag = 0;
        modelMainMenu.getGame().initializeAdjacent(modelMainMenu.getGame().getRandomAgent(), initialNode);
        modelMainMenu.getGame().getRandomAgent().IDS(initialNode);
        strTemp = "Go";
    }
}


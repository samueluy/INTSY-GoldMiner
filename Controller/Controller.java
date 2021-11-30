package Controller;

import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;

import java.io.*;
import java.awt.event.*;

import Model.*;
import View.*;

public class Controller implements ActionListener{
    private ModelMainMenu modelMainMenu;
    private GUI gui;
    private Clip sound;
    private AudioInputStream audio;
    private FloatControl gainControl;

    private int nCells;

    private ImageIcon imgError = new ImageIcon("Lib/utilities/Error.png");
    private ImageIcon imgCongrats = new ImageIcon("Lib/utilities/Congratulations.png");
    private ImageIcon imgLoser = new ImageIcon("Lib/utilities/Loser.png");

    public Controller(){
        modelMainMenu = new ModelMainMenu();

        initializeSoundFiles();
        controlSoundVolume(-8);


        gui = new GUI(modelMainMenu);
        gui.setActionListeners(this);
    }
    
    /**
    *   starts the the Smart Agent to Play the game.
    */
    public void smartPlay ()
    {
        Board brdBoard = new Board(nMaxDimension);
        Smart sSmartAgent = new Smart(nMaxDimension);
        String strActionOutput = new String();
        char cAction;

        do 
        {
            brdBoard.display();
            cAction = sSmartAgent.aStar(); // Store Best Action
                    
            strActionOutput = brdBoard.executeAction(cAction); // Execute Action and Store Action return information
            strActionHistory = strActionHistory + cAction; // Store Action Done to The history of actions
            sSmartAgent.updateHeuristicBoard(strActionOutput); // Update the Heuristic board with the return information of the action
  
            // If Win
            if(strActionOutput.equals("M,GOLD")) 
            {
                bWin = true;
                break;
            }
            
            // If Lose
            else if (strActionOutput.equals("M,PIT"))
            {
                bGameOver = true;
                break;
            }
        }while(!bGameOver && !bWin);
    }

    /** 
    * This function listens to the GUI buttons with an ActionListener and
    * specifies the activities to be done if a particular button is pressed
    *
    * @param e      the ActionEvent that that is passed to every GUI button with an ActionListener
    */
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Start AI System")){
            //reset methods
            gui.showNumOfCellsMenu();
        }
        if(e.getActionCommand().equals("Submit")){
            getTotalCellNumber();
        }
        if(e.getActionCommand().equals("Random")){
            gui.showStartGoldMiner(nCells, "Random");
        }
        if(e.getActionCommand().equals("Intelligent")){
            gui.showStartGoldMiner(nCells, "Intelligent");
        }
        if(e.getActionCommand().equals("Start Mining")){
            gui.showJOptionPane(imgCongrats, "Congratulations! You found the gold :))");
        }
        if(e.getActionCommand().equals("About Gold Miner")){
            gui.showAboutGoldMiner();
        }
        if(e.getActionCommand().equals("Sound Settings")){
            gui.showSoundSettings();
        }
        if(e.getActionCommand().equals("Track 1")){
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(1));
        }
        if(e.getActionCommand().equals("Track 2")){
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(2));
        }
        if(e.getActionCommand().equals("Default Track")){
            changeSoundTrack(modelMainMenu.getSoundSettings().getFile(0));
        }
        if(e.getActionCommand().equals("Go back to Main Menu")){
            gui.showMainMenu();
        }
        if(e.getActionCommand().equals("Exit AI System")){
            System.exit(0);
        }
    }

    /** 
    * This function initializes the .wav files to be used in the game
    *
    */
    private void initializeSoundFiles()
    {
        try {
            updateAudioInputStream(modelMainMenu.getSoundSettings().getFile(0));
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            sound = AudioSystem.getClip();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
        try {
            sound.open(audio);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /** 
    * This function gets the audio input stream given a File object
    *
    * @param f     the File object to get the audio input stream from
    */
    private void updateAudioInputStream(File f) throws UnsupportedAudioFileException, IOException{
        audio = AudioSystem.getAudioInputStream(f);
    }

    /** 
    * This function controls the sound volume given a float value
    *
    * @param f     the float value to be set as the new volume of a Clip object
    */
    private void controlSoundVolume(float f){
        gainControl = (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(f);
        sound.start();
        sound.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /** 
    * This function changes the soundtrack of the game given a File object
    *
    */
    private void changeSoundTrack(File fileToPlay){
        sound.stop();
        sound.close();
        try {
            updateAudioInputStream(fileToPlay);
        } catch (UnsupportedAudioFileException e2) {
            e2.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            try {
                sound.open(audio);
                sound.start();
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    //optimize dis later
    private void getTotalCellNumber(){
        if(gui.getNumOfCellsMenu().getTxtFldNumOfCells().getText().matches("[0-9]+")){
            nCells = Integer.parseInt(gui.getNumOfCellsMenu().getTxtFldNumOfCells().getText());
            if(!(nCells >= 8 && nCells <= 69)) gui.showJOptionPane(imgError, "Oopsie! Invalid value submitted :((");
            else gui.showChooseLevelMenu();
        }
        else gui.showJOptionPane(imgError, "Oopsie! Invalid value submitted :((");
    }
}


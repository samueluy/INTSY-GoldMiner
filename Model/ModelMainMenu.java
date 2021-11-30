package Model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

//represents the main menu of the entire system
public class ModelMainMenu
{
    private Game game;
    private String strAboutGoldMiner;
    private ModelSoundSettings soundSettings;

    public ModelMainMenu()
    {
        try 
        {   
            //get texts about Gold Miner and stores it into strAboutGoldMiner
            strAboutGoldMiner = new String(Files.readAllBytes(Paths.get("Lib/utilities/AboutGoldMiner.txt")));
        } catch (IOException e) 
        {
            e.printStackTrace();
        }
        soundSettings = new ModelSoundSettings();
        game = new Game(8);
    }

    //returns game
    public Game getGame()
    {
        return this.game;
    }

    //returns instructions about Gold Miner for the About Gold Miner button in GUI
    public String getStrAboutGoldMiner()
    {
        return this.strAboutGoldMiner;
    }

    //returns soundSettings for the Sound Settings button in GUI 
    public ModelSoundSettings getSoundSettings()
    {
        return this.soundSettings;
    }
}
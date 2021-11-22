package Model;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class ModelMainMenu{
    //private Game game
    private String strAboutGoldMiner;
    private ModelSoundSettings soundSettings;

    public ModelMainMenu(){
        try {
            strAboutGoldMiner = new String(Files.readAllBytes(Paths.get("Lib/utilities/AboutGoldMiner.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        soundSettings = new ModelSoundSettings();
    }

    public String getStrAboutGoldMiner(){
        return strAboutGoldMiner;
    }

    public ModelSoundSettings getSoundSettings(){
        return soundSettings;
    }
}

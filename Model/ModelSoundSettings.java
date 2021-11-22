package Model;

import java.io.File;

public class ModelSoundSettings{
    private File[] arrTracks;

    /** 
    * Constructs a new ModelSoundSettings object
    *
    * @apiNote 	   Lewis G. (2016, February 26). Stardew Valley OST - Settling In. 
    *                   [Video]. YouTube. Retrieved November 13, 2021 from https://www.youtube.com/watch?v=R0-Q17WChH0&list=PLKDOdCjxOjzIFucHobwJpSK4-vAVXST90&index=4
    * @apiNote 	   1ofamillion. (2015, February 19). Monster Hunter - Poke Village Theme. 
    *                   [Video]. YouTube. Retrieved November 13, 2021 from https://www.youtube.com/watch?v=__QdAxqBi5Y
    * @apiNote 	   Cloud183. (2008, June 03). Final Fantasy VII - Waltz de Chocobo [HQ]. 
    *                   [Video]. YouTube. Retrieved November 13, 2021 from https://www.youtube.com/watch?v=UB3nB5vdmcs
    */
    public ModelSoundSettings(){
        arrTracks = new File[4];
        arrTracks[0] = new File("Lib/utilities/TrackDefault.wav");
        arrTracks[1] = new File("Lib/utilities/Track1.wav");
        arrTracks[2] = new File("Lib/utilities/Track2.wav");
    }

    public File getFile(int nIndex){
        return arrTracks[nIndex];
    }
}

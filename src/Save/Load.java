package Save;

import Main.Handler;
import Text.Text;
import Utility.CustomFileReader;

import java.io.File;

public class Load {

    /**
     * Load the file date in the game.
     * @param handler Handler to access the game and load the data values.
     * @param fileStorage The fileStorage in which is the  transition between the save file and the program.
     * @return True when the loading is finished. False if the one of the parameters is null.
     */
    public static boolean load(Handler handler, FileStorage fileStorage) {
        if(fileStorage == null || handler == null)
            return false;
        // load sound
        handler.getSound().fromString(String.valueOf(fileStorage.get("Sound.Volume")));
        handler.getSound().playSoundWithKey("background.wav");
        handler.getMenuState().getSliderSoundVolume().setValue(handler.getSound().getCurrentPercent());
        // load ...

        return true;
    }

    /**
     * Load default values in the game.
     * @param handler Handler to access the game and load the default values
     * @return True when the loading is finished. False if the parameter is null.
     */
    public static boolean loadDefault(Handler handler) {
        if(handler == null)
            return false;
        // load sound
        handler.getSound().playSoundWithKey("background.wav");
        handler.getMenuState().getSliderSoundVolume().setValue(handler.getSound().getCurrentPercent());
        // load ...

        return true;
    }
}

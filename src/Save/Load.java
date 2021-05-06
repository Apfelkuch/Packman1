package Save;

import Main.Handler;
import Text.Text;
import Utility.CustomFileReader;
import music.Sound;

import java.io.File;

public class Load {

    /**
     * Load the content from the save-file into the game.
     *
     * @param handler The handler to load the content into the game.
     * @param path    The path from the save-file.
     * @return true if the process was successful, otherwise false.
     */
    public static boolean load(Handler handler, String path) {
        if (!(new File(path).isFile())) {
            return false;
        }
        String loadString = CustomFileReader.loadFileAsString(Text.SavePath);
        String[] parts = loadString.split("\n");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].split(":")[0].equals("class music.Sound")) {
                handler.getSound().fromString(parts[i]);
                handler.getSound().playSound(Sound.BACKGROUND_MUSIC);
                handler.getMenuState().getSoundVolume().setValue(handler.getSound().getCurrentPercent());
            }
        }
        return true;
    }


}

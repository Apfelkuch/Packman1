package Save;

import Main.Handler;
import Text.Text;
import Utility.CustomFileReader;
import music.Sound;

import java.io.File;

public class Load {

    public static boolean load(Handler handler) {
        if(!(new File(Text.SavePath).isFile())) {
            return false;
        }
        String loadString = CustomFileReader.loadFileAsString(Text.SavePath);
        String[] parts = loadString.split("\n");

        for (int i=0;i<parts.length;i++) {
            if(parts[i].split(":")[0].equals("class music.Sound")) {
                handler.getSound().fromString(parts[i]);
                handler.getSound().playSound(Sound.BACKGROUND_MUSIC);
                handler.getMenuState().getSoundVolume().setValue(handler.getSound().getCurrentPercent());
            }
        }
        return true;
    }


}

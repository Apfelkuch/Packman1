package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static String BACKGROUND_MUSIC = "res/sound/background.wav";

    private boolean play;

    public Sound() {
        play = false;
    }

    public void playSound(String soundFile) {
        try {
            File f = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            play = true;
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlay() {
        return play;
    }
}

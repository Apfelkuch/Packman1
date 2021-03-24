package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static String BACKGROUND_MUSIC = "res/sound/background.wav";

    private Clip clip = null;

    public void playSound(String soundFile) {
        try {
            File f = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    // GETTER && SETTER
    public boolean isPlay() {
        return clip.isRunning();
    }
    public Clip getClip() {
        return clip;
    }
}

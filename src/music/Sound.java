package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static String BACKGROUND_MUSIC = "res/sound/background.wav";
    private float offset = 0;

    private float volume = 0.0f;

    private File currentSoundFile = null;
    private Clip clip = null;
    private FloatControl floatControl = null;

    public void playSound(String soundFile) {
        try {
            // set offset
            if(soundFile.equals(BACKGROUND_MUSIC))
                offset = 70;

            currentSoundFile = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(currentSoundFile.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
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
    public float getVolume() {
        return volume;
    }
    public FloatControl getFloatControl() {
        return floatControl;
    }

    /**
     * Set the volume of the sound.
     * @param percent The percent of the volume, between 0 and 1.
     */
    public void setVolume(float percent) {
        if(floatControl == null) return;
        float diff = floatControl.getMaximum() - floatControl.getMinimum() - offset - 1;
        volume = floatControl.getMinimum() + offset + (diff * percent);
        System.out.println(volume);
        floatControl.setValue(volume);
    }

    public float getCurrentPercent() {
        float a = Math.abs(floatControl.getValue()) + Math.abs(floatControl.getMinimum() + offset);
        float b = floatControl.getMaximum() - floatControl.getMinimum() - offset;
        return a / b;
    }
}

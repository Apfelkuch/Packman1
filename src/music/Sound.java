package music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class Sound {

    public static String BACKGROUND_MUSIC = "res/sound/background.wav";
    private ArrayList<Song> songs;

    /**
     * BACKGROUND_MUSIC = offset: 40
     */
    private float offset = 0;

    private float volume = 0.0f;

    private File currentSoundFile = null;
    private Clip clip = null;
    private FloatControl floatControl = null;

    public Sound() {
        // TODO need to be completed.
        File[] files = new File("res/sound").listFiles(new FileFilter() {
            @Override
            public boolean accept(File f) { // accept all files with a wav extension and ignore the rest
                if (f != null) {
                    if (f.isDirectory()) {
                        return true;
                    }
                    String fileName = f.getName();
                    int i = fileName.lastIndexOf('.');
                    if (i > 0 && i < fileName.length() - 1) {
                        String desiredExtension = fileName.substring(i + 1).toLowerCase(Locale.ENGLISH);
                        if (desiredExtension.equals("wav")) {
                            return true;
                        }
                    }
                }
                return false;
            }
        });
//        System.out.println("Sound.Sound:soundFiles");
        songs = new ArrayList<Song>();
        for (File f : files) {
//            System.out.println(f.getName());
            if (f != null) {
                songs.add(new Song(f.getName(), f.getPath()));
            }
        }

    }

    public void playSound(String soundFile) {
        try {
            // set offset
            if (soundFile.equals(BACKGROUND_MUSIC))
                offset = 40;

            currentSoundFile = new File(soundFile);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(currentSoundFile.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(volume);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Put the class data in a save format.
     *
     * @return his.getClass().toString() + ":" + volume
     */
    public String toString() {
        return this.getClass().toString() + ":" + volume;
    }

    /**
     * Check if the String is a data format of this class and if that is true.
     * The information form the string are put into the class values.
     *
     * @param s The string with the data.
     * @return true, if successful otherwise false.
     */
    public boolean fromString(String s) {
        if (!s.split(":")[0].equals(this.getClass().toString()))
            return false;
        volume = Float.parseFloat(s.split(":")[1]);
        if (floatControl != null) {
            floatControl.setValue(volume);
        }
        return true;
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
     *
     * @param percent The percent of the volume, between 0 and 1.
     */
    public void setVolume(float percent) {
        if (floatControl == null) return;
        float diff = floatControl.getMaximum() - floatControl.getMinimum() - offset;
        volume = floatControl.getMinimum() + offset + (diff * percent);
        floatControl.setValue(volume);
    }

    /**
     * Find the current percent ratio between the current sound value and the area, in which the value can go.
     * (minimum bis current - offset) / (maximum - minimum - offset)
     *
     * @return The ratio between the current and all possible values.
     */
    public float getCurrentPercent() {
        float a = -floatControl.getMinimum() + floatControl.getValue() - offset;
        float b = floatControl.getMaximum() - floatControl.getMinimum() - offset;
        return a / b;
    }

}

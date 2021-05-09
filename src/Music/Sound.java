package Music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class Sound {

    /**
     * The Hashmap in which the path is saved with the sound file name from the "res/sound" directory as the key.
     */
    private HashMap<String, String> songs;

    /**
     * BACKGROUND_MUSIC = offset: 40
     */
    private float offset = 0;

    /**
     * The volume of the sound.
     */
    private float volume = 0.0f;

    /**
     * The current sound file which is played.
     */
    private File currentSoundFile = null;

    /**
     * The clip.
     */
    private Clip clip = null;

    /**
     * The floatControl.
     */
    private FloatControl floatControl = null;

    /**
     * Initialize the sound class and load the path of all sound from the "res/sound" directory into the songs Hashmap.
     */
    public Sound() {
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
                        return desiredExtension.equals("wav");
                    }
                }
                return false;
            }
        });
        songs = new HashMap<String, String>(); // filename, file path to play the sound
        if (files == null) {
            throw new IllegalArgumentException("file directory 'res/sound' have not sounds");
        }
        for (File file : files) {
            if (file != null) {
                songs.put(file.getName(), file.getPath());
            }
        }

    }

    /**
     * Play the sound given in the file.
     *
     * @param soundFile The path of the file with the sound.
     */
    public void playSound(String soundFile) {
        try {
            // set offset
            if (soundFile.equals(songs.get("background.wav"))) {
                offset = 40;
            }

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
     * Play a sound with the key, with is in the songs Hashmap.
     *
     * @param keyAsFilename The key from the sound in the Hashmap.
     */
    public void playSoundWithKey(String keyAsFilename) {
        playSound(songs.get(keyAsFilename));
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

    /**
     * Check if the current clip is playing.
     *
     * @return True if the clip is running, otherwise false.
     */
    public boolean isPlay() {
        return clip != null && clip.isRunning();
    }

    /**
     * Getter for the clip.
     *
     * @return The clip.
     */
    public Clip getClip() {
        return clip;
    }

    /**
     * Getter for the volume.
     *
     * @return The volume.
     */
    public float getVolume() {
        return volume;
    }

    /**
     * Getter for the floatControl.
     *
     * @return The floatControl.
     */
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
     * Find the current percent ratio between the current sound value and the area, in which the value can go.<br>
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

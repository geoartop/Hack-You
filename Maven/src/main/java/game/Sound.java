package game;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * <p>Αναπαραγωγή ήχου και ηχητικών εφέ</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Sound implements ChangeListener {

    private Clip clip;
    private static final URL[] soundURL = new URL[6];
    private boolean active = false;
    private static int volume = (SoundSettings.min + SoundSettings.max) / 2;
    private FloatControl soundVolume;

    /**
     * <p>Constructor for Sound.</p>
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/sound/audio_thiseas.wav");
        soundURL[1] = getClass().getResource("/sound/sound_effect.wav");
        soundURL[2] = getClass().getResource("/sound/Coin_1.wav");
        soundURL[3] = getClass().getResource("/sound/win_sound.wav");
        soundURL[4] = getClass().getResource("/sound/swoosh.wav");
        soundURL[5] = getClass().getResource("/sound/death_sound.wav");

        SoundSettings.sliderAddChangeListener(this);
    }

    /**
     * <p>setFile.</p>
     *
     * @param i an int
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            soundVolume =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            soundVolume.setValue(volume);// Set initial volume

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>play clip.</p>
     */
    public void play() {
        active = true;
        clip.start();
    }

    /**
     * <p>loop clip.</p>
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * <p>Getter for the field <code>active</code>.</p>
     *
     * @return a boolean
     */
    public boolean isPlaying() {
        return active;
    }

    /**
     * <p>stop clip.</p>
     */
    public void stop() {
        active = false;
        clip.stop();
    }

    /**
     * <p>flush clip.</p>
     */
    public void flush() {
        clip.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (soundVolume == null) {
            return;
        }
        if (!SoundSettings.sliderIsAdjusting()) {
            volume = SoundSettings.getSliderValue();
            soundVolume.setValue(volume);
        }
    }

}

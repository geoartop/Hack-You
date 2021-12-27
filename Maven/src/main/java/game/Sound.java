package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * <p>Αναπαραγωγή ήχου και ηχητικών εφέ</p>
 * TODO(all) Προσθήκη death sound
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Sound {

    private Clip clip;
    private final URL[] soundURL = new URL[7];
    private boolean active = false;

    /**
     * <p>Constructor for Sound.</p>
     */
    public Sound() {
        soundURL[0] = getClass().getResource("/sound/audio_thiseas.wav");
        //soundURL[0] = getClass().getResource("/sound/ymnos.wav");
        soundURL[1] = getClass().getResource("/sound/sound_effect.wav");
        soundURL[2] = getClass().getResource("/sound/Coin_1.wav");
        soundURL[3] = getClass().getResource("/sound/victory.wav");
        soundURL[4] = getClass().getResource("/sound/swoosh.wav");
        soundURL[5] = getClass().getResource("/sound/death_sound.wav");
        soundURL[6] = getClass().getResource("/sound/end.wav");
    }

    /**
     * <p>setFile.</p>
     *
     * @param i a int
     */
    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>play clip</p>
     */
    public void play() {
        active = true;
        clip.start();
    }

    /**
     * <p>loop clip</p>
     */
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * <p>isPlaying.</p>
     *
     * @return a boolean
     */
    public boolean isPlaying() {
        return active;
    }

    /**
     * <p>stop clip</p>
     */
    public void stop() {
        active = false;
        clip.stop();
    }

}

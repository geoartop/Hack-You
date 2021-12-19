package game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

/**
 * Αναπαραγωγή ήχου και ηχητικών εφέ
 * TODO(Emmanouil Dellatolis) Προσθήκη Coin Sound Effect
 */
public final class Sound {

    Clip clip;
    private final URL[] soundURL = new URL[3];

    public Sound() {
        soundURL[0] = getClass().getResource("/sound/audio_thiseas.wav");
        //soundURL[1] = getClass().getResource("/sound/click-3.wav");
        soundURL[1] = getClass().getResource("/sound/sound_effect.wav");
        soundURL[2] = getClass().getResource("/sound/Coin_1.wav");
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

}

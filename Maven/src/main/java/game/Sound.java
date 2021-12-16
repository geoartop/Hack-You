package game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Αναπαραγωγή ήχου και ηχητικών εφέ
 */
public class Sound {

    Clip clip;
    URL[] soundURL = new URL[2];

    public Sound() {
        soundURL[0] = getClass().getResource("/audio_thiseas.wav");
        soundURL[1] = getClass().getResource("/sound_effect.wav");
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

    public void stop(){
        clip.stop();
    }

}

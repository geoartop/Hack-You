package game;

import com.google.common.annotations.VisibleForTesting;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

/**
 * <p>SoundSettings class.</p>
 *
 * @author panagiotis
 * @version 1.0
 */
public final class SoundSettings extends UtilityFrame implements ActionListener {

    private static final JSlider slider = new JSlider(-30, 6, (-30 + 6) / 2);
    /**
     * <code>slider</code> minimum value
     */
    static final int min = slider.getMinimum();
    /**
     * <code>slider</code> maximum value
     */
    static final int max = slider.getMaximum();
    /**
     * Προσδιορισμός για τον αν παίζεται μουσική <br>
     * στο παιχνίδι ή όχι ώστε να απεικονιστεί η κατάσταση ήχου στο κουμπί
     */
    private final JButton musicOn_Off = new JButton(String.format("Sound %s",
            ButtonSetter.getPlaySound() ? "off" : "on"));
    private final static int WIDTH = 320;
    private final static int HEIGHT = 200;

    /**
     * <p>Constructor for SoundSettings.</p>
     *
     * @param menu a {@link game.Menu} object
     */
    public SoundSettings(Menu menu) {
        super("Sound Settings", WIDTH, HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setSoundStatus(true);
                SoundSettings.super.closeFrame();
                if (ButtonSetter.getPlaySound()) {
                    Menu.continuePlaying();
                }
            }
        });
        setup();


    }

    /**
     * <p>setup.</p>
     */
    private void setup() {
        Menu.stopMusic();
        slider.setBounds(0, 0, 300, 100);
        slider.setOpaque(false);
        slider.setEnabled(true);

        slider.setEnabled(ButtonSetter.getPlaySound());

        frame.add(slider);
        ButtonSetter.setButton(musicOn_Off, 50, 100, 200, 50, Main.mainFont, this);
        frame.add(musicOn_Off);
        frame.add(backgroundLabel);

    }

    /**
     * <p>Constructor for SoundSettings.</p>
     *
     * @param options a {@link game.Options} object
     */
    public SoundSettings(Options options) {
        super("Sound Settings", WIDTH, HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                options.setSoundStatus(true);
                SoundSettings.super.closeFrame();
            }
        });
        setup();
    }

    /**
     * <p>Setter for the field's <code>slider</code> value .</p>
     *
     * @param value an int
     */
    @VisibleForTesting
    public static void setSliderValue(int value) {
        slider.setValue(value);
    }

    /**
     * <p>Getter for the field's <code>slider</code> value .</p>
     *
     * @return an int
     */
    static int getSliderValue() {
        return slider.getValue();
    }

    /**
     * <p>Check if slider is still adjusting.</p>
     *
     * @return a boolean
     */
    static boolean sliderIsAdjusting() {
        return slider.getValueIsAdjusting();
    }

    /**
     * <p>add ChangeListener to slider.</p>
     *
     * @param changeListener a {@link ChangeListener} object
     */
    static void sliderAddChangeListener(ChangeListener changeListener) {
        slider.addChangeListener(changeListener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == musicOn_Off) {
            ButtonSetter.setPlaySound(!ButtonSetter.getPlaySound());
            slider.setEnabled(ButtonSetter.getPlaySound());
            musicOn_Off.setText(String.format("Sound %s", ButtonSetter.getPlaySound() ? "off" : "on"));
        }

    }

}

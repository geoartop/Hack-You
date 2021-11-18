import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Panagiotis Spanakis kai synergates
 *
 * Κλάση όπου θα τρέχει ο λαβίρυνθος
 */
public class Labyrinth implements KeyListener {

    //JFrame frame; ΙΣΩΣ!
    //protected static File file;

    public static void setLabyrinth(){
        switch (Levels.difficulty) {
            case "easy":
                //file=EasyLabyrinth.txt;
                break;
            case "medium":
                //file=MediumLabyrinth.txt;
                break;
            default:
                //file=HardLabyrinth.txt;
                break;
        }

    }

    public Labyrinth(){

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

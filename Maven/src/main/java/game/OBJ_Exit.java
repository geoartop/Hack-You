package game;

/**
 * Αντικείμενο Εξόδου στο παιχνίδι
 */
public class OBJ_Exit extends SuperObject {
    public OBJ_Exit() {
        super("/icons/exit.png");
        name = "Exit";
        collision = false;
    }
}

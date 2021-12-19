package game;

/**
 * Αντικείμενο Εξόδου στο παιχνίδι
 */
public class OBJ_Exit extends SuperObject {

    public OBJ_Exit() {
        super("/icons/exit.png", 48);
        name = "Exit";
        collision = false;
    }

}

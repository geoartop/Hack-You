package game;

/**
 * Αντικείμενο Εξόδου στο παιχνίδι
 */
public class OBJ_Exit extends SuperObject {

    //private Sound se = new Sound();

    public OBJ_Exit() {
        super("/icons/exit.png", 48);
        name = "Exit";
        collision = false;
        //se.setFile(3);
    }

    /*public void playSE(){
        se.play();
    }*/

}

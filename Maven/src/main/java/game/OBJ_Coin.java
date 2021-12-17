package game;
/**
 * Αντικείμενο coin στο παιχνίδι
 */
public class OBJ_Coin extends SuperObject {

    private Sound se = new Sound();

    public OBJ_Coin() {
        super("/icons/coin.png",30);
        name = "Coin";
        collision = false;
        //se.setFile(2);
    }

    public void playSE(){
        se.play();
    }
}
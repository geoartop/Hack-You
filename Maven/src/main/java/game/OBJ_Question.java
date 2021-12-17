package game;

/**
 * Αντικείμενο ερώτησης στο παιχνίδι
 */
public class OBJ_Question extends SuperObject {

    public OBJ_Question() {
        super("/icons/qmark.png",48);
        name = "Question";
        collision = true;
    }
}

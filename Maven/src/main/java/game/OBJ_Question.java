package game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Question extends SuperObject{

    public OBJ_Question() {
        name = "Question mark";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/icons/qmark.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

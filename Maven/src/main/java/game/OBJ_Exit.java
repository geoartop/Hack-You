package game;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Exit extends SuperObject{
    public OBJ_Exit() {
            name = "Exit";
            try {
                image = ImageIO.read(getClass().getResourceAsStream("/icons/exit.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            collision = false;
    }
}

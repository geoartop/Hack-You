package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * <p> Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class AssetSetter {

    private final GamePanel gp;
    private int index = -1;

    /**
     * <p>Constructor for AssetSetter.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * <p>addElement and set its position.</p>
     *
     * @param element a {@link SuperObject} object
     * @param x       an int
     * @param y       an int
     */
    private void addElement(SuperObject element, int x, int y) {
        gp.obj.add(element);
        index++;
        gp.obj.get(index).setWorldX(x * GamePanel.tileSize);
        gp.obj.get(index).setWorldY(y * GamePanel.tileSize);
    }

    /**
     * <p>addSpikes horizontally or vertically.</p>
     *
     * @param x            an int
     * @param y            an int
     * @param isHorizontal a boolean
     */
    private void addSpikes(int x, int y, boolean isHorizontal) {
        addElement(new OBJ_Spikes(index), x, y);
        if (isHorizontal) {
            addElement(new OBJ_Spikes(index - 1), x + 1, y);
        } else {
            addElement(new OBJ_Spikes(index - 1), x, y + 1);
        }
    }

    /**
     * <p>addCoins horizontally or vertically.</p>
     *
     * @param x            an int
     * @param y            an int
     * @param isHorizontal a boolean
     */
    private void addCoins(int x, int y, boolean isHorizontal) {
        addElement(new OBJ_Coin(), x, y);
        if (isHorizontal) {
            addElement(new OBJ_Coin(), x + 1, y);
        } else {
            addElement(new OBJ_Coin(), x, y + 1);
        }
    }

    /**
     * <p>Add objects to map according to difficulty.</p>
     * Id specifications for objects :
     * <p>0 questions</p>
     * <p>1 spikes</p>
     * <p>2 coins</p>
     * <p>3 exit</p>
     */
    void load() {
        InputStream is = getClass().getResourceAsStream(String.format("/objects/%s%d.txt", Levels.getDifficulty(), TileManager.getLevel()));
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(is),
                        StandardCharsets.UTF_8))) {

            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] details = currentLine.split(",");

                int id = Integer.parseInt(details[0]);

                int x = Integer.parseInt(details[1]);
                int y = Integer.parseInt(details[2]);

                if (id == 0) {
                    addElement(new OBJ_Question(), x, y);
                } else if (id == 1) {
                    boolean isHorizontal = Boolean.parseBoolean(details[3]);
                    addSpikes(x, y, isHorizontal);
                } else if (id == 2) {
                    boolean isHorizontal = Boolean.parseBoolean(details[3]);
                    addCoins(x, y, isHorizontal);
                } else {
                    addElement(new OBJ_Exit(), x, y);
                }

                currentLine = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

package game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

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
     * <p>addElement and set its position</p>
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
     * <li>0 questions</li>
     * <li>1 spikes</li>
     * <li>2 coins</li>
     * <li>3 exit</li>
     */
    private void load() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(String.format("src/main/resources/objects/%s%d.txt", Levels.getDifficulty(), TileManager.getLevel())),
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


    /*private void easyObjects() {
        load();
        if (TileManager.getLevel() == 1) {
            addElement(new OBJ_Question(), 2, 6);
            addSpikes(2, 7, true);
            addCoins(2, 8, true);
            addElement(new OBJ_Question(), 9, 6);
            addSpikes(8, 5, true);
            addElement(new OBJ_Question(), 2, 13);
            addSpikes(2, 14, true);
            addElement(new OBJ_Question(), 7, 12);
            addSpikes(8, 12, false);
            addElement(new OBJ_Question(), 10, 16);
            addSpikes(9, 16, false);
            addElement(new OBJ_Question(), 9, 22);
            addSpikes(8, 22, false);
            addElement(new OBJ_Question(), 12, 24);
            addSpikes(13, 24, false);
            addElement(new OBJ_Question(), 20, 24);
            addSpikes(21, 24, false);
            addElement(new OBJ_Question(), 23, 3);
            addSpikes(22, 2, false);
            addElement(new OBJ_Question(), 16, 14);
            addSpikes(16, 15, true);
            addElement(new OBJ_Question(), 25, 14);
            addSpikes(24, 13, true);
            addElement(new OBJ_Question(), 21, 19);
            addSpikes(20, 18, true);
            addCoins(2, 18, true);
            addCoins(6, 18, true);
            addCoins(10, 20, true);
            addCoins(24, 18, true);
            addCoins(16, 12, true);
            addCoins(24, 6, true);
            addCoins(5, 24, false);
            addCoins(10, 8, false);
            addCoins(14, 4, false);
            addCoins(18, 2, false);
            addCoins(21, 6, false);
            addCoins(20, 10, false);
            addCoins(5, 24, false);
            addCoins(22, 14, false);
            addCoins(16, 22, false);
            addElement(new OBJ_Exit(), 27, 24);
        } else {
            System.out.println("Nothing yet");
        }
    }


    private void mediumObjects() {
        if (TileManager.getLevel() == 1) {
            load();
            addElement(new OBJ_Question(), 4, 3);
            addSpikes (5, 2, false);
            addElement(new OBJ_Question(), 4, 9);
            addSpikes (5, 8, false);
            addElement(new OBJ_Question(), 2, 10);
            addSpikes (2, 11, true);
            addElement(new OBJ_Question(), 2, 18);
            addSpikes (2, 19, true);
            addElement(new OBJ_Question(), 8, 17);
            addSpikes (8, 18, true);
            addElement(new OBJ_Question(), 3, 31);
            addSpikes (4, 30, false);
            addElement(new OBJ_Question(), 8, 27);
            addSpikes (9, 26, false);
            addElement(new OBJ_Question(), 15, 27);
            addSpikes (14, 28, true);
            addElement(new OBJ_Question(), 16, 3);
            addSpikes (17, 2, false);
            addElement(new OBJ_Question(), 14, 6);
            addSpikes (15, 6, false);
            addElement(new OBJ_Question(), 12, 12);
            addSpikes (11, 12, false);
            addElement(new OBJ_Question(), 18, 16);
            addSpikes (17, 16, false);
            addElement(new OBJ_Question(), 17, 22);
            addSpikes (16, 22, false);
            addElement(new OBJ_Question(), 19, 24);
            addSpikes (18, 25, true);
            addElement(new OBJ_Question(), 22, 15);
            addSpikes (22, 14, true);
            addElement(new OBJ_Question(), 24, 9);
            addSpikes (25, 8, false);
            addElement(new OBJ_Question(), 27, 4);
            addSpikes (28, 4, false);
            addElement(new OBJ_Question(), 29, 17);
            addSpikes (28, 16, false);
            addElement(new OBJ_Question(), 31, 18);
            addSpikes (30, 19, true);
            addElement(new OBJ_Question(), 29, 26);
            addSpikes (28, 26, false);
            addElement(new OBJ_Question(), 25, 30);
            addSpikes (26, 30, false);
            addCoins  (4, 14, true);
            addCoins  (2, 20, true);
            addCoins  (8, 19, true);
            addCoins  (10, 25, true);
            addCoins  (14, 21, true);
            addCoins  (20, 5, true);
            addCoins  (22, 12, true);
            addCoins  (22, 18, true);
            addCoins  (26, 24, true);
            addCoins  (30, 20, true);
            addCoins  (30, 12, true);
            addCoins  (30, 7, true);
            addCoins  (30, 2, true);
            addCoins  (8, 4, false);
            addCoins  (8, 8, false);
            addCoins  (9, 12, false);
            addCoins  (18, 12, false);
            addCoins  (21, 8, false);
            addCoins  (16, 16, false);
            addCoins  (16, 30, false);
            addCoins  (20, 26, false);
            addCoins  (28, 30, false);
            addCoins  (5, 30, false);
            addElement(new OBJ_Exit(), 33, 30);
        } else {
            System.out.println("Nothing yet");
        }
    }


    private void hardObjects() {
        if (TileManager.getLevel() == 1) {
            addElement(new OBJ_Question(), 2, 5);
            addSpikes(2, 6, true);
            addElement(new OBJ_Question(), 4, 3);
            addSpikes(5, 2, false);
            addElement(new OBJ_Question(), 8, 11);
            addSpikes(9, 10, false);
            addElement(new OBJ_Question(), 9, 17);
            addSpikes(8, 16, false);
            addElement(new OBJ_Question(), 2, 19);
            addSpikes(2, 18, true);
            addElement(new OBJ_Question(), 2, 22);
            addSpikes(2, 23, true);
            addElement(new OBJ_Question(), 7, 27);
            addSpikes(6, 26, true);
            addElement(new OBJ_Question(), 9, 34);
            addSpikes(8, 34, false);
            addElement(new OBJ_Question(), 12, 39);
            addSpikes(13, 38, false);
            addElement(new OBJ_Question(), 18, 32);
            addSpikes(18, 33, true);
            addElement(new OBJ_Question(), 19, 24);
            addSpikes(18, 23, true);
            addElement(new OBJ_Question(), 15, 4);
            addSpikes(14, 5, true);
            addElement(new OBJ_Question(), 16, 2);
            addSpikes(17, 2, false);
            addElement(new OBJ_Question(), 23, 9);
            addSpikes(22, 8, true);
            addElement(new OBJ_Question(), 22, 14);
            addSpikes(21, 14, false);
            addElement(new OBJ_Question(), 23, 22);
            addSpikes(22, 23, true);
            addElement(new OBJ_Question(), 25, 34);
            addSpikes(24, 34, false);
            addElement(new OBJ_Question(), 28, 32);
            addSpikes(29, 32, false);
            addElement(new OBJ_Question(), 28, 24);
            addSpikes(29, 24, false);
            addElement(new OBJ_Question(), 26, 12);
            addSpikes(26, 13, true);
            addElement(new OBJ_Question(), 31, 2);
            addSpikes(30, 2, false);
            addElement(new OBJ_Question(), 36, 2);
            addSpikes(37, 2, false);
            addElement(new OBJ_Question(), 32, 14);
            addSpikes(33, 14, false);
            addElement(new OBJ_Question(), 39, 14);
            addSpikes(38, 15, true);
            addElement(new OBJ_Question(), 36, 24);
            addSpikes(37, 24, false);
            addElement(new OBJ_Question(), 34, 27);
            addSpikes(34, 28, true);
            addElement(new OBJ_Question(), 35, 38);
            addSpikes(34, 37, true);
            addElement(new OBJ_Question(), 39, 36);
            addSpikes(38, 37, true);
            addCoins(7, 2, false);
            addCoins(2, 8, true);
            addCoins(11, 6, false);
            addCoins(13, 12, false);
            addCoins(18, 7, true);
            addCoins(2, 15, true);
            addCoins(6, 19, true);
            addCoins(2, 29, true);
            addCoins(2, 35, true);
            addCoins(6, 36, true);
            addCoins(14, 36, true);
            addCoins(18, 36, true);
            addCoins(14, 30, true);
            addCoins(12, 24, false);
            addCoins(12, 20, false);
            addCoins(16, 16, false);
            addCoins(26, 16, true);
            addCoins(26, 6, false);
            addCoins(26, 20, false);
            addCoins(27, 2, false);
            addCoins(38, 5, true);
            addCoins(34, 12, true);
            addCoins(36, 18, false);
            addCoins(38, 22, true);
            addCoins(38, 27, true);
            addCoins(30, 24, false);
            addCoins(31, 28, false);
            addCoins(34, 29, true);
            addCoins(22, 32, true);
            addCoins(23, 38, false);
            addCoins(30, 35, true);
            addCoins(18, 22, true);
            addCoins(36, 34, false);
            addElement(new OBJ_Exit(), 41, 38);
        } else {
            System.out.println("Nothing yet");
        }
    }*/

    /**
     * <p>setObjects according to difficulty.</p>
     */
    public void setObject() {
        load();
    }

}

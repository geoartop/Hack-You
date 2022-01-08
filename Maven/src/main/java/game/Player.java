package game;

import javax.swing.SwingUtilities;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * <p>Φόρτωση του παίκτη και εγγραφή των κινήσεών του στην οθόνη</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see Entity
 */
public class Player extends Entity {

    private int worldx, worldy;
    static final int speed = 2;

    private final GamePanel gp;
    private final KeyHandler keyH;
    private final int screenX;
    private final int screenY;
    private static boolean hasLoaded;

    /**
     * Coins collected from player
     */
    private static int coinsCollected = 0;

    /**
     * Κινήσεις animation ήττας παίκτη
     */
    private int timesPassed = 0;

    private String direction;

    //Μεταβλητές παίκτη
    private int spriteCounter = 0;
    private int spriteNum = 1;
    final Rectangle solidArea = new Rectangle(8, 32, 32, 16);
    final int solidAreaDefaultX = solidArea.x;
    final int solidAreaDefaultY = solidArea.y;

    private boolean collisionOn = false;

    private static final int outOfBoundsLimit = 15;

    /**
     * <p>Getter for the field <code>coinsCollected</code>.</p>
     *
     * @return an int
     */
    public static int getCoinsCollected() {
        return coinsCollected;
    }

    /**
     * <p>Restores <code>coinsCollected</code> value.</p>
     */
    public static void restoreCoinsCollected() {
        coinsCollected = 0;
    }

    /**
     * <p>Constructor for Player.</p>
     *
     * @param gp   a {@link game.GamePanel} object
     * @param keyH a {@link game.KeyHandler} object
     */
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = GamePanel.screenWidth / 2 - (GamePanel.tileSize / 2);
        screenY = GamePanel.screenHeight / 2 - (GamePanel.tileSize / 2);

        setDefaultValues();

        if (!hasLoaded) {
            super.getImages();
            hasLoaded = true;
        }
    }

    /**
     * <p>Καθορισμός αρχικής θέσης παίκτη</p>
     */
    private void setDefaultValues() {
        worldx = 100;
        worldy = 50;
        direction = "down";
    }

    /**
     * <p>Ανανέωση κίνησης παίκτη</p>
     */
    public void update() {
        if (keyH.keyIsPressed()) {
            if (keyH.getUpPressed()) {
                direction = "up";
            } else if (keyH.getDownPressed()) {
                direction = "down";
            } else if (keyH.getLeftPressed()) {
                direction = "left";
            } else {
                direction = "right";
            }

            collisionOn = false;
            gp.collisionCheck.checkTile(this);
            int objIndex = gp.collisionCheck.checkObject(this, true);
            interact(objIndex);

            //If collision is false only then can player move on
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        //Εξασφαλίζει ότι ο παίκτης δε θα βγει out of bounds
                        if (worldy < outOfBoundsLimit) {
                            break;
                        }
                        worldy -= speed;
                        break;
                    case "down":
                        worldy += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;
                    default:
                        worldx += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 5) {
                if (spriteNum < 9) {
                    spriteNum++;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    /**
     * <p>Σταθεροποίηση κίνησης παίκτη</p>
     */
    void stabilizePlayer() {
        keyH.stopMovement();
    }

    /**
     * <p>Διαχείριση interactions του παίκτη με αντικείμενα μέσα στο παιχνίδι</p>
     *
     * @param index θέση του παίκτη στον χάρτη
     */
    private void interact(int index) {
        if (index != 999) {
            String objectName = gp.obj.get(index).name;

            if (ButtonSetter.getPlaySound()) {
                gp.obj.get(index).playSE();
            }

            if (Objects.equals(objectName, "Question")) {
                //Για να μην κολλήσει το progressBar και η ροή του παιχνιδιού
                gp.labyrinthFrame.stopBar();
                gp.setGameState(GamePanel.pauseState);
                gp.keyH.setQuizTrig(true);

                SwingUtilities.invokeLater(() -> new Quiz(gp));
                gp.obj.set(index, null);

            }
            //Τερματισμός παιχνιδιού σε περίπτωση νίκης
            if (Objects.equals(objectName, "Exit")) {
                gp.setGameState(GamePanel.endState);
            }
            //Προσθήκη χρόνου (ίσως και πόντων) όταν ο παίκτης βρίσκει coins
            if (Objects.equals(objectName, "Coin")) {
                coinsCollected++;
                gp.obj.set(index, null);
            }

        }
    }

    /**
     * <p>Απεικόνιση "θανάτου" παίκτη</p>
     *
     * @param g2 a {@link java.awt.Graphics2D} object
     */
    public void drawDeathAnimation(Graphics2D g2) {
        BufferedImage image;
        image = death[timesPassed];
        setValues(g2, image);

    }

    /**
     * <p>setValues.</p>
     *
     * @param g2    a {@link Graphics2D} object
     * @param image a {@link BufferedImage} object
     */
    private void setValues(Graphics2D g2, BufferedImage image) {
        int x1 = screenX;
        int y1 = screenY;

        if (screenX > worldx) {
            x1 = worldx;
        }
        if (screenY > worldy) {
            y1 = worldy;
        }
        int rightOffsetValue1 = GamePanel.screenWidth - screenX;

        if (rightOffsetValue1 > gp.WorldWidth - worldx) {
            x1 = GamePanel.screenWidth - (gp.WorldWidth - worldx);
        }

        int bottomOffsetValue1 = GamePanel.screenHeight - screenY;

        if (bottomOffsetValue1 > gp.WorldHeight - worldy) {
            y1 = GamePanel.screenHeight - (gp.WorldHeight - worldy);
        }

        g2.drawImage(image, x1, y1, null);
    }


    /**
     * <p>draw.</p>
     *
     * @param g2 a {@link java.awt.Graphics2D} object
     */
    public void draw(Graphics2D g2) {

        if (gp.labyrinthFrame.getHasLost()) {
            drawDeathAnimation(g2);
            timesPassed++;
            return;
        }

        BufferedImage image;

        switch (direction) {
            case "up":
                image = up[spriteNum - 1];
                break;
            case "down":
                image = down[spriteNum - 1];
                break;
            case "left":
                image = left[spriteNum - 1];
                break;
            default:
                image = right[spriteNum - 1];
                break;
        }

        setValues(g2, image);
    }

    /**
     * <p>Getter for the field <code>worldx</code>.</p>
     *
     * @return an int
     */
    public int getWorldx() {
        return worldx;
    }

    /**
     * <p>Getter for the field <code>worldy</code>.</p>
     *
     * @return an int
     */
    public int getWorldy() {
        return worldy;
    }

    /**
     * <p>Getter for the field <code>direction</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getDirection() {
        return direction;
    }

    /**
     * <p>Setter for the field <code>collisionOn</code>.</p>
     *
     * @param collisionOn a boolean
     */
    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    /**
     * <p>Getter for the field <code>screenX</code>.</p>
     *
     * @return an int
     */
    public int getScreenX() {
        return screenX;
    }

    /**
     * <p>Getter for the field <code>screenY</code>.</p>
     *
     * @return an int
     */
    public int getScreenY() {
        return screenY;
    }
}

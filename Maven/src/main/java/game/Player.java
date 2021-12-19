package game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.util.Objects;

/**
 * Φόρτωση του παίκτη και εγγραφή των κινήσεών του στην οθόνη
 */
public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler keyH;
    protected final int screenX;
    protected final int screenY;
    private static boolean hasLoaded;

    private int timesPassed = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 16;

        setDefaultValues();

        if (!hasLoaded) {
            getImage();
            hasLoaded = true;
        }
    }

    /**
     * Καθορισμός αρχικής θέσης παίκτη
     */
    private void setDefaultValues() {
        worldx = 100;
        worldy = 50;
        speed = 2;
        direction = "down";
    }

    /**
     * Ανανέωση κίνησης παίκτη
     */
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
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
                        if (worldy < 15)
                            break;
                        worldy -= speed;
                        break;
                    case "down":
                        //if (y < 520)
                        worldy += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;
                    case "right":
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
     * Σταθεροποίηση κίνησης παίκτη
     */
    public void stabilizePlayer() {
        keyH.upPressed = false;
        keyH.downPressed = false;
        keyH.rightPressed = false;
        keyH.leftPressed = false;
    }

    /**
     * Διαχείριση interactions του παίκτη με αντικείμενα μέσα στο παιχνίδι
     *
     * @param index θέση του παίκτη στον χάρτη
     */
    private void interact(int index) {
        if (index != 999) {
            String objectName = gp.obj.get(index).name;
            if (Objects.equals(objectName, "Question")) {
                //Για να μην κολλήσει το progressBar και η ροή του παιχνιδιού
                //stabilizePlayer();
                gp.labyrinthFrame.stopBar();
                gp.gameState = gp.pauseState;
                gp.keyH.quizTrig = true;

                SwingUtilities.invokeLater(() -> new Quiz(gp));
                gp.obj.set(index, null);

            }
            //Τερματισμός παιχνιδιού σε περίπτωση νίκης TODO(all) ίσως προσθήκη νικητήριου ήχου
            if (Objects.equals(objectName, "Exit"))
                gp.gameState = gp.endState;
            //Προσθήκη χρόνου (ίσως και πόντων) όταν ο παίκτης βρίσκει coins
            if (Objects.equals(objectName, "Coin")) {
                if(!ButtonSetter.playSound) {
                    OBJ_Coin coin = (OBJ_Coin) gp.obj.get(index);
                    //coin.playSE();
                }
                gp.labyrinthFrame.editBarTime(LabyrinthFrame.for_correct);
                gp.obj.set(index, null);
            }

        }
    }

    /**
     * Απεικόνιση "θανάτου" παίκτη
     */
    public void drawDeathAnimation(Graphics2D g2) {
        BufferedImage image;
        image = death[timesPassed];
        setValues(g2, image);

    }

    private void setValues(Graphics2D g2, BufferedImage image) {
        int x1 = screenX;
        int y1 = screenY;

        if (screenX > worldx) {
            x1 = worldx;
        }
        if (screenY > worldy) {
            y1 = worldy;
        }
        int rightOffsetValue1 = gp.screenWidth - screenX;

        if (rightOffsetValue1 > gp.WorldWidth - worldx) {
            x1 = gp.screenWidth - (gp.WorldWidth - worldx);
        }

        int bottomOffsetValue1 = gp.screenHeight - screenY;

        if (bottomOffsetValue1 > gp.WorldHeight - worldy) {
            y1 = gp.screenHeight - (gp.WorldHeight - worldy);
        }

        g2.drawImage(image, x1, y1, null);
    }


    public void draw(Graphics2D g2) {

        if (gp.labyrinthFrame.hasLost) {
            drawDeathAnimation(g2);
            timesPassed++;
            return;
        }

        BufferedImage image = null;

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
            case "right":
                image = right[spriteNum - 1];
                break;
        }

        setValues(g2, image);
    }

}
package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Avatar3 extends JFrame implements KeyListener {
    private int x = 100;
    private int y = 100;
    private int speed = 4;
    BufferedImage image;
    private String direction = "down";
    public BufferedImage[] up = new BufferedImage[9];
    public BufferedImage[] down = new BufferedImage[9];
    public BufferedImage[] left = new BufferedImage[9];
    public BufferedImage[] right = new BufferedImage[9];
    private int c1 = 0, c2 = 0, c3 = 0, c4 = 0;

    public void getimage() {
        try {
            setMovement(up, "thiseaswalkingup");
            setMovement(down, "thiseaswalkingdown");
            setMovement(right, "thiseaswalkingright");
            setMovement(left, "thiseaswalkingleft");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMovement(BufferedImage[] images, String move) throws IOException {
        for (int i = 0; i < images.length; i++)
            images[i] = ImageIO.read((this.getClass()
                    .getClassLoader().getResource(String.format("src/main/resources/thiseas2/%s%d.png", move, i + 1))));
    }


    public Avatar3() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
        getimage();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                direction = "left";
                x -= speed;
                break;
            case 'w':
                direction = "up";
                y -= speed;
                break;
            case 'd':
                direction = "right";
                x += speed;
                break;
            case 's':
                direction = "down";
                y += speed;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("you released char" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case 'a':
                c1 = c1 + 1;
                if (c1 > 9) {
                    c1 = 0;
                    break;
                }
                image = left[c1 - 1];
                x -= speed;
                //repaint();
                break;
            case 'w':
                c2 = c2 + 1;
                if (c2 > 9) {
                    c2 = 0;
                    break;
                }
                image = up[c2 - 1];
                y -= speed;
                //repaint();
                break;
            case 'd':
                c3 = c3 + 1;
                if (c3 > 9) {
                    c3 = 0;
                    break;
                }
                image = right[c3 - 1];
                x += speed;
                //repaint();
                break;
            case 's':
                c4 = c4 + 1;
                if (c4 > 9) {
                    c4 = 0;
                    break;
                }
                image = down[c4 - 1];
                y += speed;
                //repaint();
                break;
        }
        repaint();
    }
}
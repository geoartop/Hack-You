import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class avatar {
    /* 1ος τροπος για αβαταρ κινηση/ animation (καλυτετρος αλλα μου πεταει λαθος στο Buffered image = null; και δεν ξερω πως
να το βγαλω δεν εχει ολες τις εντολες για το animasion μεσα)
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Myframekeys extends JFrame implements KeyListener {
    private int x=100;
    private int y=100;
    private int speed= 4;
    BufferedImage image;
    private String direction = "down";
    public BufferedImage down1, down2, down3, down4,down5,down6,down7,down8,down9,left1,right1,up1;
    public void getimage(){
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingup1.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown1.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingright1.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingleft1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown2.png"));
            down3= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown3.png"));
            down4= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown4.png"));
            down5= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown5.png"));
            down6= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown6.png"));
            down7= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown7.png"));
            down8= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown8.png"));
            down9= ImageIO.read(getClass().getResourceAsStream("thiseas/thiseaswalkingdown9.png"));


        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    JLabel label;
    public Myframekeys()  {
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setSize(500,500);
         this.setLayout(null);
         this.addKeyListener(this);
         this.getContentPane().setBackground(Color.black);
         this.setVisible(true);
         getimage();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
                direction="left";
                x-=speed;

            break;
            case 'w':
                direction="up";
                y-=speed;
            break;
            case 'd':
                direction="right";
                x+=speed;
            break;
            case 's':
                direction="down";
                y+=speed;
            break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("you released char"+ e.getKeyChar());
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                image=up1;
                break;
            case "down":
                image=down1;
                break;
            case "right":
                image=right1;
                break;
            case "left":
                image=left1;
                break;
        }
        g2.drawImage(image,x,y,64,65,null);
    }
}
 kai τωρα ο πιο ασχημος κωδικας που εχω φτιαξει στην ζωη μου ο οποιος λειτουγρει απλα πρεπει να αλλαξουμε το path στα files του θησεα

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

    public class Myframekeys2 extends JFrame implements KeyListener {
        private int c1=0;
        private int c2=0;
        private int c3=0;
        private int c4=0;


        public ImageIcon down1, down2, down3, down4,down5,down6,down7,down8,down9,left1,left2,left3,left4, left5,left6,left7,left8,left9,right1,right2,right3,right4;
        public ImageIcon right5,right6,right7,right8,right9,up1,up2,up3,up4,up5,up6,up7,up8,up9;
        JLabel move= new JLabel();

        public avatar()  {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(500,500);
            this.setLayout(null);
            this.addKeyListener(this);
            this.getContentPane().setBackground(new Color(128,128,128));
            this.setVisible(true);
            up1= new ImageIcon("thiseas2/thiseaswalkingup1.png");
            down1= new ImageIcon("thiseas2/thiseaswalkingdown1.png");
            right1= new ImageIcon("thiseas2/thiseaswalkingright1.png");
            left1= new ImageIcon("thiseas2/thiseaswalkingleft1.png");
            down2= new ImageIcon("thiseas2/thiseaswalkingdown2.png");
            down3= new ImageIcon("thiseas2/thiseaswalkingdown3.png");
            down4= new ImageIcon("thiseas2/thiseaswalkingdown4.png");
            down5= new ImageIcon("thiseas2/thiseaswalkingdown5.png");
            down6= new ImageIcon("thiseas2/thiseaswalkingdown6.png");
            down7= new ImageIcon("thiseas2/thiseaswalkingdown7.png");
            down8= new ImageIcon("thiseas2/thiseaswalkingdown8.png");
            down9= new ImageIcon("thiseas2/thiseaswalkingdown9.png");
            up2= new ImageIcon("thiseas2/thiseaswalkingup2.png");
            up3= new ImageIcon("thiseas2/thiseaswalkingup3.png");
            up4= new ImageIcon("thiseas2/thiseaswalkingup4.png");
            up5= new ImageIcon("thiseas2/thiseaswalkingup5.png");
            up6= new ImageIcon("thiseas2/thiseaswalkingup6.png");
            up7= new ImageIcon("thiseas2/thiseaswalkingup7.png");
            up8= new ImageIcon("thiseas2/thiseaswalkingup8.png");
            up9= new ImageIcon("thiseas2/thiseaswalkingup9.png");
            left2= new ImageIcon("thiseas2/thiseaswalkingleft2.png");
            left3= new ImageIcon("thiseas2/thiseaswalkingleft3.png");
            left4= new ImageIcon("thiseas2/thiseaswalkingleft4.png");
            left5= new ImageIcon("thiseas2/thiseaswalkingleft5.png");
            left6= new ImageIcon("thiseas2/thiseaswalkingleft6.png");
            left7= new ImageIcon("thiseas2/thiseaswalkingleft7.png");
            left8= new ImageIcon("thiseas2/thiseaswalkingleft8.png");
            left9= new ImageIcon("thiseas2/thiseaswalkingleft9.png");
            right2= new ImageIcon("thiseas2/thiseaswalkingright2.png");
            right3= new ImageIcon("thiseas2/thiseaswalkingright3.png");
            right4= new ImageIcon("thiseas2/thiseaswalkingright4.png");
            right5= new ImageIcon("thiseas2/thiseaswalkingright5.png");
            right6= new ImageIcon("thiseas2/thiseaswalkingright6.png");
            right7= new ImageIcon("thiseas2/thiseaswalkingright7.png");
            right8= new ImageIcon("thiseas2/thiseaswalkingright8.png");
            right9= new ImageIcon("thiseas2/thiseaswalkingright9.png");




            move.setBounds(100,100,64,65);
            move.setIcon(down1);

            this.add(move);

        }

        @Override
        public void keyTyped(KeyEvent e) {

            switch (e.getKeyChar()) {
                case 'a':
                    c1 = c1 + 1;
                    move.setLocation(move.getX() - 4, move.getY());
                    if (c1 == 1) {
                        move.setIcon(left1);
                    } else if (c1 == 2) {
                        move.setIcon(left2);
                    } else if (c1 == 3) {
                        move.setIcon(left3);
                    } else if (c1 == 4) {
                        move.setIcon(left4);
                    } else if (c1 == 5) {
                        move.setIcon(left5);
                    } else if (c1 == 6) {
                        move.setIcon(left6);
                    } else if (c1 == 7) {
                        move.setIcon(left7);
                    } else if (c1 == 8) {
                        move.setIcon(left8);
                    } else if (c1 == 9) {
                        move.setIcon(left9);
                    } else {
                        c1 = 0;
                    }

                    break;
                case 'w':
                    c2=c2+1;
                    move.setLocation(move.getX(),move.getY()-4);
                    if (c2==1){
                        move.setIcon(up1);
                    } else if (c2==2){
                        move.setIcon(up2);
                    } else if (c2==3){
                        move.setIcon(up3);
                    }else if (c2==4) {
                        move.setIcon(up4);
                    } else if (c2==5){
                        move.setIcon(up5);
                    } else if (c2==6){
                        move.setIcon(up6);
                    } else if (c2==7){
                        move.setIcon(up7);
                    } else  if (c2==8){
                        move.setIcon(up8);
                    } else if (c2==9){
                        move.setIcon(up9);
                    } else {
                        c2 = 0;
                    }

                    break;
                case 'd':
                    move.setLocation(move.getX()+4,move.getY());
                    c3 = c3 + 1;
                    if (c3 == 1) {
                        move.setIcon(right1);
                    } else if (c3 == 2) {
                        move.setIcon(right2);
                    } else if (c3 == 3) {
                        move.setIcon(right3);
                    } else if (c3 == 4) {
                        move.setIcon(right4);
                    } else if (c3 == 5) {
                        move.setIcon(right5);
                    } else if (c3 == 6) {
                        move.setIcon(right6);
                    } else if (c3 == 7) {
                        move.setIcon(right7);
                    } else if (c3 == 8) {
                        move.setIcon(right8);
                    } else if (c3 == 9) {
                        move.setIcon(right9);
                    } else {
                        c3 = 0;
                    }


                    break;
                case 's':
                    c4 = c4 + 1;
                    move.setLocation(move.getX() , move.getY()+4);
                    if (c4 == 1) {
                        move.setIcon(down1);
                    } else if (c4 == 2) {
                        move.setIcon(down2);
                    } else if (c4 == 3) {
                        move.setIcon(down3);
                    } else if (c4 == 4) {
                        move.setIcon(down4);
                    } else if (c4 == 5) {
                        move.setIcon(down5);
                    } else if (c4 == 6) {
                        move.setIcon(down6);
                    } else if (c4 == 7) {
                        move.setIcon(down7);
                    } else if (c4 == 8) {
                        move.setIcon(down8);
                    } else if (c4 == 9) {
                        move.setIcon(down9);
                    } else {
                        c4 = 0;
                    }

                    break;
            }

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("you released char   "+ e.getKeyChar());
        }


    }
 ειναι 5 το πρωι ασχολουμε εεε με αυτο 6 ωρες καληνυχτα  αυριο εχουμε υπνο στις βασεις και τον σπανακη να φωναζει που χανει στο ταβλι*/

}



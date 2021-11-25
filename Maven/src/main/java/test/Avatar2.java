package test;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//Awaiting for g.artop explanation
public class Avatar2 extends JFrame implements KeyListener {
    private int x=100;
    private int y=100;
    private int speed= 4;
    BufferedImage image;
    private String direction = "down";
    private BufferedImage[] up=new BufferedImage[9];
    private BufferedImage[] down=new BufferedImage[9];
    private BufferedImage[] right=new BufferedImage[9];
    private BufferedImage[] left=new BufferedImage[9];

    public void getImage(){
        try{
            setMovement(up,"thiseaswalkingup");
            setMovement(down,"thiseaswalkingdown");
            setMovement(right,"thiseaswalkingright");
            setMovement(left,"thiseaswalkingleft");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    JLabel label;

    public void setMovement(BufferedImage[] bufferedImages,String move) throws IOException {
        for(int i=0;i<bufferedImages.length;i++){
            bufferedImages[i]=ImageIO.read(new File(String.format("src/main/resources/thiseas2/%s%d.png",move,i+1)));
        }
    }

    public Avatar2()  {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
        getImage();
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


    public void draw(Graphics2D g){
        super.paint(g);
        BufferedImage image = null;

        Graphics2D g2D= (Graphics2D) g;
        switch (direction){
            case "up":
                image=up[0];
                break;
            case "down":
                image=down[0];
                break;
            case "right":
                image=right[0];
                break;
            case "left":
                image=left[0];
                break;
        }
        g2D.drawImage(image,x,y,64,65,null);
        g2D.drawImage(up[0],10,10,null );
    }
}

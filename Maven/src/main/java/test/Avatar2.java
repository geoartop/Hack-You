package test;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
//Awaiting for g.artop explanation
public class Avatar2 extends JFrame implements KeyListener {
    private int x=100;
    private int y=100;
    private int speed= 4;
    Image image;
    private String direction = "down";
    private Image[] up=new Image[9];
    private Image[] down=new Image[9];
    private Image[] right=new Image[9];
    private Image[] left=new Image[9];

    private int c1=0,c2=0,c3=0,c4=0;
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

    public void setMovement(Image[] images,String move) throws IOException {
        for(int i=0;i<images.length;i++)
            images[i]=ImageIO.read(new File(String.format("src/main/resources/thiseas2/%s%d.png",move,i+1)));
    }

    public Avatar2()  {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.getContentPane().setBackground(Color.black);
        this.setVisible(true);
        this.setBackground(Color.white);
        getImage();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a':
                c1 = c1 + 1;
                if(c1>9) {
                    c1 = 0;
                    break;
                }
                image=left[c1-1];
                x-=speed;
                //repaint();
                break;
            case 'w':
                c2 = c2 + 1;
                if(c2>9) {
                    c2 = 0;
                    break;
                }
                image=up[c2-1];
                y-=speed;
                //repaint();
                break;
            case 'd':
                c3 = c3 + 1;
                if(c3>9) {
                    c3 = 0;
                    break;
                }
                image=right[c3-1];
                x+=speed;
                //repaint();
                break;
            case 's':
                c4 = c4 + 1;
                if(c4>9) {
                    c4 = 0;
                    break;
                }
                image=down[c4-1];
                y+=speed;
                //repaint();
                break;
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("you released char"+ e.getKeyChar());
    }

    public void paint(Graphics g){
        //super.paint(g);

        Graphics2D g2D= (Graphics2D) g;

        g2D.drawImage(image,x,y,null);
    }


    /*public void draw(Graphics g){
        Image image = null;
        super.paint(g);
        Graphics2D g2D= (Graphics2D) g;
        switch (direction){
            case "up":
                c1 = c1 + 1;
                if(c1>9) {
                    c1 = 0;
                    break;
                }
                image=up[c1-1];
                break;
            case "down":
                c2 = c2 + 1;
                if(c2>9) {
                    c2 = 0;
                    break;
                }
                image=down[c2-1];
                break;
            case "right":
                c3 = c3 + 1;
                if(c3>9) {
                    c3 = 0;
                    break;
                }
                image=right[c3-1];
                break;
            case "left":
                c4 = c4 + 1;
                if(c4>9) {
                    c4 = 0;
                    break;
                }
                image=left[c4-1];
                break;
        }
        g2D.drawImage(image,x,y,64,65,null);
        g2D.drawImage(up[0],10,10,null );
    }*/
}

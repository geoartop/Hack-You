package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Avatar extends JFrame implements KeyListener {
    private int c1=0;
    private int c2=0;
    private int c3=0;
    private int c4=0;
    private int[] c={0,0,0,0};

    private final int speed=4;
    private ImageIcon[] up=new ImageIcon[9];
    private ImageIcon[] down=new ImageIcon[9];
    private ImageIcon[] right=new ImageIcon[9];
    private ImageIcon[] left=new ImageIcon[9];


    JLabel move= new JLabel();
    public Avatar()  {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLayout(null);
        this.addKeyListener(this);
        this.getContentPane().setBackground(new Color(128,128,128));
        this.setVisible(true);
        setMovement(up,"thiseaswalkingup");
        setMovement(down,"thiseaswalkingdown");
        setMovement(right,"thiseaswalkingright");
        setMovement(left,"thiseaswalkingleft");
        move.setBounds(100,100,64,65);
        move.setIcon(down[0]);
        this.add(move);
    }

    public void setMovement(ImageIcon[] imageIcons,String move){
        for(int i=0;i<imageIcons.length;i++){
            imageIcons[i]=new ImageIcon(String.format("src/main/resources/thiseas2/%s%d.png",move,i+1));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (Character.toLowerCase(e.getKeyChar())) {
            case 'a':
            case 'α':
                c1 = c1 + 1;
                move.setLocation(move.getX() - speed, move.getY());
                if(c1>9) {
                    c1 = 0;
                    break;
                }
                move.setIcon(left[c1-1]);
                break;
            case 'w':
            case 'ς':
                c2=c2+1;
                move.setLocation(move.getX(),move.getY()-speed);
                if(c2>9) {
                    c2 = 0;
                    break;
                }
                move.setIcon(up[c2-1]);
                break;
            case 'd':
            case 'δ':
                move.setLocation(move.getX()+speed,move.getY());
                c3 = c3 + 1;
                if(c3>9) {
                    c3 = 0;
                    break;
                }
                move.setIcon(right[c3-1]);
                break;
            case 's':
            case 'σ':
                c4 = c4 + 1;
                move.setLocation(move.getX() , move.getY()+speed);
                if(c4>9) {
                    c4 = 0;
                    break;
                }
                move.setIcon(down[c4-1]);
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

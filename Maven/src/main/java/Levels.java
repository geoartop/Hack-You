import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Επιλογή Επίπεδου δυσκολίας
 *
 * @author Panagiotis Spanakis k synergates
 *
 * */
public class Levels implements ActionListener {

    JFrame frame;
    JButton easy=new JButton("Easy");
    JButton medium=new JButton("Medium");
    JButton hard=new JButton("Hard");
    JLabel label=new JLabel();
    /**Θέλουμε να γνωρίζει η κλάση labyrinth το επίπεδο δυσκολίας που επίλεξε */
    protected static String difficulty;

    public Levels(){
        frame=new JFrame(); //create frame
        frame.setTitle("Select Difficulty"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setLayout(null);

        setButton(easy,250,200,150,50);
        setButton(medium,250,300,150,50);
        setButton(hard,250,400,150,50);

        label.setBounds(0,0,100,50);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Select Difficulty");

        /**Προσθήκη συστατικών*/
        frame.add(easy);
        frame.add(medium);
        frame.add(hard);
        frame.add(label);
    }

    /** Μέθοδος δημιουργίας Κουμπιών*/
    public void setButton(JButton button,int x,int y,int width,int height){
        button.setBounds(x,y,width,height);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
    }

    /**Ενέργεια όταν κάνουμε κλικ στο κουμπί*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==easy){
            //new EasyLabyrinths();
            difficulty="easy";
        }else if(e.getSource()==medium){
            //new MediumLabyrinths();
            difficulty="medium";
        }else {
            //new HardLabyrinths();
            difficulty="hard";
        }
        //Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!
        frame.dispose();
    }
}

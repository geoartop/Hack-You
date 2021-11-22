/**
 * @author Team Hack-You
 * Λειτουργική κλάση για την λειτορυγία των ερωτήσεων
 */

public class Questions {

    //protected static File file;

    protected static void setQuestionsDifficulty(){
        switch (Levels.difficulty) {
            case "easy":
                // JOptionPane.showMessageDialog(null,"1","ferwu",JOptionPane.INFORMATION_MESSAGE);
                //file=EasyQuestions.txt
                break;
            case "medium":
                //file=MediumQuestions.txt
                break;
            default:
                //file=HardQuestions.txt
                break;
        }
    }

    public Questions(){

    }



}

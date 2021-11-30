package highscoreTest;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            HighScore.load();
            boolean checkForHigh = HighScore.checkForNewRegister("Marios", 76);
            HighScore.sort();
            if(checkForHigh){
                System.out.println("Successful new register");
            }else {
                System.out.println("Sorry not good enough");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

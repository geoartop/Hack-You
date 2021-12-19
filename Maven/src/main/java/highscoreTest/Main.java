package highscoreTest;

import game.WinFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        //for ( PlayerInfo playerInfo : HighScore.playerInfo)
        //    System.out.printf("%s : %d%n",playerInfo.getName(),playerInfo.getScore());
        //new HighScore("Athanasia",33);
        SwingUtilities.invokeLater(WinFrame::new);

    }


}

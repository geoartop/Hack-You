package highscoreTest;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Λειτουργική κλάση για την εμφάνιση των top 10 scores μαζί με τα username αντίστοιχα
 */
public class HighScore {

    protected static ArrayList<PlayerInfo> playerInfo = new ArrayList<>();

    public HighScore(String name,int score) {
        try {
            load();
            boolean checkForNewHigh = HighScore.checkForNewRegister(name,score);
            //sort();
            if (checkForNewHigh) {
                sort();
                JOptionPane.showMessageDialog(null, "You managed to set a new HighScore to the highscore table", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean checkForNewRegister(String name, int score) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/HighScore.txt"));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        if (lines < 10) {
            appendScore(name, score);
            playerInfo.add(new PlayerInfo(name, score));
            return true;
        }

        for (PlayerInfo player : playerInfo) {
            if (score > player.getScore()) {
                appendScore(name, score);
                playerInfo.add(new PlayerInfo(name, score));
                return true;
            }
        }
        return false;

    }

    public static void appendScore(String name, int score) {
        FileWriter fileWriter = null;

        BufferedWriter bufferedWriter = null;

        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("src/main/resources/HighScore.txt", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            //Writing text to file με συγκεκριμένη μορφοποίηση
            printWriter.print(String.format("%s %d", name, score));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Closing the resources
            try {
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void load() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/HighScore.txt"));
        String currentLine = reader.readLine();

        while (currentLine != null) {
            String[] playerDetails = currentLine.split(" ");

            String name = playerDetails[0];

            int score = Integer.parseInt(playerDetails[1]);

            //Creating PlayerInfo object for every student record and adding it to ArrayList
            playerInfo.add(new PlayerInfo(name, score));
            currentLine = reader.readLine();
        }
        reader.close();

    }

    public static void sort() throws IOException {
        //Sorting ArrayList playerInfo based on scores
        playerInfo.sort(new scoresCompare());
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/HighScore.txt"));

        int counter = 0;
        //Ξαναδημιουργώ το αρχείο βάζοντας τα playerInfo σε σωστή σειρά και να είναι μέχρι 10
        for (PlayerInfo player : playerInfo) {
            if (counter == 10) {
                playerInfo.remove(counter);
                break;
            }
            writer.write(player.getName());

            writer.write(" " + player.getScore());

            writer.newLine();
            counter++;

        }

        writer.close();
    }

}

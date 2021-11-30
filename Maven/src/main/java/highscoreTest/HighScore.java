package highscoreTest;

import java.io.*;
import java.util.ArrayList;

/**
 * Λειτουργική κλάση για την εμφάνιση των top 10 scores μαζί με τα username αντίστοιχα
 */
public class HighScore {

    protected static ArrayList<PlayerInfo> playerInfo = new ArrayList<>();

    public HighScore() {
        try {
            load();
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
            //Writing text to file
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
        //Creating BufferedReader object to read the input text file

        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/HighScore.txt"));

        //Reading Players records one by one

        String currentLine = reader.readLine();

        while (currentLine != null) {
            String[] studentDetail = currentLine.split(" ");

            String name = studentDetail[0];

            int score = Integer.parseInt(studentDetail[1]);

            //Creating PlayerInfo object for every student record and adding it to ArrayList
            playerInfo.add(new PlayerInfo(name, score));
            currentLine = reader.readLine();
        }
        reader.close();

    }

    public static void sort() throws IOException {

        //Sorting ArrayList playerInfo based on scores

        playerInfo.sort(new scoresCompare());

        //Creating BufferedWriter object to write into output text file

        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/HighScore.txt"));

        int counter = 0;
        for (PlayerInfo player : playerInfo) {
            writer.write(player.getName());

            writer.write(" " + player.getScore());

            writer.newLine();
            counter++;
            if (counter == 10)
                break;
        }

        //Closing the resources
        writer.close();
    }

}

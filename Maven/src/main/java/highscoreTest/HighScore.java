package highscoreTest;

import com.google.common.annotations.VisibleForTesting;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;

/**
 * <p>Λειτουργική κλάση για την εμφάνιση των top 10 scores μαζί με τα username αντίστοιχα</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see PlayerInfo
 */
public final class HighScore {

    private final LinkedList<PlayerInfo> playerInfo = new LinkedList<>();
    private final String name;
    private final int score;
    private static int playerInfoSize;

    /**
     * <p>Getter for <code>playerInfo.get(index)</code>.</p>
     *
     * @param index a int
     * @return a {@link highscoreTest.PlayerInfo} object
     */
    public PlayerInfo getPlayerInfoElement(int index) {
        return playerInfo.get(index);
    }

    /**
     * <p>Κατασκευαστής ο οποίος αρχικά κάνει writable το αρχείο των highscores και μετά την επεξεργασία
     * του αρχείου το κάνει read-only ώστε να μην επιτρέπεται η κακόβουλη αλλαγή του</p>
     *
     * @param name  : όνομα παίκτη
     * @param score : βαθμολογία παίκτη
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
        try {
            setFile(true);
            load();

            boolean checkForNewHigh = checkForNewRegister();
            if (checkForNewHigh) {
                //Αν δεν έγινε personal record
                boolean flag = true;
                sort();
                int count = 0;
                //Για να μην υπάρξει interrupt στο Thread του WinFrame
                if (playerInfo.stream().anyMatch(p -> p.getName().equals(name))) {
                    for (PlayerInfo p : playerInfo) {
                        if (isRegistered(name, score)) {
                            count++;
                            if (count == 3) {
                                break;
                            }
                        }
                        if (new PlayerInfo(name, score).didGreater(p)) {
                            SwingUtilities.invokeLater(() ->
                                    JOptionPane.showMessageDialog(null, "Ξεπέρασες το προσωπικό σου highscore!", "Congratulations", JOptionPane.INFORMATION_MESSAGE));
                            flag = false;
                            break;
                        }
                    }
                }
                if (flag) {
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(null, "You managed to set a new Highscore to the highscore table", "Congratulations", JOptionPane.INFORMATION_MESSAGE));
                }
            }

            playerInfoSize = playerInfo.size();
            setFile(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>Μέθοδος που μεταβάλει την κατάσταση του αρχείου σε writable/not-writable </p>
     *
     * @param status : true -> writable , false -> not-writable
     */
    private void setFile(boolean status) {
        File file = new File("src/main/resources/HighScore.txt");
        //making the file as read/read-only using setWritable(status) method
        file.setWritable(status);
    }

    /**
     * <p>Έλεγχος για το αν το score που δίδεται είναι αρκετά μεγάλο
     * για να καταγραφεί στον πίνακα των top 10 ή όχι</p>
     *
     * @return true αν έγινε η καταχώρηση επιτυχώς, false αν δεν έγινε νέα καταχώρηση
     * @throws IOException if any
     */
    private boolean checkForNewRegister() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/HighScore.txt"));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        if (lines < 10) {
            appendScore();
            playerInfo.add(new PlayerInfo(name, score));
            return true;
        }

        for (PlayerInfo player : playerInfo) {
            if (score > player.getScore()) {
                appendScore();
                playerInfo.add(new PlayerInfo(name, score));
                return true;
            }
        }
        return false;

    }

    /**
     * <p>Προσθήκη στοιχείων στο αρχείο txt των highscores </p>
     */
    private void appendScore() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;

        try {
            fileWriter = new FileWriter("src/main/resources/HighScore.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            //Writing text to file με συγκεκριμένη μορφοποίηση
            printWriter.print(String.format("%s %d", name, score));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Closing the resources
            try {
                assert printWriter != null;
                printWriter.close();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * <p>Φόρτωση πληροφοριών αρχείου στη συνδεδεμένη λίστα <code>playerInfo</code></p>
     *
     * @throws IOException if any
     */
    private void load() throws IOException {
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

    /**
     * <p>Ταξινόμηση playerInfo και αρχείου με φθίνουσα σειρά με βάση τα scores</p>
     *
     * @throws IOException if any
     */
    private void sort() throws IOException {
        //Sorting ArrayList playerInfo based on scores
        playerInfo.sort((p1, p2) -> p2.getScore() - p1.getScore());
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

    /**
     * <p>clear LinkedList.</p>
     */
    @VisibleForTesting
    public void clear() {
        playerInfo.clear();
    }

    /**
     * <p>Έλεγχος για το αν υπάρχει μια συγκεκριμένη καταχώρηση.</p>
     *
     * @param name  a {@link java.lang.String} object
     * @param score a int
     * @return a boolean
     */
    @VisibleForTesting
    public boolean isRegistered(String name, int score) {
        return playerInfo.contains(new PlayerInfo(name, score));
    }

    /**
     * <p>Getter for the field <code>playerInfoSize</code>.</p>
     *
     * @return a int
     */
    public static int getPlayerInfoSize() {
        return playerInfoSize;
    }

}

package highscoreTest;

import com.google.common.annotations.VisibleForTesting;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

/**
 * <p>Λειτουργική κλάση για την εμφάνιση των top 10 scores μαζί με τα username αντίστοιχα.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see PlayerInfo
 */
public final class HighScore {

    /**
     * <code>playerInfo</code> Περιέχει τα στοιχεία των παικτών που έχουν καταχωρήσει highscore
     */
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
     * @param name  όνομα παίκτη
     * @param score βαθμολογία παίκτη
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
        setFile(true);
        load();
        boolean checkForNewHigh = checkForNewRegister();
        if (checkForNewHigh) {
            //Αν δεν έγινε personal record
            boolean flag = true;
            sort();
            int count = 0;
            //Έλεγχος για το αν έγινε προσωπικό ρεκόρ
            if (playerInfo.stream().anyMatch(p -> p.getName().equals(name))) {
                for (PlayerInfo p : playerInfo) {
                    int check = new PlayerInfo(name, score).didGreater(p);
                    if (check == PlayerInfo.greater) {
                        //Για να μην υπάρξει interrupt στο Thread του WinFrame
                        SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(null,
                                        "Ξεπέρασες το προσωπικό σου highscore!", "Congratulations",
                                        JOptionPane.INFORMATION_MESSAGE));
                        flag = false;
                        break;
                    } else if (check == PlayerInfo.notGreater) {
                        count++;
                        //Αν συναντάται η ίδια καταχώρηση πάνω από μια φορά, η αναζήτηση σταματά
                        if (count == 2) {
                            break;
                        }
                    }
                }
            }
            if (flag) {
                //Για να μην υπάρξει interrupt στο Thread του WinFrame
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null,
                                "You managed to set a new Highscore to the highscore table", "Congratulations",
                                JOptionPane.INFORMATION_MESSAGE));
            }
        }
        playerInfoSize = playerInfo.size();
        setFile(false);
    }

    /**
     * <p>Μέθοδος που μεταβάλει την κατάσταση του αρχείου σε writable/not-writable </p>
     *
     * @param status : true - writable , false - not-writable
     */
    private void setFile(boolean status) {
        File file = new File("./HighScore.txt");
        //making the file as read/read-only using setWritable(status) method
        file.setWritable(status);
    }

    /**
     * <p>Έλεγχος για το αν το score που δίδεται είναι αρκετά μεγάλο
     * για να καταγραφεί στον πίνακα των top 10 ή όχι</p>
     *
     * @return true αν έγινε η καταχώρηση επιτυχώς,<br> false αν δεν έγινε νέα καταχώρηση
     */
    private boolean checkForNewRegister() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("./HighScore.txt"), StandardCharsets.UTF_8))) {

            int lines = 0;
            while (reader.readLine() != null) {
                lines++;
            }
            reader.close();
            //Αν οι γραμμές είναι < 10 γίνεται καταχώρηση χωρίς έλεγχο
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * <p>Προσθήκη στοιχείων στο αρχείο txt των highscores </p>
     */
    private void appendScore() {
        try (BufferedWriter writer = new BufferedWriter
                (new OutputStreamWriter(
                        new FileOutputStream("./HighScore.txt", true), StandardCharsets.UTF_8))) {
            writer.write(String.format("%s %d", name, score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Φόρτωση πληροφοριών αρχείου στη συνδεδεμένη λίστα <code>playerInfo</code></p>
     */
    private void load() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("./HighScore.txt"), StandardCharsets.UTF_8))) {

            String currentLine = reader.readLine();

            while (currentLine != null) {
                String[] playerDetails = currentLine.split(" ");

                String name = playerDetails[0];

                int score = Integer.parseInt(playerDetails[1]);

                //Creating PlayerInfo object for every record and adding it
                playerInfo.add(new PlayerInfo(name, score));
                currentLine = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>Ταξινόμηση playerInfo και αρχείου με φθίνουσα σειρά με βάση τα scores</p>
     */
    private void sort() {
        //Sorting ArrayList playerInfo based on scores
        playerInfo.sort((p1, p2) -> p2.getScore() - p1.getScore());
        try (BufferedWriter writer = new BufferedWriter
                (new OutputStreamWriter(
                        new FileOutputStream("./HighScore.txt"), StandardCharsets.UTF_8))) {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Δημιουργία αρχείου καταγραφής HighScore την 1η φορά εκτέλεσης του παιχνιδιού.</p>
     */
    public static void setup() {
        try {
            File file = new File("./HighScore.txt");
            if (file.createNewFile()) {
                SwingUtilities.invokeLater(() ->
                        JOptionPane.showMessageDialog(null, "Επιτυχής προετοιμασία αρχείου HighScore",
                                "Info", JOptionPane.INFORMATION_MESSAGE));
            }
            file.setWritable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Έλεγχος για το αν υπάρχει μια συγκεκριμένη καταχώρηση.</p>
     *
     * @param name  a {@link java.lang.String} object
     * @param score an int
     * @return a boolean
     */
    @VisibleForTesting
    public boolean isRegistered(String name, int score) {
        return playerInfo.contains(new PlayerInfo(name, score));
    }

    /**
     * <p>Getter for the field <code>playerInfoSize</code>.</p>
     *
     * @return an int
     */
    public static int getPlayerInfoSize() {
        return playerInfoSize;
    }

}

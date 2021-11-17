package cli;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class ReadText {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/resources/testfile.txt"));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        System.out.println(lines);

    }
}

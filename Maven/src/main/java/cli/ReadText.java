package cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ReadText {

    public static void main(String[] args) throws IOException {
        //Τρόπος π.χ σπαν
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/testfile.txt")));
        System.out.println(content);
        //--Τέλος τρόπου Σπαν
        Scanner sc = new Scanner(new File("src/main/resources/testfile.txt"));
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine())
            lines.add(sc.nextLine());

        System.out.println(lines);

    }
}

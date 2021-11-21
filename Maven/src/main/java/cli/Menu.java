package cli;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner=new Scanner(System.in);
    public static String UserName;

    public Menu(){
       System.out.println("Welcome to Thyseas Labyrinth Game by Hack-You Team");
       System.out.println("Insert UserName:");
       String username=scanner.nextLine();
       UserName=username;
       if(username.equalsIgnoreCase("spyros")){
           System.err.println("You lost, good paradise");
           System.exit(1);
       }
       System.out.printf("Wassup %s \n",username);
       //Save file somehow
       OptionMenu();
       System.out.println("Use the arrow keys to move");

    }

    public void OptionMenu(){
        for (;;) {
            try {
                System.out.println("Select Difficulty");
                System.out.println("E for easy, M for medium , H for hard");
                char answer = scanner.next().charAt(0);
                answer = Character.toLowerCase(answer);
                if (answer != 'e' && answer != 'm' && answer != 'h')
                    throw new WrongInputException("Please give a correct answer");
                break;
            } catch (WrongInputException e) {
                e.printIssue();
                scanner.nextLine();
            }
        }
    }
}

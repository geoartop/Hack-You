package cli;

public class Labyrinth {

    private String[][] labyrinth;
    private final int size;
    public Labyrinth(char answer){
        switch (answer) {
            case 'e':
                size = 6;
                break;
            case 'm':
                size = 8;
                break;
            default:
                size = 10;
                break;
        }
        labyrinth=new String[size][size];
    }

}

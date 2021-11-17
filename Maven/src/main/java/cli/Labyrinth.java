package cli;

public class Labyrinth {
    private String[][] labyrinth;
    private int size;
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
    }

}

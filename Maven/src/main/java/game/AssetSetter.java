package game;

/**
 * Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class AssetSetter {

    private final GamePanel gp;
    private int index = -1;

    /**
     * <p>Constructor for AssetSetter.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    private void addElement(SuperObject element) {
        gp.obj.add(element);
        index++;
    }

    private void addSpikes(int x, int y, boolean isHorizontal) {
        addElement(new OBJ_Spikes(index));
        setXY(x, y);
        addElement(new OBJ_Spikes(index - 1));
        if (isHorizontal) {
            setXY(x + 1, y);
        } else {
            setXY(x, y + 1);
        }
    }

    private void setXY(int x, int y) {
        gp.obj.get(index).worldX = x * gp.tileSize;
        gp.obj.get(index).worldY = y * gp.tileSize;
    }

    /*private void easyObjects(){

    }

    private void mediumObjects(){

    }

    private void hardObjects(){

    }*/

    /**
     * <p>setObject.</p>
     */
    public void setObject() {

        /*switch (Levels.getDifficulty()) {
            case "Easy":
                easyObjects();
                break;
            case "Medium":
                mediumObjects();
                break;
            default:
                hardObjects();
                break;
        }*/

        addElement(new OBJ_Question());
        setXY(1, 9);

        addElement(new OBJ_Question());
        setXY(6, 4);

        addElement(new OBJ_Question());
        setXY(5, 13);

        addElement(new OBJ_Question());
        setXY(11, 12);

        addElement(new OBJ_Question());
        setXY(11, 5);

        addSpikes(12, 4, false);
        //addVerticalSpikes(12, 4);

        addElement(new OBJ_Question());
        setXY(8, 9);

        addElement(new OBJ_Exit());
        setXY(15, 4);

        addElement(new OBJ_Coin());
        setXY(2, 10);

        addElement(new OBJ_Question());
        setXY(2, 6);

        addSpikes(2, 7, true);

    }

}

package game;

/**
 * <p> Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel.</p>
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

    /**
     * <p>addElement and set its position</p>
     *
     * @param element a {@link SuperObject} object
     * @param x an int
     * @param y an int
     */
    private void addElement(SuperObject element, int x, int y) {
        gp.obj.add(element);
        index++;
        gp.obj.get(index).setWorldX(x * GamePanel.tileSize);
        gp.obj.get(index).setWorldY(y * GamePanel.tileSize);
    }

    /**
     * <p>addSpikes horizontally or vertically</p>
     *
     * @param x            an int
     * @param y            an int
     * @param isHorizontal a boolean
     */
    private void addSpikes(int x, int y, boolean isHorizontal) {
        addElement(new OBJ_Spikes(index), x, y);
        if (isHorizontal) {
            addElement(new OBJ_Spikes(index - 1), x + 1, y);
        } else {
            addElement(new OBJ_Spikes(index - 1), x, y + 1);
        }
    }

    private void addCoins(int x, int y, boolean isHorizontal) {
        addElement(new OBJ_Coin(), x, y);
        if (isHorizontal) {
            addElement(new OBJ_Coin(), x + 1, y);
        } else {
            addElement(new OBJ_Coin(), x, y + 1);
        }
    }

    /*private void easyObjects(){
        if(TileManager.getLevel() == 1) {
            System.out.println();
        }else {

        }
    }

    private void mediumObjects(){
        if(TileManager.getLevel() == 1) {

        }else {

        }
    }

    private void hardObjects(){
        if(TileManager.getLevel() == 1) {

        }else {

        }
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

        /*
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

        addElement(new OBJ_Question());
        setXY(8, 9);

        addElement(new OBJ_Exit());
        setXY(15, 4);

        addElement(new OBJ_Coin());
        setXY(2, 10);

        addElement(new OBJ_Question());
        setXY(2, 6);

        addSpikes(2, 7, true);
        */

    }

}
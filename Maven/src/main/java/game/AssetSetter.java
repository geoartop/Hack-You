package game;

/**
 * Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel
 *
 * @author Team Hack-You
 *
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

    private void addQuestion(OBJ_Question question, int x, int y) {
        addElement(question);
        setXY(x, y);
        gp.obj.add(new OBJ_Spikes(index));
        setXY(x, y + 1);
        gp.obj.add(new OBJ_Spikes(index));
        setXY(x + 1, y + 1);
        index++;
    }

    private void addElement(SuperObject element) {
        gp.obj.add(element);
        index++;
    }

    private void setXY(int x, int y) {
        gp.obj.get(index).worldX = x * gp.tileSize;
        gp.obj.get(index).worldY = y * gp.tileSize;
    }

    /**
     * <p>setObject.</p>
     */
    public void setObject() {

        addElement(new OBJ_Question());
        setXY(1, 9);

        addElement(new OBJ_Question());
        setXY(6, 4);

        addElement(new OBJ_Question());
        setXY(5, 13);

        addElement(new OBJ_Question());
        setXY(11, 12);

        addElement(new OBJ_Question());
        setXY(12, 5);

        addElement(new OBJ_Question());
        setXY(8, 9);

        addElement(new OBJ_Exit());
        setXY(15, 4);

        addElement(new OBJ_Coin());
        setXY(2, 10);

        addElement(new OBJ_Question());
        setXY(3, 8);

        addElement(new OBJ_Spikes(index));
        setXY(2, 8);

    }

}

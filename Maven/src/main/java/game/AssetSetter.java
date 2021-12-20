package game;

/**
 * Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel
 */
public final class AssetSetter {

    private final GamePanel gp;
    private int index = -1;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    private void addElement(SuperObject element) {
        gp.obj.add(element);
        index++;
    }

    private void setXY(int x, int y) {
        gp.obj.get(index).worldX = x * gp.tileSize;
        gp.obj.get(index).worldY = y * gp.tileSize;
    }

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

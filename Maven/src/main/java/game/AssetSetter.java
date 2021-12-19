package game;

/**
 * Τοποθέτηση των αντικειμένων του παιχνιδιού στο gamepanel
 */
public final class AssetSetter {

    private final GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj.add(new OBJ_Question());
        gp.obj.get(0).worldX = 1 * gp.tileSize; // tile row - 1
        gp.obj.get(0).worldY = 9 * gp.tileSize; // tile col - 1

        gp.obj.add(new OBJ_Question());
        gp.obj.get(1).worldX = 6 * gp.tileSize;
        gp.obj.get(1).worldY = 4 * gp.tileSize;

        gp.obj.add(new OBJ_Question());
        gp.obj.get(2).worldX = 5 * gp.tileSize;
        gp.obj.get(2).worldY = 13 * gp.tileSize;

        gp.obj.add(new OBJ_Question());
        gp.obj.get(3).worldX = 11 * gp.tileSize;
        gp.obj.get(3).worldY = 12 * gp.tileSize;

        gp.obj.add(new OBJ_Question());
        gp.obj.get(4).worldX = 12 * gp.tileSize;
        gp.obj.get(4).worldY = 5 * gp.tileSize;

        gp.obj.add(new OBJ_Question());
        gp.obj.get(5).worldX = 8 * gp.tileSize;
        gp.obj.get(5).worldY = 9 * gp.tileSize;

        gp.obj.add(new OBJ_Exit());
        gp.obj.get(6).worldX = 15 * gp.tileSize;
        gp.obj.get(6).worldY = 4 * gp.tileSize;

        gp.obj.add(new OBJ_Coin());
        gp.obj.get(7).worldX = 2 * gp.tileSize;
        gp.obj.get(7).worldY = 10 * gp.tileSize;

    }
}

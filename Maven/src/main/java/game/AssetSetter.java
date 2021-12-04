package game;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Question();
        gp.obj[0].worldX = 1 * gp.tileSize; // tile row - 1
        gp.obj[0].worldY = 6 * gp.tileSize; // tile col - 1

        gp.obj[1] = new OBJ_Question();
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;
    }
}

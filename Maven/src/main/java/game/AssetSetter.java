package game;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        /*gp.obj[0] = new OBJ_Question();
        gp.obj[0].worldX = 1 * gp.tileSize; // tile row - 1
        gp.obj[0].worldY = 6 * gp.tileSize; // tile col - 1

        gp.obj[1] = new OBJ_Question();
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 1 * gp.tileSize;

        gp.obj[2] = new OBJ_Question();
        gp.obj[2].worldX = 5 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new OBJ_Question();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 9 * gp.tileSize;

        gp.obj[4] = new OBJ_Question();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 2 * gp.tileSize;

        gp.obj[5] = new OBJ_Question();
        gp.obj[5].worldX = 8 * gp.tileSize;
        gp.obj[5].worldY = 6 * gp.tileSize;

        gp.obj[6] = new OBJ_Exit();
        gp.obj[6].worldX = 15 * gp.tileSize;
        gp.obj[6].worldY = 1 * gp.tileSize;*/

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

        /*if(Levels.difficulty.equals("Easy")){
            gp.obj.add(new OBJ_Exit());
            gp.obj.get(6).worldX = 1 * gp.tileSize;
            gp.obj.get(6).worldY = 7 * gp.tileSize;
        }*/


        /*gp.obj[0] = new OBJ_Question();
        gp.obj[0].worldX = 1 * gp.tileSize; // tile row - 1
        gp.obj[0].worldY = 9 * gp.tileSize; // tile col - 1

        gp.obj[1] = new OBJ_Question();
        gp.obj[1].worldX = 6 * gp.tileSize;
        gp.obj[1].worldY = 4 * gp.tileSize;

        gp.obj[2] = new OBJ_Question();
        gp.obj[2].worldX = 5 * gp.tileSize;
        gp.obj[2].worldY = 13 * gp.tileSize;

        gp.obj[3] = new OBJ_Question();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 12 * gp.tileSize;

        gp.obj[4] = new OBJ_Question();
        gp.obj[4].worldX = 12 * gp.tileSize;
        gp.obj[4].worldY = 5 * gp.tileSize;

        gp.obj[5] = new OBJ_Question();
        gp.obj[5].worldX = 8 * gp.tileSize;
        gp.obj[5].worldY = 9 * gp.tileSize;

        gp.obj[6] = new OBJ_Exit();
        gp.obj[6].worldX = 15 * gp.tileSize;
        gp.obj[6].worldY = 4 * gp.tileSize;*/


    }
}

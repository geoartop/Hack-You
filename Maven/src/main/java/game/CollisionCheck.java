package game;

/**
 * <p>Έλεγχος συγκρούσεων του παίκτη με τοίχους.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class CollisionCheck {

    private final GamePanel gp;

    /**
     * <p>Constructor for CollisionCheck.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }

    /**
     * <p>checkTile.</p>
     *
     * @param entity a {@link game.Entity} object
     */
    public void checkTile(Player entity) {

        int entityLeftWorldX = entity.getWorldx() + entity.solidArea.x;
        int entityRightWorldX = entity.getWorldx() + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.getWorldy() + entity.solidArea.y;
        int entityBottomWorldY = entity.getWorldy() + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / GamePanel.tileSize;
        int entityRightCol = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - Player.speed) / GamePanel.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + Player.speed) / GamePanel.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - Player.speed) / GamePanel.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                break;
            default:
                entityRightCol = (entityRightWorldX + Player.speed) / GamePanel.tileSize;
                tileNum1=gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2=gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                break;

        }
        if (gp.tileM.getTileCollision(tileNum1, tileNum2)) {
            entity.setCollisionOn(true);
        }
    }

    /**
     * <p>checkObject.</p>
     *
     * @param entity a {@link game.Entity} object
     * @param player a boolean
     * @return index
     */
    public int checkObject(Player entity, boolean player) {
        int index = 999;
        //Object counter
        int i = 0;
        for (SuperObject object : gp.obj) {
            if (object != null) {
                // get entity's solid area position
                entity.solidArea.x = entity.getWorldx() + entity.solidArea.x;
                entity.solidArea.y = entity.getWorldy() + entity.solidArea.y;
                // get the object solid area position
                object.solidArea.x = object.getWorldX() + object.solidArea.x;
                object.solidArea.y = object.getWorldY() + object.solidArea.y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.solidArea.y -= Player.speed;
                        index = check(entity, player, index, i);
                        break;
                    case "down":
                        entity.solidArea.y += Player.speed;
                        index = check(entity, player, index, i);
                        break;
                    case "left":
                        entity.solidArea.x -= Player.speed;
                        index = check(entity, player, index, i);
                        break;
                    default:
                        entity.solidArea.x += Player.speed;
                        index = check(entity, player, index, i);
                        break;

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                object.solidArea.x = SuperObject.solidAreaDefaultX;
                object.solidArea.y = SuperObject.solidAreaDefaultY;

            }
            i++;
        }

        return index;
    }

    /**
     * <p>check.</p>
     *
     * @param entity a {@link Player} object
     * @param player a boolean
     * @param index  an int
     * @param i      an int
     * @return index an int
     */
    private int check(Player entity, boolean player, int index, int i) {
        if (entity.solidArea.intersects(gp.obj.get(i).solidArea)) {
            if (gp.obj.get(i).collision) {
                entity.setCollisionOn(true);
            }
            if (player) {
                index = i;
            }
        }

        return index;
    }
}

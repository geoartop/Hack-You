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

        int entityLeftWorldX = entity.solidArea.x + entity.getWorldx();
        int entityRightWorldX = entity.solidArea.x + entity.getWorldx() + entity.solidArea.width;
        int entityTopWorldY = entity.solidArea.y + entity.getWorldy();
        int entityBottomWorldY = entity.solidArea.y + entity.getWorldy() + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / GamePanel.tileSize;
        int entityRightColumn = entityRightWorldX / GamePanel.tileSize;
        int entityTopRow = entityTopWorldY / GamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / GamePanel.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - Player.speed) / GamePanel.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityTopRow];
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + Player.speed) / GamePanel.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityBottomRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityBottomRow];
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - Player.speed) / GamePanel.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityLeftColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityLeftColumn][entityBottomRow];
                break;
            default:
                entityRightColumn = (entityRightWorldX + Player.speed) / GamePanel.tileSize;
                tileNumber1 = gp.tileM.mapTileNumber[entityRightColumn][entityTopRow];
                tileNumber2 = gp.tileM.mapTileNumber[entityRightColumn][entityBottomRow];
                break;

        }
        if (gp.tileM.getTileCollision(tileNumber1, tileNumber2)) {
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
                entity.solidArea.x = entity.solidArea.x + entity.getWorldx();
                entity.solidArea.y = entity.solidArea.y + entity.getWorldy();
                // get the object solid area position
                object.solidArea.x = object.solidArea.x + object.getWorldX();
                object.solidArea.y = object.solidArea.y + object.getWorldY();

                switch (entity.getDirection()) {
                    case "up":
                        entity.solidArea.y -= Player.speed;
                        break;
                    case "down":
                        entity.solidArea.y += Player.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= Player.speed;
                        break;
                    default:
                        entity.solidArea.x += Player.speed;
                        break;
                }
                index = check(entity, player, index, i);
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

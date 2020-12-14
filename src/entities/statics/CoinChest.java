package entities.statics;

import java.awt.Graphics;

import graphics.Assets;
import launcher.Handler;
import tiles.Tile;

public class CoinChest extends StaticEntity {

    private boolean scoreTaken;

    public CoinChest(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        scoreTaken = false;
        health = 1;
        bounds.x = 5;
        bounds.y = (int) (height/ 1.5f);
        bounds.width = width - 2;
        bounds.height = (int) (height - height / 1.02f);
    }

    @Override
    public void die(){}

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        if(health <= 0){
            g.drawImage(Assets.coin_chest_open, (int) (x - handler.getGameCam().getxOffset()), (int) (y - handler.getGameCam().getyOffset()), width, height, null);
            if(!scoreTaken) handler.getWorld().getEntityManager().getPlayer().setCoins();
            scoreTaken = true;
        } else {
            g.drawImage(Assets.coin_chest_closed, (int) (x - handler.getGameCam().getxOffset()), (int) (y - handler.getGameCam().getyOffset()), width, height, null);
        }
    }
    
}

package entities.statics;

import java.awt.Graphics;

import graphics.Assets;
import launcher.Handler;
import tiles.Tile;

public class BigTree extends StaticEntity {

    public BigTree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = -10;
        bounds.y = (int) (height / 1.5f);
        bounds.width =  35;
        bounds.height = 2;
    }

    @Override
    public void die(){}

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.big_tree, (int) (x - handler.getGameCam().getxOffset()) - width, (int) (y - handler.getGameCam().getyOffset()) - (height *2), width * 2, height * 3, null);
    }
    
}

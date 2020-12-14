package entities.statics;

import java.awt.Graphics;

import graphics.Assets;
import launcher.Handler;
import tiles.Tile;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        bounds.x = -10;
        bounds.y = (int) (height / 1.5f);
        bounds.width =  30;
        bounds.height = 2;
    }

    @Override
    public void die(){}

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCam().getxOffset()) - width, (int) (y - handler.getGameCam().getyOffset()) - height, width * 2, height * 2, null);
    }
    
}

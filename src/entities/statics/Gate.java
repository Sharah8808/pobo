package entities.statics;

import java.awt.Graphics;

import graphics.Assets;
import launcher.Handler;
import tiles.Tile;

public class Gate extends StaticEntity {

    public Gate(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        endGameEntity = true;

        bounds.x = -52;
        bounds.y = (int) (height/ 1.5f);
        bounds.width = width + 50;
        bounds.height = (int) (height - height / 1.02f);

    }

    @Override
    public void die(){}

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.gate_closed, (int) (x - handler.getGameCam().getxOffset()) - width, (int) (y - handler.getGameCam().getyOffset()) - (height *2), width * 2, height * 3, null);
    }
    
}

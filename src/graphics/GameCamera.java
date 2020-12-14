package graphics;

import entities.Entity;
import launcher.Handler;
import tiles.Tile;

public class GameCamera {
    private Handler handler;
    public float xOffset, yOffset;

    public GameCamera(Handler handler,  float xOffset, float yOffset){
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    //utk cek kamera yg ksi liat putih2 di luar map tu
    public void checkBlankSpace(){
        //hilangin white space di bgian kiri map
        if(xOffset < 0){
            xOffset = 0;
        } 
        //kanan map
        else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() + 16){
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth() + 16;
        }   
        // atas map
        if(yOffset < 0){
            yOffset = 0;
        } 
        //bawah map
        else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() + 35){
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight() + 35;
        }
    }

    public void move(float xAmount, float yAmount){
        xOffset += xAmount;
        yOffset += yAmount;
        checkBlankSpace();
    }

    //atur posisi x y kamera biar tepat d tengah2 entity, playerny (?
    public void centerOneEntity(Entity e){
        xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2 ; 
        yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2; 
        checkBlankSpace();
    }

    public float getxOffset(){
        return xOffset;
    }

    public float getyOffset(){
        return yOffset;
    }

    public void setxOffset(float xOffset){
        this.xOffset = xOffset;
    }

    public void setyOffset(float yOffset){
        this.yOffset = yOffset;
    }
}

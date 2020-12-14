package entities.creatures;

import entities.Entity;
import launcher.Handler;
import tiles.Tile;

import java.awt.image.BufferedImage;

public abstract class Creature extends Entity {

    protected float speed;
    protected float xMove, yMove;

    //g bsa d ubah krn final, defaulth smua creature
    // public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    //animations
    protected boolean stayStill = false;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        if(!checkEntityCollisions(xMove, 0f))
            moveX();
        if(!checkEntityCollisions(0f, yMove))
            moveY();
    }

    //utk move x y coalission bound, rectangle tu
    public void moveX(){
        //temporary x
        int tx;
        if(xMove > 0){ //gerak kanan
            tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            //nge-cek collision utk rectangle bound di pojok kanan atas && pojok kanan bawah        
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                stayStill = false;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
                stayStill = true;
            }
        }else if(xMove < 0){ //gerak kiri
            tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            //nge-cek collision utk rectangle bound di pojok kanan atas && pojok kanan bawah        
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && 
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
                x += xMove;
                stayStill = false;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
                stayStill = true;
            }
        }
    }

    public void moveY(){
        int ty;
        if(yMove < 0){ //gerak atas
            ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty ) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width ) / Tile.TILEWIDTH, ty ) ){
                y += yMove;
                stayStill = false;
            } else{
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
                stayStill = true;
            }
        }else if(yMove > 0){ //gerak bawah
            ty = (int) (y + yMove + bounds.y + bounds.height ) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty ) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width ) / Tile.TILEWIDTH, ty ) ){
                y += yMove;
                stayStill = false;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                stayStill = true;
            }
        }
    }

    //ambil tile d posisi x y (dri parameter), utk tw dia tu solid atau ga (kya batu atau pohon)
    protected boolean collisionWithTile(int x , int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    // int lastFrameAnimation = 4;
    abstract protected BufferedImage getCurrentAnimationFrame();
    
    abstract protected  BufferedImage getIdleAnimationFrame();

    //getters and setters
    public int getHealth(){
        return health;
    }

    public float getSpeed(){
        return speed;
    }

    public float getxMove(){
        return xMove;
    }

    public float getyMove(){
        return yMove;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void setxMove(float xMove){
        this.xMove = xMove;
    }

    public void setyMove(float yMove){
        this.yMove = yMove;
    }
    
}

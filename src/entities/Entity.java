package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import launcher.Handler;

public abstract class Entity {
    // protected TreasureHunter game;
    protected Handler handler;
    //pke float biar smooth movement
    protected float x, y;
    //ukuran entiti
    protected int width, height;

    public static final int DEFAULT_HEALTH = Integer.MAX_VALUE;
    protected int health;

    //singkatan dri coalission bounds
    protected Rectangle bounds;

    //nandain dia entiti brbahaya atau ga
    protected boolean deadly = false;
    protected boolean endGameEntity = false;

    public Entity(Handler handler, float x, float y, int width, int height){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    //entity kena pukul, kena damage
    public void receiveDamage(int damage){
        health -= damage;

        if(health <= 0){
            die();
        }
    }

    public abstract void die();

    public abstract void tick();

    public abstract void render(Graphics g);

    //tes smua entity d game utk tw apakah dia menyentuh/bertemu dgn entity yg lain
    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            //utk mncegah dia cek collision ke dirinya sendiri
            //klo in dihilangin, player g bsa gerak :v krn dia nge-detect collision ke dirinya sndiri
            if(e.equals(this))
                continue;
            //jk entity yg kt looping brsentuhan/memotong (intersect) dgn collision bounds (area collisionnya) 
            //maka akan return true, artinya ada collision
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    //akan return bounding area di sbuah entity, di skitaran entity tsb
    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public Rectangle getCollisionBoundsEnemy(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset - 1), (int) (y + bounds.y + yOffset -1), bounds.width + 3, bounds.height + 3);
    }

    //getter
    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }
    
    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public int getHealth(){
        return health;
    }

    public boolean isDeadly(){
        return deadly;
    }

    public boolean isEndGameEntity(){
        return endGameEntity;
    }

    //setter
    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setDeadly(boolean deadly){
        this.deadly = deadly;
    }

    public void setEndGameEntity(boolean endGameEntity){
        this.endGameEntity = endGameEntity;
    }
}

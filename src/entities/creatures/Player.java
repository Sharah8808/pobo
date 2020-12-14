package entities.creatures;

import graphics.Animation;
import graphics.Assets;
import graphics.StatDisplay;
import launcher.Handler;
import states.GameState;
import states.MenuState;
import states.State;
import entities.Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.Rectangle;

public class Player extends Creature {

    public MenuState newMenuState;
    public GameState gameState;

    //attack timer
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown; //800 milisecond, 1000 = 1 detik

    //invincible timer
    private long lastInvincibleTime, invincibleCooldown = 2000;

    //animations
    private Animation animDown, animUp, animLeft, animRight;
    
    //stat display (heart, coin key)
    private StatDisplay stat;

    private int health;
    private boolean tempInvincible;
    private int keys;
    private int coins;  

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        
        this.health = 3;
        keys = 0;
        coins = 0;
        tempInvincible = false;

        //ini nilainya fleksibel, ssuatu sma coallison bound yg mw d pasang ke karakter/player
        bounds.x = 20;
        bounds.y = 34;
        bounds.width = 25;
        bounds.height = 30;

        animDown = new Animation(250, Assets.player_down);
        animUp = new Animation(250, Assets.player_up);
        animLeft = new Animation(250, Assets.player_left);
        animRight = new Animation(250, Assets.player_right);

        stat = new StatDisplay(handler);
    }

    @Override
    public void die(){
        
        if(health == 0){
            State.setState(handler.getGame().gameOverState);
            return;
        }

        Rectangle playerBody = getCollisionBoundsEnemy(0, 0);
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){

            if(playerBody.intersects(e.getCollisionBoundsEnemy(0, 0)) && e.isDeadly() == true && !tempInvincible){
                lastInvincibleTime = System.currentTimeMillis();
                tempInvincible = true;
                this.health--;

                //set posisi player ke tmpt awal tiap kali dia mati
                handler.getWorld().getEntityManager().getPlayer().setX(handler.getWorld().getSpawnX());
                handler.getWorld().getEntityManager().getPlayer().setY(handler.getWorld().getSpawnY());
            }
            
        }
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        //movement
        getInput();
        move();
        die();
        handler.getGameCam().centerOneEntity(this);

        //attack
        checkAttacks();
        checkInvincible();
        stat.tick();
    }

    //klo user klik attack key (tmbol atas bwah knn kiri), method ni akan generate attack yg akan trjadi
    //jgn lupa klo tiap attack pnya smacam collision boundnya yg ngena item d sbelah bwah/atas/kri/kanan
    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();

        if(attackTimer < attackCooldown)
            return;

        //cb = collision box
        Rectangle cb = getCollisionBounds(0, 0);
        //area attacknya, ar = attack area
        Rectangle ar = new Rectangle();
        //ukuran bound/area attacknya, misal 20 pixel
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y - arSize; 
        } else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width/2 - arSize/2;
            ar.y = cb.y + cb.height; 
        } else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height/2 - arSize/2; 
        } else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height/2 - arSize/2; 
        } else {
            return;
        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar)){
                if(this.keys > 0 && e.isEndGameEntity() ){
                    State.setState(handler.getGame().gameFinishedState);
                    return;
                }
                
                e.receiveDamage(1);
                return;
            }
        }
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        stat.render(g);

        //otomatis posisinya sesuai dgn x y dari abstract class entity
        //x y msih brupa float, hrs d ubah ke bntk integer
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCam().getxOffset()), (int) (y - handler.getGameCam().getyOffset()) ,width, height, null);

    }

    int lastFrameAnimation = 4;
    public BufferedImage getCurrentAnimationFrame(){
        if(xMove < 0){
            lastFrameAnimation = 1;
            return animLeft.getCurrentFrame();
        } else if(xMove > 0){
            lastFrameAnimation = 2;
            return animRight.getCurrentFrame();
        } else if(yMove < 0){
            lastFrameAnimation = 3;
            return animUp.getCurrentFrame();
        } else if(yMove > 0){
            lastFrameAnimation = 4;
            return animDown.getCurrentFrame();
        } else {
            return getIdleAnimationFrame();
        }
    }

    public BufferedImage getIdleAnimationFrame(){
        switch(lastFrameAnimation){
            case 1:
            return animLeft.getIdleFrame();

            case 2:
            return animRight.getIdleFrame();

            case 3:
            return animUp.getIdleFrame();

            case 4:
            return animDown.getIdleFrame();

            default:
            return null;
        }
    }

    private void checkInvincible(){
        if(tempInvincible){
            if(System.currentTimeMillis() - lastInvincibleTime > invincibleCooldown){
            tempInvincible = false;
            }
        }
    }

    //getter setter

    public int getHealth(){
        return health;
    }

    public void setCoins(int coins){
        this.coins = coins;
    }

    public void setCoins(){
        this.coins = this.coins + 1;
    }

    public int getCoins(){
        return this.coins;
    }

    public void setKeys(){
        this.keys = this.keys + 1;
    }

    public String getKeys(){
        return Integer.toString(this.keys);
    }

    public String getScore(){
      return Integer.toString(this.coins * 100);
    }
}

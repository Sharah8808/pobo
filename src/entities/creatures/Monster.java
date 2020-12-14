package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import graphics.Animation;
import graphics.Assets;
import launcher.Handler;
import tiles.Tile;

public class Monster extends Creature {

    private int moveRange, moveSpeed, currentMove = 0;
    private String moveType;
    private boolean direction;

    private Animation animDown, animUp, animLeft, animRight;

    public Monster(Handler handler, float x, float y, String moveType, int moveRange, int moveSpeed) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        deadly = true;

        //animation frames
        animLeft = new Animation(200, Assets.monster_left);
        animRight = new Animation(200, Assets.monster_right);
        animUp = new Animation(200, Assets.monster_up);
        animDown = new Animation(200, Assets.monster_down);

        //buat collision bounds monster
        bounds.x = 2;
        bounds.y = (int) (height/ 3f); //+4
        bounds.width = width - 5;
        bounds.height = (int) (height - height / 2.32f);


        this.moveType = moveType;
        this.moveRange = moveRange;
        this.moveSpeed = moveSpeed;
        this.direction = true;
    }

    @Override
    public void die() {

    }

    @Override
    public void tick() {
      movement();
      move();

        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCam().getxOffset()), (int) (y - handler.getGameCam().getyOffset()) ,width, height, null);
    }

    public void movement(){
      //currentMove cek smpe dia brnilai sma dgn jarak maksimal monster tu blh bergerak (moveRange)
      //klo sdh melebihi, arah dia gerak akan brbalik, msuk ke statemen if else d bwahnya utk gerak horizontal/vertikal
      currentMove++;
      if(stayStill == true || currentMove > moveRange){
        direction = !direction;
        currentMove = 0;
      }

      // if(moveType == "horizontal")
      //x += xMove dri method move() dri class creature, xMove dsni kcepatan yg d ksi ke monster tu
      //x = posisi x (kiri kanan), xMove = pergerakan arah x yg horizontal
      //y = posisi y (atas bwh), yMove = pergreakan  arah y yg vertikal
      if(moveType == "horizontal"){ //dri parameter monster, gerak horizontal
        if(direction) xMove = moveSpeed;
        else xMove = -moveSpeed;
      } else if (moveType == "vertical"){
        if(direction) yMove = moveSpeed;
        else yMove = -moveSpeed;
      }
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
}

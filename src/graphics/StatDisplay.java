package graphics;

import launcher.Handler;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

//utk nampilin jmlh nyawa, koin n key ke layar
public class StatDisplay {
    private Handler handler;
    private Animation anim;

    public StatDisplay(Handler handler){
        this.handler = handler;
        anim = new Animation(100, Assets.coins);
    }

    public void tick(){
        anim.tick();
    }

    public void render(Graphics g){
        switch (handler.getWorld().getEntityManager().getPlayer().getHealth()) {
            case 1:
                g.drawImage(Assets.heart, 10, 10, 60, 60, null);
                break;
        
            case 2:
                g.drawImage(Assets.heart, 10, 10, 60, 60, null);
                g.drawImage(Assets.heart, 60, 10, 60, 60, null);
                break;

            case 3:
                g.drawImage(Assets.heart, 10, 10, 60, 60, null);
                g.drawImage(Assets.heart, 60, 10, 60, 60, null);
                g.drawImage(Assets.heart, 110, 10, 60, 60, null);
                break;

            default:
                break;
        }
        showCoin(g);    
        showKey(g);
    }

    public void showCoin(Graphics g){

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawImage( anim.getCurrentFrame(), 360, 20, 32, 32, null );
        g.drawString(handler.getWorld().getEntityManager().getPlayer().getScore(), 400, 47);

    }

    public void showKey(Graphics g){

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawImage(Assets.key, 510, 17, 40, 40, null);
        g.drawString(handler.getWorld().getEntityManager().getPlayer().getKeys(), 550, 47);

    }
}

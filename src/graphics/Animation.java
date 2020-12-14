package graphics;

import java.awt.image.BufferedImage;

public class Animation {
    
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        //mili detik yg tlh brlalu sejak game d jalankan
        lastTime = System.currentTimeMillis();
        timer = 0;
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            //krn frame utk animasinya cmn 4 frame, jdi frames.lenght = 4
            if(index >= frames.length){
                index = 0;
            }
        }
    }   

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public BufferedImage getIdleFrame(){
        return frames[0];
    }
}

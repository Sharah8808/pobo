package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

public abstract class UIObject implements ClickListener {
    
    protected float x, y;
    protected int width, height;
    protected boolean hovering = false;
    protected Rectangle bounds;

    public UIObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    //utk cek apa mouse tu ada di atas button
    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }

    //ktika lepas klikan mouse, klik -> lepas klik = klik button :v
    public void onMouseRelease(MouseEvent e){
        if(hovering)
            onClick();
    }

    //gettter setter
    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public boolean getHovering(){
        return hovering;
    }

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

    public void getHovering(boolean hovering){
        this.hovering = hovering;
    }
}

package items;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

import graphics.Assets;
import launcher.Handler;

public class Item {
    //handler
    public static Item[] items = new Item[5];
    public static Item coinItem = new Item(Assets.coin, "Coin", 0);
    public static Item keyItem = new Item(Assets.key, "Key", 1);

    //class

    public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

    protected Handler handler;
    protected BufferedImage texture;
    protected BufferedImage[] textureAnim;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    //count utk truh total item yg kt punya d inventory, misal coin = 23, key = 1
    protected int x, y, count; 

    public Item(BufferedImage texture, String name, int id){
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        items[id] = this;
    }

    public Item(BufferedImage[] textureAnim, String name, int id){
        this.textureAnim = textureAnim;
        this.name = name;
        this.id = id;
        count = 1;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);
        items[id] = this;
    }

    public void tick(){
    }

    public void render(Graphics g){
        if(handler == null)
            return;
        render(g, (int) (x - handler.getGameCam().getxOffset()) ,(int) (y - handler.getGameCam().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, ITEMWIDTH, ITEMHEIGHT, null);
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y);
        return i;
    }

    //for test n debug purposes
    public Item createNew(int count){
        Item i = new Item(texture, name, id);
        i.setCount(count);
        return i;
    }

    //SGETTER
    public Handler getHandler(){
        return handler;
    }

    public BufferedImage getTexture(){
        return texture;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getCount(){
        return count;
    }

    //setter

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    public void setTexture(BufferedImage texture){
        this.texture = texture;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.x = y;
    }

    public void setCount(int count){
        this.count = count;
    }

    //for coin
    public int getCoin(){
        return items[2].count * 100;
    }

}

package tiles;

import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class Tile {
    
    //static variables
    //change this into arraylist
    public static Tile[] tiles = new Tile[256];
    public static Tile waterTile = new WaterTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile grassTile = new GrassTile(2);
    public static Tile stoneTile = new StoneTile(3);

    //class
    protected BufferedImage texture;
    //utk identifikasi tiap tile, tiap tile pnya id yg beda
    protected final int id;

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick(){

    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    //metode utk nentuin tile itu bisa di lewatin/dijalanin/diinjak player apa ga
    //contoh rumput = bisa, batu = g bisa
    public boolean isSolid(){
        return false;
    }

    public int getId(){
        return id;
    }


}

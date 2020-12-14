package worlds;

import java.awt.Graphics;

import entities.EntityManager;
import entities.creatures.*;
import entities.statics.*;
import launcher.Handler;
import utils.Utils;
import tiles.Tile;

public class World {

    private Handler handler;
    private int width, height; // 5x5

    // koordinat pas player prtama muncul
    private int spawnX, spawnY;
    private int[][] tiles;

    // pos = mncirikan ukuran 1 tile, utk posisi entity di tile keberapa dri map
    private int pos = 64;

    // entity manager
    private EntityManager entityManager;

    public World(Handler handler, String path) {
        this.handler = handler;
        // itemManager = new ItemManager(handler);
        entityManager = new EntityManager(handler, new Player(handler, 200, 200));

        // loadEntity();
        loadWorldBeta();
        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        handler.getGame().getGameFinishedState().setName(null);

    }

    public void tick(){
        entityManager.tick();
    }

    public void render(Graphics g){
        //xstart = posisi kamera pling kiri (garis vertikal), xend pling kanan
        //ystart posisi kamera pling atas (garis horizontal). yend pling bawah

        //utk statement Math.max ni, misal utk int xstart, var tsb akan bernilai antara 0 atau game.getGameCam().getxOffset() / Tile.TILEWIDTH, trgantung mana yg lbh besar nilainya
        //kalau game.getGameCam().getxOffset() / Tile.TILEWIDTH lbh besar dri 0 (misal 4), maka nilai int xstart = 4
        //coba ganti jdi game.getGameCam().getxOffset() / Tile.TILEWIDTH + 1 biar liat prbedaanny
        int xStart = (int) Math.max(0, handler.getGameCam().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, ( handler.getGameCam().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1); 
        int yStart = (int) Math.max(0, handler.getGameCam().getyOffset() / Tile.TILEHEIGHT);;
        int yEnd = (int) Math.min(height, ( handler.getGameCam().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g, 
                        (int) (x*Tile.TILEWIDTH - handler.getGameCam().getxOffset()), 
                        (int) (y*Tile.TILEHEIGHT - handler.getGameCam().getyOffset()));
            }
        }

        entityManager.render(g);
    }

    public Tile getTile(int x, int y){

        //utk mmastikan biar player ga brada di luar tile/map, biar g glitch
        //jika bgitu, dia akan brdiri d grasstile wlaupun bkn, bia g error aja
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;

        Tile t = Tile.tiles[tiles[x][y]];

        if(t == null)
            return Tile.dirtTile;
        
            return t;
    }

    

    //get file dri comp,  get all data, n simpen ke var tiles[][] utk tw tile mana d posisi mana
    private void loadWorld(String path){
            String file = Utils.loadFileAsString(path);
            String[] tokens = file.split("\\s+");
            width = Utils.parseInt(tokens[0]);
            height = Utils.parseInt(tokens[1]);
            spawnX = Utils.parseInt(tokens[2]) * pos;
            spawnY = Utils.parseInt(tokens[3]) * pos;

            tiles = new int[width][height];
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    tiles[x][y] = Utils.parseInt(tokens[(x+y*width) +  4]);
                }
            }
        
    }
    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getSpawnX(){
        return spawnX;
    }

    public int getSpawnY(){
        return spawnY;
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public Handler getHandler(){
        return handler;
    }

    public void setHandler(Handler handler){
        this.handler = handler;
    }

    //loadw world m map
    public void loadEntity(){
        //tree
        entityManager.addEntity(new Tree(handler, pos * 26 + (pos/4), pos));
        entityManager.addEntity(new Tree(handler, pos * 5 + (pos/4), pos * 2));
        entityManager.addEntity(new Tree(handler, pos * 7 + (pos/4), pos * 2));
        entityManager.addEntity(new Tree(handler, pos * 9 + (pos/4), pos * 2));
        entityManager.addEntity(new Tree(handler, pos * 19 + (pos/4), pos * 2));
        entityManager.addEntity(new Tree(handler, pos * 14 + (pos/4), pos * 4));
        entityManager.addEntity(new Tree(handler, pos * 16 + (pos/4), pos * 5));
        entityManager.addEntity(new Tree(handler, pos * 17 + (pos/4), pos * 9));
        entityManager.addEntity(new Tree(handler, pos * 23 + (pos/4) , pos * 10));
        entityManager.addEntity(new Tree(handler, pos * 24 + (pos/4), pos * 10));
        entityManager.addEntity(new Tree(handler, pos * 25 + (pos/4), pos * 10));
        entityManager.addEntity(new Tree(handler, pos * 26 + (pos/4), pos * 10));
        entityManager.addEntity(new Tree(handler, pos * 24 + (pos/4), pos * 14));
        entityManager.addEntity(new Tree(handler, pos * 18 + (pos/4), pos * 16));
        entityManager.addEntity(new Tree(handler, pos * 3 + (pos/4), pos * 20));
        entityManager.addEntity(new Tree(handler, pos * 28 + (pos/4), pos * 20));
        entityManager.addEntity(new Tree(handler, pos * 28 + (pos/4), pos * 22));
        entityManager.addEntity(new Tree(handler, pos * 4 + (pos/4), pos * 23));
        entityManager.addEntity(new Tree(handler, pos * 22 + (pos/4), pos * 23));
        entityManager.addEntity(new Tree(handler, pos * 3 + (pos/4), pos * 24));
        entityManager.addEntity(new Tree(handler, pos * 26 + (pos/4), pos * 24));
        entityManager.addEntity(new Tree(handler, pos * 28 + (pos/4), pos * 24));
        entityManager.addEntity(new Tree(handler, pos * 24 + (pos/4), pos * 25));
        entityManager.addEntity(new Tree(handler, pos *  9 + (pos/4), pos * 26));
        entityManager.addEntity(new Tree(handler, pos * 11 + (pos/4), pos * 26));
        entityManager.addEntity(new Tree(handler, pos * 27 + (pos/4), pos * 5));

        //big tree
        entityManager.addEntity(new BigTree(handler, pos * 27 + (pos/4), pos * 6));
        entityManager.addEntity(new BigTree(handler, pos * 11 - (pos/4), pos * 11));
        entityManager.addEntity(new BigTree(handler, pos * 7, pos * 17));
        entityManager.addEntity(new BigTree(handler, pos * 13, pos * 14));

        //bush
        entityManager.addEntity(new Bush(handler, pos * 1  , pos * 3));
        entityManager.addEntity(new Bush(handler, pos *  1 , pos * 4));
        entityManager.addEntity(new Bush(handler, pos * 4  , pos * 2));
        entityManager.addEntity(new Bush(handler, pos * 10  , pos * 2));
        entityManager.addEntity(new Bush(handler, pos * 16  , pos * 18));
        entityManager.addEntity(new Bush(handler, pos * 17  , pos * 21));
        entityManager.addEntity(new Bush(handler, pos *  3 , pos * 22));
        entityManager.addEntity(new Bush(handler, pos *  14 , pos * 26 ));

        //rock
        entityManager.addEntity(new Rock(handler, pos * 20 ,pos * 3));
        entityManager.addEntity(new Rock(handler, pos * 22 ,pos * 4));
        entityManager.addEntity(new Rock(handler, pos * 22 ,pos * 5));
        entityManager.addEntity(new Rock(handler, pos * 17 ,pos * 5));
        entityManager.addEntity(new Rock(handler, pos * 18 ,pos * 5));
        entityManager.addEntity(new Rock(handler, pos * 28 ,pos * 10));
        entityManager.addEntity(new Rock(handler, pos * 20 ,pos * 11));
        entityManager.addEntity(new Rock(handler, pos * 9 ,pos * 14));
        entityManager.addEntity(new Rock(handler, pos * 9 ,pos * 14));
        entityManager.addEntity(new Rock(handler, pos * 10 ,pos * 15));
        entityManager.addEntity(new Rock(handler, pos * 22 ,pos * 15));
        entityManager.addEntity(new Rock(handler, pos * 19 ,pos * 16));
        entityManager.addEntity(new Rock(handler, pos * 21 ,pos * 17));
        entityManager.addEntity(new Rock(handler, pos * 25 ,pos * 18));
        entityManager.addEntity(new Rock(handler, pos * 19 ,pos * 19));
        entityManager.addEntity(new Rock(handler, pos * 20 ,pos * 19));
        entityManager.addEntity(new Rock(handler, pos * 19 ,pos * 20));
        entityManager.addEntity(new Rock(handler, pos * 2 ,pos * 20));
        entityManager.addEntity(new Rock(handler, pos * 25 ,pos * 20));

        //monster horizontal
        entityManager.addEntity(new Monster(handler, pos * 13 , pos * 2, "horizontal", pos * 3 , 2));
        entityManager.addEntity(new Monster(handler, pos * 25 , pos * 2, "horizontal", pos , 2));
        entityManager.addEntity(new Monster(handler, pos * 1 , pos * 5, "horizontal", pos * 2 , 2));
        entityManager.addEntity(new Monster(handler, pos * 1 , pos * 7, "horizontal", pos * 7, 2));
        entityManager.addEntity(new Monster(handler, pos * 24, pos * 9, "horizontal", pos * 3, 2));
        entityManager.addEntity(new Monster(handler, pos * 13, pos * 10, "horizontal", pos, 2));
        entityManager.addEntity(new Monster(handler, pos * 10, pos * 13, "horizontal", pos * 5, 2));
        entityManager.addEntity(new Monster(handler, pos * 4, pos * 16, "horizontal", pos * 5, 2));
        entityManager.addEntity(new Monster(handler, pos * 22, pos * 16, "horizontal", pos * 3, 2));
        entityManager.addEntity(new Monster(handler, pos * 18, pos * 18, "horizontal", pos * 5, 2));
        entityManager.addEntity(new Monster(handler, pos * 5, pos * 19, "horizontal", pos * 4, 2));
        entityManager.addEntity(new Monster(handler, pos * 27, pos * 19, "horizontal", pos, 2)); //range 2
        entityManager.addEntity(new Monster(handler, pos * 1, pos * 19, "horizontal", pos , 2)); //range 3
        entityManager.addEntity(new Monster(handler, pos * 17, pos * 22, "horizontal", pos * 2, 2));
        entityManager.addEntity(new Monster(handler, pos * 14, pos * 27, "horizontal", pos * 2, 2)); // range 4
        entityManager.addEntity(new Monster(handler, pos * 19, pos * 27, "horizontal", pos * 3, 2));
        entityManager.addEntity(new Monster(handler, pos * 15, pos * 28, "horizontal", pos * 3, 2));

        //monster vertikal
        entityManager.addEntity(new Monster(handler, pos * 8, pos * 1, "vertical", pos , 2)); //range 3
        entityManager.addEntity(new Monster(handler, pos * 8, pos * 7, "vertical", pos * 6 , 2));
        entityManager.addEntity(new Monster(handler, pos * 16, pos * 9, "vertical", pos , 2));
        entityManager.addEntity(new Monster(handler, pos * 1, pos * 12, "vertical", pos * 3, 2));
        entityManager.addEntity(new Monster(handler, pos * 3, pos * 14, "vertical", pos * 3, 2));
        entityManager.addEntity(new Monster(handler, pos * 21, pos * 20, "vertical", pos *3, 2)); 
        entityManager.addEntity(new Monster(handler, pos * 24, pos * 20, "vertical", pos , 2));//range 3
        entityManager.addEntity(new Monster(handler, pos * 5, pos * 22, "vertical", pos , 2)); 
        entityManager.addEntity(new Monster(handler, pos * 7, pos * 22, "vertical", pos , 2)); //
        entityManager.addEntity(new Monster(handler, pos * 22, pos * 22, "vertical", pos * 2, 2)); //
        entityManager.addEntity(new Monster(handler, pos * 26, pos * 22, "vertical", pos , 2));//
        entityManager.addEntity(new Monster(handler, pos * 9, pos * 24, "vertical", pos , 2));//
        entityManager.addEntity(new Monster(handler, pos * 6, pos * 26, "vertical", pos , 2)); //
        entityManager.addEntity(new Monster(handler, pos * 10, pos * 26, "vertical", pos , 2)); //
        entityManager.addEntity(new Monster(handler, pos * 8, pos * 28, "vertical", pos , 2)); // 

        //key chest
        entityManager.addEntity(new KeyChest(handler, pos * 25 ,pos * 14));

        //coin chest
        entityManager.addEntity(new CoinChest(handler, pos * 19 ,pos * 1));
        entityManager.addEntity(new CoinChest(handler, pos * 27 ,pos * 1));
        entityManager.addEntity(new CoinChest(handler, pos * 6 ,pos * 2));
        entityManager.addEntity(new CoinChest(handler, pos * 19 ,pos * 6));
        entityManager.addEntity(new CoinChest(handler, pos * 10 ,pos * 9 + (pos/4)));
        entityManager.addEntity(new CoinChest(handler, pos * 1 ,pos * 10));
        entityManager.addEntity(new CoinChest(handler, pos * 10 ,pos * 10));
        entityManager.addEntity(new CoinChest(handler, pos * 25 ,pos * 11));
        entityManager.addEntity(new CoinChest(handler, pos * 21 ,pos * 15));
        entityManager.addEntity(new CoinChest(handler, pos * 1 ,pos * 18));
        entityManager.addEntity(new CoinChest(handler, pos * 28 ,pos * 21));
        entityManager.addEntity(new CoinChest(handler, pos * 3 ,pos * 23));
        entityManager.addEntity(new CoinChest(handler, pos * 25 ,pos * 24));
        entityManager.addEntity(new CoinChest(handler, pos * 12 ,pos * 26));
        entityManager.addEntity(new CoinChest(handler, pos * 27 ,pos * 26));

        //gate
        entityManager.addEntity(new Gate(handler, pos * 27, pos * 27));

    }

    public void loadWorldBeta(){
        entityManager.addEntity(new CoinChest(handler, pos * 3 ,pos * 1));
        entityManager.addEntity(new CoinChest(handler, pos * 4 ,pos * 1));
        entityManager.addEntity(new CoinChest(handler, pos * 5 ,pos * 1));
        entityManager.addEntity(new KeyChest(handler, pos * 6 ,pos * 1));
        entityManager.addEntity(new Monster(handler, pos * 1 , pos * 5, "horizontal", pos * 2 , 2));
        entityManager.addEntity(new Monster(handler, pos * 1, pos * 1, "vertical", pos , 2)); //range 3
        entityManager.addEntity(new Gate(handler, pos * 7, pos * 5));

    }
}

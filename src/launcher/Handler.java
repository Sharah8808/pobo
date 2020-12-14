package launcher;

import graphics.GameCamera;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

//class utk kirim 1 object aja, handler, n biar bsa akses objek game n world
public class Handler {
    
    private TreasureHunter game;
    private World world;

    public Handler(TreasureHunter game){
        this.game = game;
    }

    public GameCamera getGameCam(){
        return game.getGameCam();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public TreasureHunter getGame(){
        return game;
    }

    public World getWorld(){
        return world;
    }
    
    public void setGame(TreasureHunter game){
        this.game = game;
    }

    public void setWorld(World world){
        this.world = world;
    }

}

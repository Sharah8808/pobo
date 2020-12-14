package states;

import launcher.Handler;
import worlds.World;

import java.awt.Graphics;
import java.awt.event.KeyEvent;


public class GameState extends State {

    // private Player player;
    private World world;
    public MenuState menuState;
    protected boolean gameFinished = false;
    // private Tree tree;

    public GameState(Handler handler){
        super(handler);
    }

    public void startGame(){
        world = new World(handler, "resource/worlds/worldBeta.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {

        if(world != null) world.tick();

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            handler.getGame().pauseState.switched();
            State.setState(handler.getGame().pauseState);
        }
    }

    @Override
    public void render(Graphics g) {
        if(world != null) world.render(g);
    }

    public boolean isGameFinished(){
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished){
        this.gameFinished = gameFinished;
    }

    public void switched(){
        startGame();
    }
}

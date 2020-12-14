package states;

import launcher.Handler;

import java.awt.Graphics;

public abstract class State {
    
    //---
    //game state manage
    //memegang state skrng yg kt mw tick n render ke game
    private static State currentState = null;

    public static void setState(State state){
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }
    //----
    
    //class
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void setGameFinished(boolean playerDie) {
    }

    public abstract void switched();

    public void setName(String name){}

}

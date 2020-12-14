package states;

import launcher.Handler;
import graphics.Assets;

import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;

import database.Database;
import database.Highscore;

// import 
public class GameFinished extends State  {

    private String name;
    private ArrayList<Highscore> highscores;

    public GameFinished(Handler handler, String name) {
        super(handler);
        this.name = name;
        highscores = new ArrayList<Highscore>();

    }

    public void tick(){

        if(handler.getKeyManager().up){
            handler.getGame().menuState.switched();
            State.setState(handler.getGame().menuState);
        }
    }

    public void render(Graphics g){

        g.drawImage(Assets.bgAfterGame, 0, 0, 640, 480, null);
        g.drawImage(Assets.player_win, 430, 350, 70, 70, null);
        g.drawImage(Assets.monster_right[0], 340, 350,  70, 70, null);
        g.drawImage(Assets.monster_down[0], 530, 350,  70, 70, null);

        g.drawImage(Assets.win_text, 180, 80, 256, 80, null);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 15));
        g.drawString("You have found the gate!", 185, 170);

        setFrame();
    }
    
    public void switched(){

    }

    public void setFrame(){
        if(name == null) {
            name = JOptionPane.showInputDialog("Input your name");
            highscores.add(new Highscore(Integer.parseInt(handler.getWorld().getEntityManager().getPlayer().getScore())  , name));

            Database.saveHighscore(highscores.get(highscores.size() - 1).getScore(), highscores.get(highscores.size() - 1).getScorer());
            return;
        }       
        
    }

    public void setName(String name){
        this.name = name;
    }
}

package states;

import java.awt.Graphics;

import graphics.Assets;
import launcher.Handler;
import database.*;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class ScoreState extends State {
    
    private ArrayList<Highscore> highscores;

    public ScoreState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            State.setState(handler.getGame().menuState);

        switched();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bgMenu, 0, 0, 640, 480, null);

        g.drawImage(Assets.scoreBox, 60, 28, 512, 384, null);
        g.drawImage(Assets.name_text, 50, 70, 256, 80, null);
        g.drawImage(Assets.score_option[1], 300, 70, 256, 80, null);

        if(highscores.size() >= 1){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(highscores.get(0).getScorer(), 145, 200);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(Integer.toString(highscores.get(0).getScore()), 387, 200);
        }
        if(highscores.size() >= 2){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(highscores.get(1).getScorer(), 145, 250);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(Integer.toString(highscores.get(1).getScore()), 387, 250);
        }
        if(highscores.size() >= 3){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(highscores.get(2).getScorer(), 145, 300);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            g.drawString(Integer.toString(highscores.get(2).getScore()), 387, 300);
        }
    }

    public void switched(){
        highscores = Database.getHighscores();
    }
    
}

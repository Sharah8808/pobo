package states;

import java.awt.Graphics;

import graphics.Assets;

import launcher.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

public class MenuState extends State {
    
    private UIManager uiManager;
    
    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        //utk aplikasikan aksi klik ke button dri uiManager.addObject(new blabla), tnpa ini, buttonnya g bsa d klik
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(170, 150, 270, 80, Assets.start_option, new ClickListener(){

            @Override
            public void onClick() {
                //handler.getMouseManager().setUIManager(uiManager) ni matiin smua tombol/opsi di menustate utk mndapat input UI
                //tnpa line itu, input ui dari button/menu ttp ada di game wlaupun g bsa kelihat
                handler.getMouseManager().setUIManager(null);
                handler.getGame().gameState.switched();
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(175, 229, 256, 80, Assets.score_option, new ClickListener(){

            @Override
            public void onClick() {
                handler.getGame().scoreState.switched();
                State.setState(handler.getGame().scoreState);
            }
        }));

        uiManager.addObject(new UIImageButton(175, 309, 256, 80, Assets.exit_option, new ClickListener(){

            @Override
            public void onClick() {
                System.exit(1);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bgMenu, 0, 0, 640, 480, null);
        uiManager.render(g);

        g.drawImage(Assets.title_treasure, 110, 30, 200, 100, null);
        g.drawImage(Assets.title_hunter, 320, 30, 200, 100, null);

    }

    public void switched(){
        handler.getMouseManager().setUIManager(uiManager);
    }
}

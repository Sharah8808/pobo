package states;

import java.awt.Graphics;
import graphics.Assets;
import launcher.Handler;
import ui.UIManager;
import ui.ClickListener;
import ui.UIImageButton;

public class PauseState extends State {
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);

        uiManager.addObject(new UIImageButton(175, 150, 256, 80, Assets.resume_option, new ClickListener(){

            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(175, 229, 256, 80, Assets.exit_option, new ClickListener(){

            @Override
            public void onClick() {
                handler.getGame().menuState.switched();

                State.setState(handler.getGame().menuState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.bgPause, 0, -20, 640, 480, null);
        uiManager.render(g);
        
    }

    @Override
    public void switched() {
        handler.getMouseManager().setUIManager(uiManager);
    }
    
}

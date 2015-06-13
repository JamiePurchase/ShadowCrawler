package state;

import app.Application;
import input.InputKeyboardKey;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import menu.Menu;
import menu.MenuPause;
import world.Board;

public class StatePause extends State
{
    // Parent State
    Board board;
    BufferedImage background;
    
    // Pause Menu
    Menu menuState;
    
    public StatePause(BufferedImage bkg)
    {
        this.menuState = new MenuPause(this);
        this.background = bkg;
    }
    
    public Menu getMenuState()
    {
        return this.menuState;
    }

    public void keyPressed(InputKeyboardKey key)
    {
        if(key.getRef().equals("ESCAPE")) {Application.resumeState();}
        this.menuState.keyPressed(key);
    }

    public void keyReleased(InputKeyboardKey key)
    {
        this.menuState.keyReleased(key);
    }

    public void render(Graphics gfx)
    {
        this.menuState.render(gfx);
    }
    
    public void setMenuState(Menu menu)
    {
        this.menuState = menu;
    }

    public void terminate()
    {
        //
    }

    public void tick()
    {
        this.menuState.tick();
    }
}

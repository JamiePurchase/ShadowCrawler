package menu;

import app.Application;
import app.Console;
import gfx.Drawing;
import input.InputKeyboard;
import input.InputKeyboardKey;
import java.awt.Graphics;
import state.StateBoard;

public abstract class Menu
{
    // Board
    public StateBoard parent;
    
    // Options
    public int optCursor, optCursorTickNow, optCursorTickMax, optCursorFrameNow, optCursorFrameMax;
    public String[] optString;
    
    // Input
    public InputKeyboard keyboard;
    
    public Menu(StateBoard parent)
    {
        this.parent = parent;
        this.keyboard = Application.getInputKeyboard();
        
        // Options
        this.optCursor = 0;
        this.optCursorTickNow = 0;
        this.optCursorTickMax = 2;
        this.optCursorFrameNow = 1;
        this.optCursorFrameMax = 8;
        this.initOptions();
    }
    
    public void changeMenu(String menu)
    {
        this.parent.pauseMenu(menu);
    }
    
    public void exit()
    {
        this.parent.pauseDone();
    }
    
    public StateBoard getState()
    {
        return this.parent;
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        if(key.getRef().equals("ENTER") || key.getRef().equals("SPACE")) {this.tickSelect();}
        if(key.getRef().equals("UP")) {if(this.optCursor > 1) {this.optCursor -= 1;}}
        if(key.getRef().equals("DOWN")) {if(this.optCursor < this.optString.length) {this.optCursor += 1;}}
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }
    
    public abstract void initOptions();
    
    public abstract void render(Graphics gfx);
    
    public void renderFrame(Graphics gfx)
    {
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/menuPane1bkg.png"), 0, 0, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/menuPane1border.png"), 0, 0, null);
    }
    
    public void tick()
    {
        this.optCursorTickNow += 1;
        if(this.optCursorTickNow > this.optCursorTickMax)
        {
            this.optCursorTickNow = 0;
            this.optCursorFrameNow += 1;
            if(this.optCursorFrameNow > this.optCursorFrameMax)
            {
                this.optCursorFrameNow = 1;
            }
        }
    }
    
    public abstract void tickSelect();
    
}
package menu;

import app.Application;
import gfx.Drawing;
import input.InputKeyboard;
import java.awt.Graphics;
import state.StateBoard;

public abstract class Menu
{
    // Board
    public StateBoard parent;
    
    // Tick
    public int tickNow, tickMax;
    
    // Input
    public InputKeyboard keyboard;
    
    public Menu(StateBoard parent)
    {
        this.parent = parent;
        this.keyboard = Application.getInputKeyboard();
    }
    
    public StateBoard getState()
    {
        return this.parent;
    }
    
    public abstract void render(Graphics gfx);
    
    public void renderFrame(Graphics gfx)
    {
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/menuPane1bkg.png"), 0, 0, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/menuPane1border.png"), 0, 0, null);
    }
    
    public void setTick(int now, int max)
    {
        this.tickNow = now;
        this.tickMax = max;
    }
    
    public abstract void tick();
    {
        this.tickNow += 1;
        if(this.tickNow > this.tickMax) {this.tickNow = 0;}
    }
    
}
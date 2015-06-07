package menu;

import gfx.Drawing;
import java.awt.Graphics;

public abstract class Menu
{
    public int tickNow, tickMax;
    
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
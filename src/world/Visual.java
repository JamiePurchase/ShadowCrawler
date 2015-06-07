package world;

import java.awt.Graphics;

public abstract class Visual
{
    private int renderX, renderY;
    private int tickNow, tickEnd;
    private boolean done;
    
    public boolean getDone()
    {
        return this.done;
    }
    
    public int getRenderX()
    {
        return this.renderX;
    }
    
    public int getRenderY()
    {
        return this.renderY - this.tickNow;
    }
    
    public void setDone(boolean value)
    {
        this.done = value;
    }
    
    public void setPos(int x, int y)
    {
        this.renderX = x;
        this.renderY = y;
    }
    
    public void setTick(int start, int end)
    {
        this.tickNow = start;
        this.tickEnd = end;
    }

    public abstract void render(Graphics gfx);
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow > this.tickEnd)
        {
            this.done = true;
        }
    }
    
}
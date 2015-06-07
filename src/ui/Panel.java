package ui;

import app.Application;
import java.awt.Graphics;

public class Panel
{
    private int drawX, drawY, sizeX, sizeY;
    
    public Panel(int posX, int posY, int wide, int high)
    {
        this.drawX = posX;
        this.drawY = posY;
        this.sizeX = wide;
        this.sizeY = high;
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Application.getThemeColour("BACK"));
        gfx.fillRect(this.drawX, this.drawY, this.sizeX, this.sizeY);
        
        // Border
        gfx.setColor(Application.getThemeColour("FORE"));
        gfx.drawRect(this.drawX, this.drawY, this.sizeX, this.sizeY);
        gfx.drawRect(this.drawX + 1, this.drawY + 1, this.sizeX - 2, this.sizeY - 2);
    }
    
}
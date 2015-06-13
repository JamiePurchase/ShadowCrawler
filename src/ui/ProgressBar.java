package ui;

import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;

public class ProgressBar
{
    private int posY;
    private int value;
    
    public ProgressBar(int posY)
    {
        this.posY = posY;
        this.value = 0;
    }
    
    public void render(Graphics gfx)
    {
        // Background
        gfx.setColor(Theme.getColour("PROGRESSBKG"));
        gfx.fillRect(483, this.posY + 5, 400, 40);
        
        // Progress
        gfx.setColor(Theme.getColour("PROGRESSFORE"));
        gfx.fillRect(483, this.posY + 5, this.value * 4, 40);
        
        // Border
        gfx.drawImage(Drawing.getImage("interface/progressBar.png"), 478, this.posY, null);
    }
    
    public void setValue(int value)
    {
        this.value = value;
    }
    
}
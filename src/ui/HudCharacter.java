package ui;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;

public class HudCharacter
{
    public HudCharacter()
    {
        //
    }
    
    public void render(Graphics gfx)
    {
        renderPortrait(gfx);
        renderBarHealth(gfx);
        renderBarMystic(gfx);
        renderBarEnergy(gfx);
        renderBorder(gfx);
    }
    
    private void renderBorder(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("interface/hudPlayer.png"), 50, 600, null);
    }
    
    private void renderPortrait(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("portrait/" + "Jakken_Menu" + ".png"), 55, 605, null);
    }
    
    private void renderBarEnergy(Graphics gfx)
    {
        gfx.setColor(Color.GREEN);
        gfx.fillRect(245, 645, 400, 15);
    }
    
    private void renderBarHealth(Graphics gfx)
    {
        gfx.setColor(Color.RED);
        gfx.fillRect(245, 605, 400, 15);
    }
    
    private void renderBarMystic(Graphics gfx)
    {
        gfx.setColor(Color.BLUE);
        gfx.fillRect(245, 625, 400, 15);
    }
    
    public void tick()
    {
        
    }
    
}
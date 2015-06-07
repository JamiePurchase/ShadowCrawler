package world;

import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;

public class VisualDamage extends Visual
{
    private String amount;
    
    public VisualDamage(int amount, int renderX, int renderY)
    {
        // Debug
        System.out.println("Visual Damage Object created at " + renderX + "," + renderY);
        super.setPos(renderX, renderY);
        super.setTick(0, 12);
        super.setDone(false);
        this.amount = "" + amount;
    }
    
    public void render(Graphics gfx)
    {
        // NOTE: there should be different formats (like critical or healing)
        gfx.setColor(Theme.getColour("DAMAGEVISUAL"));
        
        // temp
        gfx.setColor(Color.WHITE);
        
        gfx.setFont(Theme.getFont("DAMAGEVISUAL"));
        gfx.drawString(this.amount, super.getRenderX(), super.getRenderY());
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
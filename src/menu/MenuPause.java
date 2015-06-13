package menu;

import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import state.StateBoard;

public class MenuPause extends Menu
{
    private StateBoard stateParent;
    
    public MenuPause(StateBoard parent)
    {
        stateParent = parent;
    }
    
    @Override
    public void render(Graphics gfx)
    {
        // Frame
        this.renderFrame(gfx);
        
        // Options
        this.renderOptions(gfx);
        
        // Party
        this.renderParty(gfx);
    }
    
    private void renderOptions(Graphics gfx)
    {
        
    }
    
    private void renderParty(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("interface/menuCharacterBorder.png"), 565, 95, null);
        gfx.drawImage(Drawing.getImage("portrait/" + "Jakken_Menu" + ".png"), 570, 100, null);
        gfx.setFont(Theme.getFont("MENUNAME"));
        gfx.setColor(Theme.getColour("TEXT"));
        Drawing.writeTextShadow(gfx, "Jakken", 775, 130);
        gfx.setFont(Theme.getFont("MENUSTATS"));
        gfx.drawString("1000", 800, 175);
    }

    @Override
    public void tick()
    {
        //
    }
    
}
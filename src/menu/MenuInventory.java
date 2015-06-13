package menu;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.PartyCharacter;
import state.StatePause;

public class MenuInventory extends Menu
{
    // TEMP
    private PartyCharacter character;
    
    public MenuInventory(StatePause pause)
    {
        super(pause);
        
        // TEMP
        character = new PartyCharacter("Jakken");
    }
    
    public void initOptions()
    {
        //
    }
    
    public void render(Graphics gfx)
    {
        // Frame
        this.renderFrame(gfx);
    }
    
    public void tickSelect()
    {
        
    }
    
}
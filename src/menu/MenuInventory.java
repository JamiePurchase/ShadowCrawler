package menu;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.PartyCharacter;
import state.StateBoard;

public class MenuInventory extends Menu
{
    // TEMP
    private PartyCharacter character;
    
    public MenuInventory(StateBoard board)
    {
        super(board);
        
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
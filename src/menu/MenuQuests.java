package menu;

import gfx.Theme;
import java.awt.Graphics;
import state.StateBoard;

public class MenuQuests extends Menu
{
    
    public MenuQuests(StateBoard board)
    {
        super(board);
    }
    
    public void initOptions()
    {
        this.optString = new String[2];
        this.optString[0] = "QUEST 1";
        this.optString[1] = "QUEST 2";
    }
    
    public void render(Graphics gfx)
    {
        this.renderList(gfx);
        this.renderView(gfx);
    }
    
    private void renderList(Graphics gfx)
    {
        gfx.setFont(Theme.getFont("MENUOPTION"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("QUEST NAME", 120, 140);
    }
    
    private void renderView(Graphics gfx)
    {
        gfx.setFont(Theme.getFont("MENUOPTION"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("Details", 720, 140);
    }
    
    public void tickSelect()
    {
        
    }
    
}
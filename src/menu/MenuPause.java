package menu;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import state.StateBoard;
import state.StatePause;

public class MenuPause extends Menu
{
    
    public MenuPause(StatePause state)
    {
        super(state);
    }
    
    public void initOptions()
    {
        this.optString = new String[6];
        this.optString[0] = "QUESTS";
        this.optString[1] = "INVENTORY";
        this.optString[2] = "EQUIPMENT";
        this.optString[3] = "ABILITIES";
        this.optString[4] = "OPTIONS";
        this.optString[5] = "DONE";
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
        // Options
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.setFont(Theme.getFont("MENUOPTION"));
        for(int opt = 0; opt < this.optString.length; opt++)
        {
            gfx.drawString(this.optString[opt], 200, 160 + (opt * 50));
        }
        
        // Cursor
        int cursorX = 120 + (this.optCursorFrameNow * 3);
        if(this.optCursorFrameNow > 4) {cursorX = 135 - ((this.optCursorFrameNow - 4) * 3);}
        gfx.drawImage(Drawing.flipImage(Drawing.getImage("interface/menuCursor1.png")), cursorX, 136 + (this.optCursor * 50), null);
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
    
    public void tickSelect()
    {
        System.out.println("Tick Select (optCursor = " + this.optCursor + ")");
        if(this.optCursor == 0) {this.parent.setMenuState(new MenuQuests(this.parent));}
        if(this.optCursor == 1) {this.parent.setMenuState(new MenuInventory(this.parent));}
        if(this.optCursor == 2) {this.parent.setMenuState(new MenuEquipment(this.parent));}
        if(this.optCursor == 3) {this.parent.setMenuState(new MenuAbilities(this.parent));}
        if(this.optCursor == 4) {this.parent.setMenuState(new MenuOptions(this.parent));}
        if(this.optCursor == 5) {Application.resumeState();}
    }
    
}
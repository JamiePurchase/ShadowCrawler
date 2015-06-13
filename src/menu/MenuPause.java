package menu;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import state.StateBoard;

public class MenuPause extends Menu
{
    private StateBoard stateParent;
    
    // Options
    private int optCursor,optCursorTickNow, optCursorTickMax, optCursorFrameNow, optCursorFrameMax;
    private String[] optString;
    
    public MenuPause(StateBoard board)
    {
        super(board);
        stateParent = parent;
        initOptions();
    }
    
    private void initOptions()
    {
        this.optCursor = 0;
        this.optCursorTickNow = 0;
        this.optCursorTickMax = 2;
        this.optCursorFrameNow = 1;
        this.optCursorFrameMax = 8;
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

    @Override
    public void tick()
    {
        this.tickAnimate();
        this.tickInput();
    }
    
    private void tickAnimate()
    {
        this.optCursorTickNow += 1;
        if(this.optCursorTickNow > this.optCursorTickMax)
        {
            this.optCursorTickNow = 0;
            this.optCursorFrameNow += 1;
            if(this.optCursorFrameNow > this.optCursorFrameMax)
            {
                this.optCursorFrameNow = 1;
            }
        }
    }
    
    private void tickInput()
    {
        if(keyboard.getKeyPressed() != "NONE")
        {
            if(keyboard.getKeyPressed() == "ENTER" || keyboard.getKeyPressed() == "SPACE")
            {
                keyboard.keyPressedDone();
                if(optCursor == 0) {stateParent.pauseMenu("QUESTS");}
                if(optCursor == 1) {stateParent.pauseMenu("INVENTORY");}
                if(optCursor == 2) {stateParent.pauseMenu("EQUIPMENT");}
                if(optCursor == 3) {stateParent.pauseMenu("ABILITIES");}
                if(optCursor == 4) {stateParent.pauseMenu("OPTIONS");}
                if(optCursor == 5) {stateParent.pauseDone();}
            }
            if(keyboard.getKeyPressed() == "UP")
            {
                keyboard.keyPressedDone();
                if(this.optCursor > 0) {this.optCursor -= 1;}
            }
            if(keyboard.getKeyPressed() == "DOWN")
            {
                keyboard.keyPressedDone();
                if(this.optCursor < (this.optString.length - 1)) {this.optCursor += 1;}
            }
        }
    }
    
}
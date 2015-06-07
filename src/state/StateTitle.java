package state;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import ui.HintBar;

public class StateTitle extends State
{
    private int cursorNow, cursorMax;
    private int cursorTickNow, cursorTickMax, cursorFrame;
    
    // Interface
    private HintBar uiInfo;
    
    public StateTitle()
    {
        this.cursorNow = 1;
        this.cursorMax = 3;
        this.cursorTickNow = 0;
        this.cursorTickMax = 6;
        this.cursorFrame = 1;
        this.uiInfo = this.getInfo();
    }
    
    public HintBar getInfo()
    {
        // NOTE: may want to locate the version and update data elsewhere, where it can be maintained
        String hint1 = "Shadow Crawler (v0.2) built with Java in Netbeans IDE 8.0.2.";
        String hint2 = "Last updated  06|06|2015";
        return new HintBar(hint1, hint2);
    }
    
    public void render(Graphics gfx)
    {
        // Background
        Drawing.fillScreen(gfx, 20, 75, 35);
        Drawing.fillScreen(gfx, 200, 200, 200); // temp to reduce edge issue with Jakken portrait
        gfx.drawImage(Drawing.getImage("logo/titleWide.png"), 0, 100, null);
        
        // Temp (Jakken)
        gfx.drawImage(Drawing.getImage("portrait/Jakken.png"), 40, 220, null);
        gfx.drawImage(Drawing.getImage("portrait/Jakken_Sword1.png"), 40, 220, null);
        
        // Info Bar
        this.uiInfo.render(gfx);
        
        // Options
        gfx.setFont(Theme.getFont("MENUOPTION"));
        gfx.setColor(Theme.getColour("TEXT"));
        Drawing.write(gfx, "NEW GAME", 1100, 400, "RIGHT", true);
        Drawing.write(gfx, "CONTINUE", 1100, 450, "RIGHT", true);
        Drawing.write(gfx, "QUIT", 1100, 500, "RIGHT", true);
        int cursorX = 1135;
        if(cursorFrame == 2) {cursorX = 1134;}
        if(cursorFrame == 4) {cursorX = 1136;}
        gfx.drawImage(Drawing.getImage("interface/menuCursor1.png"), cursorX, (this.cursorNow * 50) + 328, null);
    }
    
    public void tick()
    {
        // Cursor
        this.cursorTickNow += 1;
        if(this.cursorTickNow > this.cursorTickMax)
        {
            this.cursorTickNow = 0;
            this.cursorFrame += 1;
            if(this.cursorFrame > 4) {this.cursorFrame = 1;}
        }
        
        // State Input
        if(Application.getInputKeyboard().getKeyPressed() != "NONE")
        {
            if(Application.getInputKeyboard().getKeyPressed() == "ENTER")
            {
                Application.getInputKeyboard().keyPressedDone();
                /*int menuSelect = this.menu.getCursor();
                if(menuSelect == 0) {Application.setState(new StateGround());}
                if(menuSelect == 1) {Application.setState(new StateFlight());}
                if(menuSelect == 2) {Application.setState(new StateSettings());}
                if(menuSelect == 3) {System.exit(0);}*/
                Application.setState(new StateBoard());
            }
            if(Application.getInputKeyboard().getKeyPressed() == "UP")
            {
                Application.getInputKeyboard().keyPressedDone();
                if(this.cursorNow > 1) {this.cursorNow -= 1;}
            }
            if(Application.getInputKeyboard().getKeyPressed() == "DOWN")
            {
                Application.getInputKeyboard().keyPressedDone();
                if(this.cursorNow < this.cursorMax) {this.cursorNow += 1;}
            }
        }
    }
    
}
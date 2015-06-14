package state;

import app.Application;
import app.Console;
import gfx.Drawing;
import gfx.Theme;
import input.InputKeyboardKey;
import java.awt.Graphics;
import ui.HintBar;

public class StateTitle extends State
{
    // Cursor
    private int cursorNow, cursorMax;
    private int cursorTickNow, cursorTickMax, cursorFrame;
    
    // Interface
    private HintBar uiInfo;
    
    public StateTitle()
    {
        this.cursorNow = 1;
        this.cursorMax = 6;
        this.cursorTickNow = 0;
        this.cursorTickMax = 6;
        this.cursorFrame = 1;
        this.uiInfo = this.getInfo();
        Application.getAudio().playMusic("Title");
    }
    
    public HintBar getInfo()
    {
        return Application.versionHint();
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        if(key.getRef().equals("ENTER"))
        {
            //if(this.cursorNow == 1) {Application.setState(new StateBoard());}
            if(this.cursorNow == 1) {Application.setState(new StateScene());}
            //if(this.cursorNow == 2) {}
            //if(this.cursorNow == 3) {Application.setState(new StateTutorial());}
            //if(this.cursorNow == 4) {Application.setState(new StateLegends());}
            //if(this.cursorNow == 5) {Application.setState(new StateOptions());}
            if(this.cursorNow == 6) {System.exit(0);}
        }
        if(key.getRef().equals("UP")) {if(this.cursorNow > 1) {this.cursorNow -= 1;}}
        if(key.getRef().equals("DOWN")) {if(this.cursorNow < this.cursorMax) {this.cursorNow += 1;}}
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }
    
    public void render(Graphics gfx)
    {
        renderBackground(gfx);
        renderTitle(gfx);
        renderOptions(gfx);
        renderInterface(gfx);
    }
    
    private void renderBackground(Graphics gfx)
    {
        Drawing.fillScreen(gfx, "PARCHMENT");
    }
    
    private void renderInterface(Graphics gfx)
    {
        this.uiInfo.render(gfx);
    }
    
    private void renderOptions(Graphics gfx)
    {        
        // Temp (Jakken)
        gfx.drawImage(Drawing.getImage("portrait/Jakken.png"), 300, 220, null);
        gfx.drawImage(Drawing.getImage("portrait/Jakken_Sword1.png"), 300, 220, null);
        
        // Temp (Sofeli)
        gfx.drawImage(Drawing.getImage("portrait/Sofeli.png"), 0, 220, null);
        
        // Options
        gfx.setFont(Theme.getFont("MENUOPTION"));
        gfx.setColor(Theme.getColour("TEXT"));
        Drawing.write(gfx, "NEW GAME", 1100, 350, "RIGHT", true);
        Drawing.write(gfx, "CONTINUE", 1100, 400, "RIGHT", true);
        Drawing.write(gfx, "TUTORIAL", 1100, 450, "RIGHT", true);
        Drawing.write(gfx, "LEGENDS", 1100, 500, "RIGHT", true);
        Drawing.write(gfx, "OPTIONS", 1100, 550, "RIGHT", true);
        Drawing.write(gfx, "QUIT", 1100, 600, "RIGHT", true);
        int cursorX = 1135;
        if(cursorFrame == 2) {cursorX = 1134;}
        if(cursorFrame == 4) {cursorX = 1136;}
        gfx.drawImage(Drawing.getImage("interface/menuCursor1.png"), cursorX, (this.cursorNow * 50) + 278, null);
    }
    
    private void renderTitle(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("logo/titleWide.png"), 0, 85, null);
    }
    
    public void terminate()
    {
        Application.getAudio().stopMusic();
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
    }
    
}
package state;

import app.Application;
import editor.Editor;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import menu.Menu;
import menu.MenuEquipment;
import player.Campaign;
import world.Board;
import world.BoardDao;

public class StateBoard extends State
{
    private Board board;
    
    // Pause Menu
    private boolean pause;
    private BufferedImage pauseBkg;
    private Menu pauseMenu;
    
    // Chat Display
    private boolean chatRender;
    
    public StateBoard()
    {
        this.board = BoardDao.loadBoard("FILE", false);
        this.board.setInput(Application.getInputKeyboard(), Application.getInputMouse());
        
        // Pause Menu
        this.pause = false;
        this.pauseBkg = null;
        
        // Chat Display
        this.chatRender = false;
        
        // Temp
        Application.setCampaign(new Campaign());
    }
    
    public void pauseDone()
    {
        this.pause = false;
        this.pauseMenu = null;
    }
    
    public void pauseInit()
    {
        this.pause = true;
        this.pauseMenu = new MenuEquipment(this);
    }
    
    public void render(Graphics gfx)
    {
        if(this.pause) {this.renderMenu(gfx);}
        else
        {
            this.board.render(gfx);
            //this.renderDisplay(gfx); // NOTE: this needs ALOT of thought and improvment
            if(this.chatRender) {this.renderChat(gfx);}
        }
    }
    
    public void renderChat(Graphics gfx)
    {
        // NOTE: with being DRY in mind, this piece of ui should have its own class
        
        // Portrait
        gfx.drawImage(Drawing.getImage("portrait/Jakken_Chat.png"), 140, 441, null);
        
        // Pane
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/chatPane1bkg.png"), 0, 568, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/chatPane1border.png"), 0, 568, null);
        
        // Speaker
        gfx.setFont(Theme.getFont("CHATSPEAKER"));
        gfx.setColor(Theme.getColour("TEXTSHADOW"));
        gfx.drawString("JAKKEN", 147, 626);
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("JAKKEN", 145, 625);
        // NOTE: the shadow effect is not currently available alongside alignment (improve these)
        
        // Speech
        gfx.setFont(Theme.getFont("CHATSPEECH"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("This is the chat window... here is the text.", 130, 665);
    }
    
    public void renderDisplay(Graphics gfx)
    {
        // Skill Display
        
        // Name Background
        gfx.setColor(Drawing.getColorRGB(100, 100, 100));
        gfx.fillRect(1060, 670, 200, 30);
        
        // Name Text
        gfx.setFont(Theme.getFont("STANDARD"));
        gfx.setColor(Theme.getColour("TEXT"));
        Drawing.write(gfx, "JAKKEN", 1160, 690, "CENTER");
        
        // NOTE: these setColor functions need to use the Theme class
        
        // Health Bar
        gfx.setColor(Color.RED);
        gfx.fillRect(1060, 700, 200, 20);
        gfx.setColor(Color.WHITE);
        gfx.drawRect(1060, 700, 200, 20);
        
        // Energy Bar
        gfx.setColor(Color.GREEN);
        gfx.fillRect(1060, 720, 200, 20);
        gfx.setColor(Color.WHITE);
        gfx.drawRect(1060, 720, 200, 20);
        
        // Mystic Bar
        gfx.setColor(Color.BLUE);
        gfx.fillRect(1060, 740, 200, 20);
        gfx.setColor(Color.WHITE);
        gfx.drawRect(1060, 740, 200, 20);
    }
    
    public void renderMenu(Graphics gfx)
    {
        // Background
        gfx.drawImage(this.board.getTerrainImage(), 0, 0, null);
        
        // Pause Menu
        this.pauseMenu.render(gfx);
    }
    
    public void tick()
    {
        if(this.pause) {this.pauseMenu.tick();}
        else {tickBoard();}
    }
    
    public void tickBoard()
    {
        // Keystrokes
        if(Application.getInputKeyboard().getKeyPressed() != "NONE") {this.tickBoardKey();}
        
        // Board Tick
        this.board.tick();
    }
    
    public void tickBoardKey()
    {
        // Enter (Pause Menu)
        if(Application.getInputKeyboard().getKeyPressed() == "ENTER")
        {
            Application.getInputKeyboard().keyPressedDone();
            this.pauseInit();
        }
        
        // Player Actions (if not busy)
        if(!this.board.tempPlayer.getBusy())
        {
            // Space (attack)
            if(Application.getInputKeyboard().getKeyPressed() == "SPACE")
            {
                Application.getInputKeyboard().keyPressedDone();
                this.board.tempPlayer.setAction("ATTACK");
            }
        }
    }
    
}
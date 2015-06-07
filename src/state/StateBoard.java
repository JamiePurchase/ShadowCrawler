package state;

import app.Application;
import editor.Editor;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.Campaign;
import ui.Menu;
import world.Board;
import world.BoardDao;

public class StateBoard extends State
{
    private Board board;
    
    // Pause Menu
    private boolean pause;
    private BufferedImage pauseBkg;
    private Menu pauseMenu;
    private String pauseState;
    
    // Chat Display
    private boolean chatRender;
    
    public StateBoard()
    {
        this.board = BoardDao.loadBoard("FILE", false);
        this.board.setInput(Application.getInputKeyboard(), Application.getInputMouse());
        
        // Pause Menu
        this.pause = false;
        this.pauseBkg = null;
        this.pauseMenu = null;
        this.pauseState = null;
        
        // Chat Display
        this.chatRender = false;
        
        // Temp
        Application.setCampaign(new Campaign());
    }
    
    public void pauseDone()
    {
        this.pause = false;
        this.pauseMenu = null;
        this.pauseState = null;
    }
    
    public void pauseInit()
    {
        this.pause = true;
        this.pauseMenu = new Menu();
        this.pauseState = "CHARACTER";
    }
    
    public void render(Graphics gfx)
    {
        if(this.pause) {this.renderMenu(gfx);}
        else {this.board.render(gfx);}
        
        // Temp
        this.renderDisplay(gfx);
        if(this.chatRender) {this.renderChat(gfx);}
    }
    
    public void renderChat(Graphics gfx)
    {
        // Portrait
        gfx.drawImage(Drawing.getImage("portrait/Jakken_Chat.png"), 140, 441, null);
        
        // Pane
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/chatPane1bkg.png"), 0, 568, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/chatPane1border.png"), 0, 568, null);
        
        // Speaker
        gfx.setFont(Theme.getFont("CHATSPEAKER"));
        
        // Temp
        gfx.setColor(Theme.getColour("TEXTSHADOW"));
        gfx.drawString("JAKKEN", 147, 626);
        
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("JAKKEN", 145, 625);
        
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
        
        // Pane
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/menuPane1bkg.png"), 0, 0, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/menuPane1border.png"), 0, 0, null);
        
        // Contents
        if(this.pauseState.equals("CHARACTER")) {this.renderMenuCharacter(gfx);}
    }
    
    public void renderMenuCharacter(Graphics gfx)
    {
        // Name
        gfx.setColor(Color.WHITE);
        Drawing.write(gfx, "JAKKEN", 600, 100, "CENTER");
        
        // Portrait
        gfx.drawImage(Drawing.getImage("portrait/Jakken.png"), 400, 100, null);
        gfx.drawImage(Drawing.getImage("portrait/Jakken_Sword1.png"), 400, 100, null);
    }
    
    public void tick()
    {
        if(this.pause) {tickPause();}
        else {tickBoard();}
    }
    
    public void tickBoard()
    {
        // Keystrokes
        if(Application.getInputKeyboard().getKeyPressed() != "NONE") {this.tickBoardKey();}
        
        // Board Tick
        if(!this.pause) {this.board.tick();}
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
    
    public void tickPause()
    {
        // Enter (Pause Menu)
        if(Application.getInputKeyboard().getKeyPressed() == "ENTER")
        {
            Application.getInputKeyboard().keyPressedDone();
            this.pauseDone();
        }
        
        // Menu Tick
        //this.pauseMenu.tick();
    }
    
}
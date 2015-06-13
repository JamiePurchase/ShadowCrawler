package state;

import app.Application;
import editor.Editor;
import gfx.Drawing;
import gfx.Theme;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import menu.Menu;
import menu.MenuEquipment;
import menu.MenuInventory;
import menu.MenuOptions;
import menu.MenuPause;
import menu.MenuQuests;
import player.Campaign;
import ui.HudCharacter;
import ui.MessageBar;
import ui.MessageBarChat;
import world.Board;
import world.BoardDao;

public class StateBoard extends State
{
    private Board board;
    
    // Pause Menu
    private boolean pause;
    private BufferedImage pauseBkg;
    private Menu pauseMenu;
    
    // HUD Display
    private boolean hudRender;
    private HudCharacter hudCharacter;
    
    // Message Bar
    private MessageBar chatMessage;
    private boolean chatRender;
    
    public StateBoard()
    {
        this.board = BoardDao.loadBoard("FILE", false);
        this.board.setInput(Application.getInputKeyboard(), Application.getInputMouse());
        this.board.setScroll(0, 0);
        //this.board.setScrollPlayer();
        
        // Pause Menu
        this.pause = false;
        this.pauseBkg = null;
        
        // HUD Display
        this.hudRender = true;
        this.hudCharacter = new HudCharacter();
        
        // Message Bar
        this.chatMessage = null;
        this.chatRender = false;
        
        // Temp
        Application.setCampaign(new Campaign());
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        if(this.pause) {this.pauseMenu.keyPressed(key);}
        else
        {
            if(key.getRef().equals("ENTER")) {this.pauseInit();}
            this.board.keyPressed(key);
        }
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        if(this.pause) {this.pauseMenu.keyReleased(key);}
        else {this.board.keyReleased(key);}
    }
    
    public void pauseDone()
    {
        this.pause = false;
        this.pauseMenu = null;
    }
    
    public void pauseInit()
    {
        this.pause = true;
        this.pauseMenu = new MenuPause(this);
        //this.pauseMenu = new MenuEquipment(this);
    }
    
    public void pauseMenu(String menu)
    {
        if(menu == "PAUSE") {this.pauseMenu = new MenuPause(this);}
        if(menu == "QUEST") {this.pauseMenu = new MenuQuests(this);}
        if(menu == "INVENTORY") {this.pauseMenu = new MenuInventory(this);}
        if(menu == "EQUIPMENT") {this.pauseMenu = new MenuEquipment(this);}
        if(menu == "OPTIONS") {this.pauseMenu = new MenuOptions(this);}
    }
    
    public void render(Graphics gfx)
    {
        if(this.pause) {this.renderMenu(gfx);}
        else
        {
            this.board.render(gfx);
            if(this.hudRender) {this.renderDisplay(gfx);}
            if(this.chatRender) {this.renderChat(gfx);}
        }
    }
    
    public void renderChat(Graphics gfx)
    {
        this.chatMessage.render(gfx);
    }
    
    public void renderDisplay(Graphics gfx)
    {
        // Character
        this.hudCharacter.render(gfx);
        
        // Equipment
    }
    
    public void renderDisplayOld(Graphics gfx)
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
    
    public void setChat()
    {
        this.chatMessage = null;
        this.chatRender = false;
    }
    
    public void setChat(String title, String text1, String text2)
    {
        this.chatMessage = new MessageBar(title, text1, text2);
        this.chatRender = true;
    }
    
    public void setChat(String title, String text1, String text2, String portrait)
    {
        this.chatMessage = new MessageBarChat(title, text1, text2, portrait);
        this.chatRender = true;
    }
    
    public void terminate()
    {
        // temp
        Application.getAudio().stopMusic();
    }
    
    public void tick()
    {
        if(this.pause) {this.pauseMenu.tick();}
        else {this.board.tick();}
    }
    
}
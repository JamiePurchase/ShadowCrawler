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
import ui.FormationMenu;
import ui.HudActionHint;
import ui.HudCharacter;
import ui.HudMinimap;
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
    private boolean pauseDisabled;
    private int pauseDisabledTickNow, pauseDisabledTickMax;
    
    // HUD Display
    private boolean hudRender;
    private HudCharacter hudCharacter;
    private HudMinimap hudMinimap;
    private HudActionHint hudActionHint;
    
    // Message Bar
    private MessageBar chatMessage;
    private boolean chatRender;
    
    // Formation Menu
    private FormationMenu formationMenu;
    private boolean formationMenuActive;
    
    public StateBoard()
    {
        this.board = BoardDao.loadBoard("FILE", false);
        this.board.setInput(Application.getInputKeyboard(), Application.getInputMouse());
        this.board.setScroll(0, 0);
        //this.board.setScrollPlayer();
        
        // Pause Menu
        this.pause = false;
        this.pauseBkg = null;
        this.pauseDisabled = true;
        this.pauseDisabledTickNow = 0;
        this.pauseDisabledTickMax = 12;
        
        // HUD Display
        this.hudRender = true;
        this.hudCharacter = new HudCharacter(this.board, Application.getCampaign());
        this.hudMinimap = new HudMinimap(this.board, Application.getCampaign());
        this.hudActionHint = new HudActionHint(this.board, Application.getCampaign());
        
        // Message Bar
        this.chatMessage = null;
        this.chatRender = false;
        
        // Formation Menu
        this.formationMenu = new FormationMenu(this.board, Application.getCampaign());
        this.formationMenuActive = false;
    }
    
    public void formationEdit()
    {
        this.formationMenuActive = true;
    }
    
    public void formationDone()
    {
        this.formationMenuActive = false;
    }
    
    public HudActionHint getHudActionHint()
    {
        return this.hudActionHint;
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        // ESCAPE: open the world map (temp)
        if(key.getRef().equals("ESCAPE")) {Application.setState(new StateMap(), true);}

        // ENTER: open the pause menu
        if(key.getRef().equals("ENTER") && !this.pauseDisabled)
        {
            Application.setState(new StatePause(this.board.getTerrainImage()));
            //this.pauseInit();
        }

        // PAGEUP/PAGEDOWN: switch control between different allies
        if(this.board.getEntityAllyCount() > 1)
        {
            if(key.getRef().equals("PAGEUP")) {this.board.changePlayer(true);}
            if(key.getRef().equals("PAGEDOWN")) {this.board.changePlayer(false);}
            if(key.getRef().equals("HOME")) {this.formationEdit();}
        }

        // Board Key Function
        this.board.keyPressed(key);
        
        // Formation Menu
        if(this.formationMenuActive) {this.formationMenu.keyPressed(key);}
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        this.board.keyReleased(key);
        if(this.formationMenuActive) {this.formationMenu.keyReleased(key);}
    }
    
    public void render(Graphics gfx)
    {
        if(this.pause) {this.renderMenu(gfx);}
        else
        {
            this.renderBorder(gfx);
            this.board.render(gfx);
            if(this.hudRender) {this.renderDisplay(gfx);}
            if(this.chatRender) {this.renderChat(gfx);}
            if(this.formationMenuActive) {this.formationMenu.render(gfx);}
        }
    }
    
    public void renderBorder(Graphics gfx)
    {
        Drawing.fillScreen(gfx, "MARGIN");
        /*gfx.setColor(Color.BLACK);
        gfx.drawRect(10, 15, 1346, 738);*/
    }
    
    public void renderChat(Graphics gfx)
    {
        this.chatMessage.render(gfx);
    }
    
    public void renderDisplay(Graphics gfx)
    {
        // Character
        //this.hudCharacter.render(gfx);
        
        // Equipment
        
        // Minimap
        this.hudMinimap.render(gfx);
        
        // Action Hint
        this.hudActionHint.render(gfx);
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
        if(this.pauseDisabled)
        {
            this.pauseDisabledTickNow += 1;
            if(this.pauseDisabledTickMax  >= this.pauseDisabledTickMax)
            {
                this.pauseDisabled = false;
            }
        }
        if(this.pause) {this.pauseMenu.tick();}
        else {this.board.tick();}
    }
    
}
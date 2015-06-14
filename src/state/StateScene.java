package state;

import app.Application;
import gfx.Drawing;
import gfx.Tileset;
import input.InputKeyboardKey;
import java.awt.Graphics;
import ui.MessageBarChat;
import world.Board;
import world.EntityPlayer;

public class StateScene extends State
{
    // Board
    private Board board;
    
    // Actors
    private EntityPlayer actorJakken, actorSofeli;
    
    // Chat
    private MessageBarChat chatObject;
    private boolean chatActive;
    
    // Time
    private int sceneStage;
    private int tickNow, tickMax;
    
    public StateScene()
    {
        // Board
        this.board = new Board();
        
        // Actors
        this.actorJakken = new EntityPlayer("JAKKEN", this.board, 512, 256, new Tileset("spr|chr|Jakken", Drawing.getImage("spritesheet/character/Jakken/Jakken.png"), 64, 64, 13, 42));
        this.actorSofeli = new EntityPlayer("SOFELI", this.board, 768, 336, new Tileset("spr|chr|Sofeli", Drawing.getImage("spritesheet/character/Sofeli/Sofeli.png"), 64, 64, 13, 42));
        
        // Chat
        this.chatObject = null;
        this.chatActive = false;
        
        // Time
        this.sceneStage = 0;
        this.setTick(90);
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }
    
    public void render(Graphics gfx)
    {
        renderBackground(gfx);
        renderActors(gfx);
        if(this.chatActive) {this.chatObject.render(gfx);}
    }
    
    private void renderActors(Graphics gfx)
    {
        //this.actorJakken.renderAt(gfx, 400, 300);
        this.actorJakken.render(gfx);
        this.actorSofeli.render(gfx);
    }
    
    private void renderBackground(Graphics gfx)
    {
        Drawing.fillScreen(gfx, 190, 235, 125);
        gfx.drawImage(Drawing.getImage("scene/Q1.png"), 0, 0, null);
    }
    
    public void sceneAdvance()
    {
        if(this.sceneStage == 1)
        {
            Application.getAudio().playSound("Q1S1");
            this.chatObject = new MessageBarChat("Sofeli", "Wake up, you lazy sack of shit...","There's a letter from the boss.","Sofeli");
            this.chatActive = true;
            this.setTick(90);
        }
        if(this.sceneStage == 2)
        {
            this.chatActive = false;
        }
    }
    
    private void setTick(int max)
    {
        this.tickNow = 0;
        this.tickMax = max;
        // NOTE: need to use timestamps to say things like activate in ten seconds
    }
    
    public void terminate()
    {
        //
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow > this.tickMax)
        {
            this.tickNow = 0;
            this.sceneStage += 1;
            this.sceneAdvance();
        }
    }
    
}
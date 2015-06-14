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
    
    // Scene
    private int sceneStage;
    private float sceneFade;
    private boolean sceneFadeTransition;
    
    // Tick
    private int tickNow, tickMax;
    private boolean tickActive;
    
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
        
        // Scene
        this.sceneStage = 0;
        this.sceneFade = 1.00f;
        this.sceneFadeTransition = false;
        
        // Tick
        this.tickNow = 0;
        this.tickMax = 0;
        this.tickActive = false;
        
        // Scene
        this.sceneAdvance();
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
        if(this.sceneFade > 0.8f) {renderFade(gfx);}
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
    
    private void renderFade(Graphics gfx)
    {
        Drawing.drawImageOpaque(gfx, Drawing.getImage("scene/fade.png"), 0, 0, this.sceneFade);
    }
    
    public void sceneAdvance()
    {
        if(this.sceneStage == 0)
        {
            this.setTick(90);
        }
        if(this.sceneStage == 1)
        {
            Application.getAudio().playSound("S1D1");
            this.setTick(50);
        }
        if(this.sceneStage == 2)
        {
            this.setChatDone();
            this.sceneFadeTransition = true;
            this.setTick(20);
        }
        if(this.sceneStage == 3)
        {
            this.sceneFadeTransition = false;
            this.setChat("Sofeli", "S1D2", "Wake up, you lazy sack of shit...","There's a letter from the boss.","Sofeli");
            this.setTick(50);
            // NOTE: it may be wise to have an independant tick count on the chat display
            // so that waiting for it to close doesn't get in the way
            // NOTE: it may even be worth checking that the sound has finished playing
            // and then closing the chat window a moment afterwards
        }
        if(this.sceneStage == 4)
        {
            this.setChatDone();
            this.setTick(40);
        }
        if(this.sceneStage == 5)
        {
            Application.setState(new StateBoard());
            // NOTE: should use the boardDAO and other classes to ensure all campaign data is loaded
            // however most of the loading should have taken care of by the StateLoader
        }
    }
    
    private void setChat(String speaker, String sound, String text1, String text2, String portrait)
    {
        Application.getAudio().playSound(sound);
        this.chatObject = new MessageBarChat(speaker, text1, text2, portrait);
        this.chatActive = true;
    }
    
    private void setChatDone()
    {
        this.chatActive = false;
    }
    
    private void setTick(int max)
    {
        this.tickNow = 0;
        this.tickMax = max;
        this.tickActive = true;
        // NOTE: need to use timestamps to say things like activate in ten seconds
    }
    
    public void terminate()
    {
        //
    }
    
    public void tick()
    {
        if(this.tickActive) {this.tickTime();}
        if(this.sceneFadeTransition) {this.tickFade();}
    }
    
    public void tickFade()
    {
        if(sceneStage == 2)
        {
            this.sceneFade -= 0.02f;
            if(this.sceneFade <= 0.10f)
            {
                this.sceneFade = 0.00f;
                this.sceneFadeTransition = false;
            }
        }
    }
    
    public void tickTime()
    {
        this.tickNow += 1;
        if(this.tickNow > this.tickMax)
        {
            if(!Application.getAudio().getSoundActive())
            {
                this.tickNow = 0;
                this.tickActive = false;
                this.sceneStage += 1;
                this.sceneAdvance();
            }
        }
    }
    
}
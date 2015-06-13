package state;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import ui.ProgressBar;

public class StateInit extends State
{
    private ProgressBar loadBar;
    private String loadText;
    private int loadCountNow, loadCountMax;
    private boolean loadActive;
    private boolean loadDone;
    private int loadDoneTick;
    
    public StateInit()
    {
        this.loadBar = new ProgressBar(450);
        this.loadText = "HELLO";
        this.loadCountNow = 0;
        this.loadCountMax = 3;
        this.loadActive = false;
        this.loadDone = false;
        this.loadDoneTick = 0;
    }
    
    private int getLoadPercent()
    {
        return 100 / this.loadCountMax * this.loadCountNow;
    }
    
    private void load()
    {
        // Load Audio
        if(this.loadCountNow == 1)
        {
            // Debug
            System.out.println("State Init: Load Audio");
        
            Application.loadAudio();
            this.loadText = "Preparing Audio Files";
        }
        
        if(this.loadCountNow == 2)
        {
            // Debug
            System.out.println("State Init: Load Tiles");
        
            Application.loadTiles();
            this.loadText = "Organising Tilesets";
        }
        
        if(this.loadCountNow == 3)
        {
            // Debug
            System.out.println("State Init: Load Tiles");
        
            Application.loadTiles();
            this.loadText = "Organising Tilesets";
        }
    }
    
    private boolean loadDone()
    {
        System.out.println("State Init: Load Done? " + this.loadCountNow + " = " + Application.loadTilesDone());
        if(this.loadCountNow == 1) {return Application.loadAudioDone();}
        if(this.loadCountNow == 2)
        {
            System.out.println("State Init: Load Done? " + Application.loadTilesDone());
            return Application.loadTilesDone();
        }
        return false;
    }
    
    public void render(Graphics gfx)
    {
        // Background
        Drawing.fillScreen(gfx);
        gfx.drawImage(Drawing.getImage("logo/titleStack.png"), 351, 160, null);
        
        // Progress
        this.renderProgress(gfx);
    }
    
    private void renderProgress(Graphics gfx)
    {
        // Loading Bar
        loadBar.render(gfx);
        
        // Loading Text
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.setFont(Theme.getFont("PROGRESS"));
        Drawing.write(gfx, this.loadText, 683, 550, "CENTER");
    }
    
    public void terminate()
    {
        
    }
    
    public void tick()
    {
        if(this.loadDone) {tickDone();}
        else {tickLoad();}
    }
    
    public void tickDone()
    {
        this.loadDoneTick += 1;
        if(this.loadDoneTick == 10) {Application.setState(new StateTitle());}
    }
    
    public void tickLoad()
    {
        // Are we currently loading something?
        if(this.loadActive)
        {
            // Check for completion
            if(this.loadDone())
            {
                this.loadCountNow += 1;
                this.loadActive = false;
                this.loadBar.setValue(this.getLoadPercent());
            }
        }
        else
        {
            // Progress
            if(this.loadCountNow > this.loadCountMax)
            {
                // Loading complete
                this.loadDone = true;
                this.loadDoneTick = 0;
                this.loadText = "Load Complete";
            }
            else
            {
                // Load next
                this.loadActive = true;
                this.load();
            }
        }
    }
    
}
package state;

import app.Application;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Graphics;
import map.MapManager;

public class StateMap extends State
{
    private MapManager map;
    
    public StateMap()
    {
        this.map = new MapManager();
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        //if(key.getRef().equals("ESCAPE")) {Application.resumeState();}
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        //
    }
    
    public void render(Graphics gfx)
    {
        // Background
        Drawing.fillScreen(gfx, "MARGIN");
        
        // World Map
        this.map.render(gfx);
    }
    
    public void terminate()
    {
        //
    }
    
    public void tick()
    {
        this.map.tick();
    }
    
}
package editor;

import gfx.Drawing;
import gfx.Theme;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import state.State;

public class StateAudio extends State
{
    private ArrayList<String> music, sounds;
    
    public StateAudio()
    {
        
    }

    @Override
    public void render(Graphics gfx)
    {
        this.renderToolbar(gfx);
    }
    
    public void renderToolbar(Graphics gfx)
    {
        // Toolbar Background
        gfx.setColor(Drawing.getColorRGB(64, 128, 128));
        gfx.fillRect(0, 0, Editor.getAppWidth(), 36);
        gfx.setColor(Drawing.getColorRGB(96, 160, 160));
        gfx.fillRect(0, 35, Editor.getAppWidth(), 1);
        
        // Toolbar Text
        gfx.setColor(Drawing.getColorRGB(0, 0, 0));
        gfx.setFont(Theme.getFont("STANDARD"));
        gfx.drawString("NEW", 25, 30);
        gfx.drawString("OPEN", 125, 30);
        gfx.drawString("SAVE", 225, 30);
        gfx.drawString("SETTINGS", 325, 30);
        
        // Toolbar Icons
        gfx.setColor(Color.BLACK);
        gfx.fillRect(500, 5, 26, 26);
    }

    @Override
    public void terminate()
    {
        //
    }

    @Override
    public void tick()
    {
        //
    }
    
}
package world;

import gfx.Tileset;
import input.InputKeyboardKey;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityAction
{
    private Tileset file;
    private int tickNow, tickMax, frameNow, frameMax;
    private boolean repeat;
    private EntityAction resume;
    
    public EntityAction(Tileset tileset)
    {
        this.file = tileset;
    }
    
    public void render(Graphics gfx)
    {
        
    }
    
    public void tick()
    {
        
    }
    
}
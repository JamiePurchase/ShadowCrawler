package map;

import gfx.Theme;
import input.InputKeyboardKey;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MapManager
{
    // Interface
    private int mapRenderX, mapRenderY, mapRenderW, mapRenderH;
    private int mapCursorX, mapCursorY;
    private int mapScrollX, mapScrollY;
    
    // Entities
    private ArrayList<MapFeature> mapFeature;
    private ArrayList<MapScenery> mapScenery;
    
    // Graphics
    private BufferedImage mapImage;
    private boolean mapImageReady;
    private int mapTickNow, mapTickMax;
    
    public MapManager()
    {
        // Interface
        this.mapRenderX = 11;
        this.mapRenderY = 16;
        this.mapRenderW = 1344;
        this.mapRenderH = 736;
        this.mapCursorX = 0;
        this.mapCursorY = 0;
        this.mapScrollX = 0;
        this.mapScrollY = 0;
        
        // Entities
        this.mapFeature = new ArrayList<MapFeature>();
        this.mapScenery = new ArrayList<MapScenery>();
        
        // Graphics
        this.mapImage = null;
        this.mapImageReady = false;
        this.mapTickNow = 0;
        this.mapTickMax = 0;
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }
    
    public void render(Graphics gfx)
    {
        if(!this.mapImageReady)
        {
            this.mapImage = new BufferedImage(this.mapRenderW, this.mapRenderH, BufferedImage.TYPE_BYTE_INDEXED);
            Graphics mapImageGfx = mapImage.createGraphics();
            mapImageGfx.setColor(Theme.getColour("PARCHMENT"));
            mapImageGfx.fillRect(0, 0, this.mapRenderW, this.mapRenderH);
            /*for(int x = 0; x < this.getPaneCols(); x++)
            {
                for(int y = 0; y < this.getPaneRows(); y++)
                {
                    // If we have tiles for this section of the board
                    if(x + this.getScrollTileX() < this.terrain.length && y + this.getScrollTileY() < this.terrain[x].length)
                    {
                        if(!this.terrain[x + this.getScrollTileX()][y + this.getScrollTileY()].getBlank())
                        {
                            mapImageGfx.drawImage(this.terrain[x + this.getScrollTileX()][y + this.getScrollTileY()].getImage(), x * 32, y * 32, null);
                        }
                    }
                }
            }*/
            // NOTE: need to render each MapFeature and MapScenery object to this image
            this.mapImageReady = true;
        }
        gfx.drawImage(this.mapImage, this.mapRenderX, this.mapRenderY, null);
    }
    
    public void tick()
    {
        
    }
    
}
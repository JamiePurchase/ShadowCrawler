package world;

import gfx.Drawing;
import gfx.Tileset;
import input.InputKeyboard;
import input.InputMouse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board
{
    private String file;
    private String title;
    private int paneX, paneY, paneW, paneH;
    private int sizeX, sizeY;
    private Color background;
    private int scrollX, scrollY;
    private boolean editor;
    
    // Terrain
    private Tile terrain[][];
    private BufferedImage terrainImage;
    private boolean terrainImageReady;
    
    // Temp
    private int tempTickNow, tempTickMax, tempFrameNow, tempFrameMax;
    public Entity tempPlayer;
    
    // Entities
    private ArrayList<Entity> entityEnemies;
    private ArrayList<Visual> entityVisuals;
    
    // Input (reference here because they're different objects when running the editor)
    private InputKeyboard inputKeyboard;
    private InputMouse inputMouse;
    
    public Board()
    {
        this.file = "FILE";
        this.title = "TITLE";
        this.paneX = 11;
        this.paneY = 16;
        this.paneW = 1344;
        this.paneH = 752;
        this.sizeX = 42;
        this.sizeY = 23;
        this.background = Color.BLACK;
        this.terrain = new Tile[this.sizeX][this.sizeY];
        this.setTerrainAll(new Tile());
        this.scrollX = 0;
        this.scrollY = 0;
        this.editor = false;
        
        // Temp
        this.tempTickNow = 0;
        this.tempTickMax = 6;
        this.tempFrameNow = 1;
        this.tempFrameMax = 6;
        
        // Temp
        this.tempPlayer = new Entity("JAKKEN", this, 22, 16, new Tileset(Drawing.getImage("spritesheet/Jakken.png"), 64, 64, 13, 42));
        
        // Entities (enemies)
        this.entityEnemies = new ArrayList<Entity>();
        this.entityEnemies.add(new Entity("SKELETON1", this, 20, 15, new Tileset(Drawing.getImage("spritesheet/Skeleton.png"), 64, 64, 13, 42)));
        
        // Entities (visual)
        this.entityVisuals = new ArrayList<Visual>();
    }
    
    public void damageInflict(Damage damage)
    {
        for(int x = 0; x < damage.getSizeX(); x++)
        {
            for(int y = 0; y < damage.getSizeY(); y++)
            {
                damageInflictTile(damage, damage.getPosX() + x, damage.getPosY() + y);
            }
        }
    }
    
    public void damageVisual(int amount, int tileX, int tileY)
    {
        this.entityVisuals.add(new VisualDamage(amount, (tileX - this.getScrollX()) * 32, (tileY - this.getScrollY()) * 32));
    }
    
    private void damageInflictTile(Damage damage, int tileX, int tileY)
    {
        for(int e = 0; e < this.entityEnemies.size(); e++)
        {
            if(this.entityEnemies.get(e).getTileX() == tileX && this.entityEnemies.get(e).getTileY() == tileY)
            {
                if(!this.entityEnemies.get(e).getStatusKO()) {this.entityEnemies.get(e).inflictDamage(damage);}
            }
        }
    }
    
    private int getPaneCols()
    {
        return this.paneW / 32;
    }
    
    private int getPaneRows()
    {
        return this.paneH / 32;
    }
    
    public int getPaneX()
    {
        return this.paneX;
    }
    
    public int getPaneY()
    {
        return this.paneY;
    }
    
    public int getScrollX()
    {
        return this.scrollX;
    }
    
    public int getScrollY()
    {
        return this.scrollY;
    }
    
    public Tile getTerrain(int tileX, int tileY)
    {
        return this.terrain[tileX][tileY];
    }
    
    public BufferedImage getTerrainImage()
    {
        return this.terrainImage;
    }
    
    public boolean getTileValid(int tileX, int tileY)
    {
        if(this.terrain[tileX][tileY].getSolid()) {return false;}
        // NOTE: iterate through all entities and check if they are on this tile
        return true;
    }
    
    public void redrawTerrain()
    {
        this.terrainImageReady = false;
    }
    
    public void render(Graphics gfx)
    {
        this.renderBackground(gfx);
        this.renderTerrain(gfx);
        
        if(!this.editor)
        {
            // Temp
            //gfx.drawImage(new Tileset(Drawing.getImage("spritesheet/Jakken_Sword5.png"), 192, 192, 6, 4).getTileAt(this.tempFrameNow, 4), 50, 50, null);
            this.tempPlayer.render(gfx);

            if(this.entityEnemies.size() > 0) {this.renderEnemies(gfx);}
            if(this.entityVisuals.size() > 0) {this.renderVisuals(gfx);}
        }
        else {this.renderEditor(gfx);}
    }
    
    private void renderBackground(Graphics gfx)
    {
        gfx.setColor(this.background);
        gfx.fillRect(this.paneX, this.paneY, this.paneW, this.paneH);
    }
    
    private void renderDisplay(Graphics gfx)
    {
        //
    }
    
    private void renderEditor(Graphics gfx)
    {
        // NOTE: gridlines, solidity, events, etc...
    }
    
    private void renderEnemies(Graphics gfx)
    {
        for(int e = 0; e < this.entityEnemies.size(); e++)
        {
            this.entityEnemies.get(e).render(gfx);
        }
    }
    
    private void renderTerrain(Graphics gfx)
    {
        if(!this.terrainImageReady)
        {
            this.terrainImage = new BufferedImage(this.paneW, this.paneH, BufferedImage.TYPE_BYTE_INDEXED);
            Graphics terrainGfx = terrainImage.createGraphics();
            for(int x = 0; x < this.getPaneCols(); x++)
            {
                for(int y = 0; y < this.getPaneRows(); y++)
                {
                    if(!this.terrain[x + this.scrollX][y + this.scrollY].getBlank())
                    {
                        terrainGfx.drawImage(this.terrain[x + this.scrollX][y + this.scrollY].getImage(), x * 32, y * 32, null);
                    }
                }
            }
            this.terrainImageReady = true;
        }
        gfx.drawImage(this.terrainImage, this.paneX, this.paneY, null);
    }
    
    private void renderVisuals(Graphics gfx)
    {
        for(int v = 0; v < this.entityVisuals.size(); v++)
        {
            this.entityVisuals.get(v).render(gfx);
        }
    }
    
    public void setBackground(Color color)
    {
        this.background = color;
    }
    
    public void setEditor(boolean value)
    {
        this.editor = value;
    }
    
    public void setInput(InputKeyboard keyboard, InputMouse mouse)
    {
        this.inputKeyboard = keyboard;
        this.inputMouse = mouse;
    }
    
    public void setPane(int posX, int posY, int sizeX, int sizeY)
    {
        this.paneX = posX;
        this.paneY = posY;
        this.paneW = sizeX;
        this.paneH = sizeY;
    }
    
    public void setTerrain(int posX, int posY, Tile tile)
    {
        this.terrain[posX][posY] = tile.clone();
    }
    
    public void setTerrainAll(Tile tile)
    {
        for(int x = 0; x < this.sizeX; x++)
        {
            for(int y = 0; y < this.sizeY; y++)
            {
                this.setTerrain(x, y, tile);
            }
        }
    }
    
    public void tick()
    {
        // Temp
        this.tempTickNow += 1;
        if(this.tempTickNow > this.tempTickMax)
        {
            this.tempTickNow = 0;
            this.tempFrameNow += 1;
            if(this.tempFrameNow > this.tempFrameMax)
            {
                this.tempFrameNow = 1;
            }
        }
        
        // Entities
        if(!this.editor)
        {
            // Temp
            this.tickPlayer();

            // Entities
            this.tickEnemies();
            this.tickVisuals();
        }
        else {this.tickEditor();}
        
        // NOTE: animated scenery?
    }
    
    private void tickEditor()
    {
        // NOTE: may not need this
    }
    
    public void tickEnemies()
    {
        for(int e = 0; e < this.entityEnemies.size(); e++)
        {
            if(this.entityEnemies.get(e).getStatusKO()) {this.entityEnemies.remove(e);}
            else {this.entityEnemies.get(e).tick();}
        }
    }
    
    public void tickPlayer()
    {
        // Keys
        if(!this.tempPlayer.getBusy())
        {
            if(this.inputKeyboard.getKeyObject("DOWN").isPressed()) {this.tempPlayer.move("S");}
            else if(this.inputKeyboard.getKeyObject("LEFT").isPressed()) {this.tempPlayer.move("W");}
            else if(this.inputKeyboard.getKeyObject("RIGHT").isPressed()) {this.tempPlayer.move("E");}
            else if(this.inputKeyboard.getKeyObject("UP").isPressed()) {this.tempPlayer.move("N");}
            
            // Alt (hold to guard) - press to parry? skill?
            //if(this.inputKeyboard.getKeyObject("ALT").isPressed()) {this.tempPlayer.guard();}
        }
        else
        {
            if(this.tempPlayer.getAction().equals("GUARD"))
            {
                if(this.inputKeyboard.getKeyObject("ALT").isPressed()) {this.tempPlayer.guard();}
                else {this.tempPlayer.guardDone();}
            }
        }
        
        // Temp
        this.tempPlayer.tick();
    }
    
    private void tickVisuals()
    {
        for(int v = 0; v < this.entityVisuals.size(); v++)
        {
            if(this.entityVisuals.get(v).getDone()) {this.entityVisuals.remove(v);}
            else {this.entityVisuals.get(v).tick();}
        }
    }

}
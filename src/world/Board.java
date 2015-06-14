package world;

import app.Application;
import app.Console;
import gfx.Drawing;
import gfx.Tileset;
import input.InputKeyboard;
import input.InputKeyboardKey;
import input.InputMouse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import state.StateBoard;

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
    
    // Entities
    private int entityPlayer;
    private ArrayList<EntityPlayer> entityAllies;
    private ArrayList<EntityEnemy> entityEnemies;
    private ArrayList<EntityContainer> entityContainers;
    private ArrayList<Visual> entityVisuals;
    
    // Vectors
    private ArrayList<Vector> vectors;
    
    // Damage
    private ArrayList<DamageMarker> damageMarkers;
    
    // Input (reference here because they're different objects when running the editor)
    private InputKeyboard inputKeyboard;
    private InputMouse inputMouse;
    
    public Board()
    {
        this.file = "MantasTomb1";
        this.title = "TITLE";
        this.paneX = 11;
        this.paneY = 16;
        this.paneW = 1344;
        this.paneH = 736;
        this.sizeX = 42;
        this.sizeY = 22;
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
        
        // Entities: Allies
        this.entityAllies = new ArrayList<EntityPlayer>();
        EntityPlayer allyJakken = new EntityPlayer("JAKKEN", this, 512, 512, new Tileset("spr|chr|Jakken", Drawing.getImage("spritesheet/character/Jakken/Jakken.png"), 64, 64, 13, 42));
        allyJakken.setMesh(32, 32, -16, -32);
        this.entityAllies.add(allyJakken);
        
        // Temp
        EntityPlayer allySofeli = new EntityPlayer("SOFELI", this, 640, 512, new Tileset("spr|chr|Sofeli", Drawing.getImage("spritesheet/character/Sofeli/Sofeli.png"), 64, 64, 13, 42));
        allySofeli.setMesh(32, 32, -16, -32);
        this.entityAllies.add(allySofeli);
        
        // Entities: Player
        this.entityPlayer = 0;
        
        // Entities: Enemies
        this.entityEnemies = new ArrayList<EntityEnemy>();
        this.entityEnemies.add(new EntityEnemy("SKELETON1", this, 512, 576, new Tileset("spr|crt|Skeleton", Drawing.getImage("spritesheet/creature/Skeleton/Skeleton.png"), 64, 64, 13, 42)));
        
        // Entities: Containers
        this.entityContainers = new ArrayList<EntityContainer>();
        
        // Entities: Visual
        this.entityVisuals = new ArrayList<Visual>();
        
        // Vectors
        this.vectors = new ArrayList<Vector>();
        
        // Damage
        this.damageMarkers = new ArrayList<DamageMarker>();
    }
    
    public void addContainer(EntityContainer container)
    {
        this.entityContainers.add(container);
    }
    
    public void addContainer(String ref, int tileX, int tileY, String template)
    {
        this.entityContainers.add(ContainerDao.createContainer(ref, this, tileX * 32, tileY * 32, template));
    }
    
    public void addVector(String ref, Rectangle area, boolean solid)
    {
        this.vectors.add(new Vector(this, ref, area, solid));
    }
    
    public void addVector(String ref, Rectangle area, boolean solid, BoardJoin join)
    {
        this.vectors.add(new VectorJoin(this, ref, area, solid, join));
    }
    
    public void addVector(String ref, int posX, int posY, int sizeX, int sizeY, boolean solid)
    {
        this.addVector(ref, new Rectangle(posX * 32, posY * 32, sizeX * 32, sizeY * 32), solid);
    }
    
    public void addVector(String ref, int posX, int posY, int sizeX, int sizeY, boolean solid, BoardJoin join)
    {
        this.addVector(ref, new Rectangle(posX * 32, posY * 32, sizeX * 32, sizeY * 32), solid, join);
    }
    
    public void changePlayer(boolean plus)
    {
        int player = this.entityPlayer;
        if(plus)
        {
            player += 1;
            if(player == this.entityAllies.size()) {player = 0;}
        }
        else
        {
            player -= 1;
            if(player < 0) {player = this.entityAllies.size() - 1;}
        }
        this.setPlayer(player);
    }
    
    public void damageInflict(Damage damage)
    {
        //this.damageMarkers.add(new DamageMarker(damage.getRect()));
        
        // Debug
        DamageMarker dm = new DamageMarker(this, damage.getRect());
        this.damageMarkers.add(dm);
        Console.echoRed("Board -> damageInflict");
        Console.echo("x " + damage.getPosX() + ", y " + damage.getPosY() + ", w " + damage.getSizeX() + ", h " + damage.getSizeY());
        
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
        this.entityVisuals.add(new VisualDamage(amount, (tileX - this.getScrollTileX()) * 32, (tileY - this.getScrollTileY()) * 32));
    }
    
    private void damageInflictTile(Damage damage, int tileX, int tileY)
    {
        for(int e = 0; e < this.entityEnemies.size(); e++)
        {
            if(this.entityEnemies.get(e).getPosX() == tileX && this.entityEnemies.get(e).getPosY() == tileY)
            {
                if(!this.entityEnemies.get(e).getStatusKO()) {this.entityEnemies.get(e).inflictDamage(damage);}
            }
        }
        
        // Temp cracked wall
        if(tileX == 31 || tileX == 32)
        {
            if(tileY == 13 || tileY == 14)
            {
                Tileset crypt = new Tileset("tst|crypt", Drawing.getImage("tileset/crypt.png"));
                this.terrain[31][13].setImage(crypt.getTileAt(8, 2));
                this.terrain[32][13].setImage(crypt.getTileAt(9, 2));
                this.terrain[31][14].setImage(crypt.getTileAt(8, 3));
                this.terrain[32][14].setImage(crypt.getTileAt(9, 3));
                this.terrain[31][14].setSolid(false);
                this.terrain[32][14].setSolid(false);
                this.redrawTerrain();
                // NOTE: create a gateway
            }
        }
    }
    
    public Entity getEntityAlly(int id)
    {
        return this.entityAllies.get(id);
    }
    
    public int getEntityAllyCount()
    {
        return this.entityAllies.size();
    }
    
    public Entity getEntityEnemy(int id)
    {
        return this.entityEnemies.get(id);
    }
    
    public int getEntityEnemyCount()
    {
        return this.entityEnemies.size();
    }
    
    public String getFile()
    {
        return this.getFile(false);
    }
    
    public String getFile(boolean full)
    {
        if(full) {return "Board/" + this.file + ".froth";}
        return this.file;
    }
    
    public String getIntersect(Rectangle rect)
    {
        return this.getIntersect(rect, null);
    }
    
    public String getIntersect(Rectangle rect, String refIgnore)
    {
        // Allies
        String result = getIntersectAllies(rect, refIgnore);
        if(result != null) {return "ALY|" + result;}
        
        // Enemies
        result = getIntersectEnemies(rect, refIgnore);
        if(result != null) {return "ENE|" + result;}
        
        // Containers
        result = getIntersectContainers(rect);
        if(result != null) {return "CON|" + result;}
        
        // Vectors
        result = getIntersectVectors(rect, true);
        if(result != null) {return "VCT|" + result;}
        
        // No Intersection
        return null;
    }
    
    public String getIntersectAllies(Rectangle rect, String refIgnore)
    {
        for(int a = 0; a < entityAllies.size(); a++)
        {
            if(entityAllies.get(a).getMesh().intersects(rect))
            {
                if(refIgnore != null)
                {
                    if(!entityAllies.get(a).getRef().equals(refIgnore))
                    {
                        return entityAllies.get(a).getRef();
                    }
                }
                else {return entityAllies.get(a).getRef();}
            }
        }
        return null;
    }
    
    public String getIntersectContainers(Rectangle rect)
    {
        for(int c = 0; c < entityContainers.size(); c++)
        {
            if(entityContainers.get(c).getMesh().intersects(rect))
            {
                return entityContainers.get(c).getRef();
            }
        }
        return null;
    }
    
    public String getIntersectEnemies(Rectangle rect, String refIgnore)
    {
        for(int e = 0; e < entityEnemies.size(); e++)
        {
            if(entityEnemies.get(e).getMesh().intersects(rect))
            {
                if(refIgnore != null)
                {
                    if(!entityEnemies.get(e).getRef().equals(refIgnore))
                    {
                        return entityEnemies.get(e).getRef();
                    }
                }
                else {return entityEnemies.get(e).getRef();}
            }
        }
        return null;
    }
    
    public String getIntersectVectors(Rectangle rect, boolean physical)
    {
        for(int v = 0; v < vectors.size(); v++)
        {
            if(vectors.get(v).getVector().intersects(rect))
            {
                // Looking for a collision? (ignore other vectors while physical = true)
                if(physical) {if(vectors.get(v).getSolid()) {return vectors.get(v).getRef();}}
                else {return vectors.get(v).getRef();}
            }
        }
        return null;
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
    
    public EntityPlayer getPlayer()
    {
        return this.entityAllies.get(this.entityPlayer);
    }
    
    /**
     * @hint returns the exact position to draw to the screen
     * @param boardX coords relative to the board
     * @return 
     */
    public int getScreenPosX(int boardX)
    {
        return boardX - this.scrollX + this.paneX;
    }
    
    /**
     * @hint returns the exact position to draw to the screen
     * @param boardY coords relative to the board
     * @return 
     */
    public int getScreenPosY(int boardY)
    {
        return boardY - this.scrollY + this.paneY;
    }
    
    public int getScrollPosX()
    {
        return this.scrollX * 64;
    }
    
    public int getScrollPosY()
    {
        return this.scrollY * 64;
    }
    
    public int getScrollTileX()
    {
        return this.scrollX;
    }
    
    public int getScrollTileY()
    {
        return this.scrollY;
    }
    
    public int getSizeX()
    {
        return this.sizeX;
    }
    
    public int getSizeY()
    {
        return this.sizeY;
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
        for(int e = 0; e < entityEnemies.size(); e++)
        {
            if(entityEnemies.get(e).getPosX() == tileX && entityEnemies.get(e).getPosY() == tileY)
            {
                return false;
            }
        }
        return true;
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        this.getPlayer().keyPressed(key);
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        this.getPlayer().keyReleased(key);
    }
    
    public void redrawTerrain()
    {
        this.terrainImageReady = false;
    }
    
    public void render(Graphics gfx)
    {
        this.renderBackground(gfx);
        this.renderTerrain(gfx);
        
        // Development
        this.renderVectors(gfx);
        this.renderDamageMarkers(gfx);
        
        if(!this.editor)
        {
            this.renderAllies(gfx);
            if(this.entityEnemies.size() > 0) {this.renderEnemies(gfx);}
            if(this.entityContainers.size() > 0) {this.renderContainers(gfx);}
            if(this.entityVisuals.size() > 0) {this.renderVisuals(gfx);}
        }
        else {this.renderEditor(gfx);}
    }
    
    private void renderAllies(Graphics gfx)
    {
        for(int a = 0; a < this.entityAllies.size(); a++)
        {
            this.entityAllies.get(a).render(gfx);
        }
    }
    
    private void renderBackground(Graphics gfx)
    {
        gfx.setColor(this.background);
        gfx.fillRect(this.paneX, this.paneY, this.paneW, this.paneH);
    }
    
    private void renderContainers(Graphics gfx)
    {
        for(int c = 0; c < this.entityContainers.size(); c++)
        {
            this.entityContainers.get(c).render(gfx);
        }
    }
    
    private void renderDamageMarkers(Graphics gfx)
    {
        for(int dm = 0; dm < this.damageMarkers.size(); dm++)
        {
            this.damageMarkers.get(dm).render(gfx);
        }
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
                    // If we have tiles for this section of the board
                    if(x + this.getScrollTileX() < this.terrain.length && y + this.getScrollTileY() < this.terrain[x].length)
                    {
                        if(!this.terrain[x + this.getScrollTileX()][y + this.getScrollTileY()].getBlank())
                        {
                            terrainGfx.drawImage(this.terrain[x + this.getScrollTileX()][y + this.getScrollTileY()].getImage(), x * 32, y * 32, null);
                        }
                    }
                }
            }
            this.terrainImageReady = true;
        }
        gfx.drawImage(this.terrainImage, this.paneX, this.paneY, null);
    }
    
    private void renderVectors(Graphics gfx)
    {
        for(int v = 0; v < this.vectors.size(); v++)
        {
            this.vectors.get(v).render(gfx);
        }
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
    
    public void setPlayer(int id)
    {
        this.entityAllies.get(this.entityPlayer).setAI(true);
        this.entityAllies.get(id).setAI(false);
        this.entityPlayer = id;
        this.setScrollPlayer();
    }
    
    public void setScroll(int posX, int posY)
    {
        this.scrollX = posX;
        this.scrollY = posY;
        this.terrainImageReady = false;
    }
    
    public void setScrollPlayer()
    {
        this.scrollX = this.getPlayer().getPosX() - (this.paneW / 2);
        this.scrollY = this.getPlayer().getPosY() - (this.paneH / 2);
        this.terrainImageReady = false;
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
        if(this.editor) {this.tickEditor();}
        else
        {
            this.tickAllies();
            this.tickEnemies();
            this.tickVisuals();
            this.tickDamageMarkers();
        }
    }
    
    private void tickAllies()
    {
        for(int a = 0; a < this.entityAllies.size(); a++)
        {
            if(this.entityAllies.get(a).getStatusKO()) {this.entityAllies.remove(a);}
            else {this.entityAllies.get(a).tick();}
        }
    }
    
    private void tickEditor()
    {
        // NOTE: may not need this
    }
    
    public void tickDamageMarkers()
    {
        for(int dm = 0; dm < this.damageMarkers.size(); dm++)
        {
            if(this.damageMarkers.get(dm).getDone()) {this.damageMarkers.remove(dm);}
            else {this.damageMarkers.get(dm).tick();}
        }
    }
    
    public void tickEnemies()
    {
        for(int e = 0; e < this.entityEnemies.size(); e++)
        {
            if(this.entityEnemies.get(e).getStatusKO()) {this.entityEnemies.remove(e);}
            else {this.entityEnemies.get(e).tick();}
        }
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
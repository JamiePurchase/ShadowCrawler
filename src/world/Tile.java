package world;

import gfx.Tileset;
import java.awt.image.BufferedImage;

public class Tile
{
    private boolean blank;
    private BufferedImage image;
    private boolean solid;
    
    public Tile()
    {
        this.blank = true;
        this.image = null;
        this.solid = false;
    }
    
    public Tile(BufferedImage image, boolean solid)
    {
        this.blank = false;
        this.image = image;
        this.solid = solid;
    }
    
    public Tile(Tileset tileset, int tileX, int tileY, boolean solid)
    {
        this.blank = false;
        this.image = tileset.getTileAt(tileX, tileY);
        this.solid = solid;
    }
    
    public Tile clone()
    {
        if(this.blank) {return new Tile();}
        else {return new Tile(this.image, this.solid);}
    }
    
    public boolean getBlank()
    {
        return this.blank;
    }
    
    public BufferedImage getImage()
    {
        return this.image;
    }
    
    public boolean getSolid()
    {
        return this.solid;
    }
    
    public void setImage(BufferedImage newImage)
    {
        this.blank = false;
        this.image = newImage;
    }
    
    public void setSolid(boolean value)
    {
        this.solid = value;
    }
    
}
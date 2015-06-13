package world;

import java.awt.Rectangle;

public class Vector
{
    private String ref;
    private Rectangle area;
    private boolean solid;
    
    public Vector(String ref, int posX, int posY, int wide, int high, boolean solid)
    {
        this.ref = ref;
        this.area = new Rectangle(posX, posY, wide, high);
        this.solid = solid;
    }
    
    public String getRef()
    {
        return this.ref;
    }
    
    public boolean getSolid()
    {
        return this.solid;
    }
    
    public Rectangle getVector()
    {
        return this.area;
    }
    
}
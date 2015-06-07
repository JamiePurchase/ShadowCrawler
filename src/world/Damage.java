package world;

import gfx.Tileset;

public class Damage
{
    private int amount;
    private String type;
    private int posX, posY, sizeX, sizeY;
    
    public Damage(int amount, String type, int posX, int posY, int sizeX, int sizeY)
    {
        this.amount = amount;
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    
    public int getDamageBase()
    {
        return this.amount;
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public int getSizeX()
    {
        return this.sizeX;
    }
    
    public int getSizeY()
    {
        return this.sizeY;
    }
    
}
package world;

import app.Console;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Vector
{
    private Board board;
    private String ref;
    private Rectangle area;
    private boolean solid;
    
    public Vector(Board board, String ref, int posX, int posY, int wide, int high, boolean solid)
    {
        this.board = board;
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
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Color.cyan);
        gfx.drawRect(this.board.getScreenPosX(this.area.x), this.board.getScreenPosY(this.area.y), this.area.width, this.area.height);
    }
    
}
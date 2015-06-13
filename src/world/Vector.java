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
    private Color renderColor;
    
    public Vector(Board board, String ref, Rectangle rect, boolean solid)
    {
        this.board = board;
        this.ref = ref;
        this.area = rect;
        this.solid = solid;
        this.renderColor = Color.cyan;
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
        gfx.setColor(this.renderColor);
        gfx.drawRect(this.board.getScreenPosX(this.area.x), this.board.getScreenPosY(this.area.y), this.area.width, this.area.height);
    }
    
    public void setRenderColor(Color rgb)
    {
        this.renderColor = rgb;
    }
    
}
package world;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class DamageMarker
{
    private Board board;
    private Rectangle rect;
    private int tick;
    private boolean done;
    
    public DamageMarker(Board board, Rectangle rect)
    {
        this.board = board;
        this.rect = rect;
        this.tick = 12;
        this.done = false;
    }
    
    public boolean getDone()
    {
        return this.done;
    }
    
    public void render(Graphics gfx)
    {
        gfx.setColor(Color.cyan);
        gfx.drawRect(this.board.getScreenPosX(this.rect.x), this.board.getScreenPosX(this.rect.y), this.rect.width, this.rect.height);
    }
    
    public void tick()
    {
        if(!this.done)
        {
            this.tick -= 1;
            if(this.tick < 1) {this.done = true;}
        }
    }
    
}
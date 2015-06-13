package ui;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.Campaign;
import world.Board;

public class HudActionHint
{
    private Board board;
    private Campaign campaign;
    private String hint;
    private BufferedImage image;
    private boolean active;
    
    public HudActionHint(Board board, Campaign campaign)
    {
        this.board = board;
        this.campaign = campaign;
        this.setHint();
    }
    
    public boolean getActive()
    {
        return this.active;
    }
    
    public void render(Graphics gfx)
    {
        Drawing.write(gfx, this.hint, 1316, 718, "RIGHT");
        if(this.image != null) {gfx.drawImage(this.image, 1290, 700, null);}
    }
    
    public void setHint()
    {
        this.hint = "";
        this.image = null;
        this.active = false;
    }
    
    public void setHint(String hint)
    {
        this.hint = hint;
        this.active = true;
    }
    
    public void setHint(String hint, BufferedImage image)
    {
        this.hint = hint;
        this.image = image;
        this.active = true;
    }
    
}
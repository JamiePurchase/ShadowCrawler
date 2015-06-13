package ui;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.Campaign;
import world.Board;

public class HudMinimap
{
    private Board board;
    private Campaign campaign;
    
    // Position
    private int posX, posY, sizeX, sizeY;
    
    public HudMinimap(Board board, Campaign campaign)
    {
        this.board = board;
        this.campaign = campaign;
        this.posX = 1100;
        this.posY = 100;
        this.sizeX = 200;
        this.sizeY = 200;
    }
    
    private BufferedImage getPlayerImage()
    {
        return Drawing.getImage("interface/minimap/iconPlayer.png");
    }
    
    private double getRotation(String face)
    {
        if(face == "E") {return 90;}
        if(face == "S") {return 180;}
        if(face == "W") {return 270;}
        return 0;
    }
    
    public void render(Graphics gfx)
    {
        renderTerrain(gfx);
        renderPlayer(gfx);
        renderEnemies(gfx);
        //renderTreasure(gfx);
        renderDetails(gfx);
        renderBorder(gfx);
    }
    
    private void renderBorder(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("interface/minimap/mapBorder.png"), this.posX, this.posY, null);
    }
    
    private void renderDetails(Graphics gfx)
    {
        gfx.setColor(Color.BLACK);
        Drawing.write(gfx, "Mantas Tomb", this.posX + 20, (this.posY + this.sizeY) - 20, "LEFT");
        Drawing.write(gfx, "00 x 00", (this.posX + this.sizeX) - 20, (this.posY + this.sizeY) - 20, "RIGHT");
    }
    
    private void renderEnemies(Graphics gfx)
    {
        //
    }
    
    private void renderPlayer(Graphics gfx)
    {
        Drawing.drawImageRotate(gfx, this.getPlayerImage(), this.posX + (this.sizeX / 2) - 8, this.posY + (this.sizeY / 2) - 8, this.getRotation(this.board.getPlayer().getFace()));
    }
    
    private void renderTerrain(Graphics gfx)
    {
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/minimap/bkgGrass.png"), this.posX + 4, this.posY + 4, 0.6f);
    }
    
    public void tick()
    {
        
    }
    
}
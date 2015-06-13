package ui;

import gfx.Drawing;
import java.awt.Color;
import java.awt.Graphics;
import player.Campaign;
import world.Board;
import world.EntityPlayer;

public class HudCharacter
{
    private Board board;
    private Campaign campaign;
    
    public HudCharacter(Board board, Campaign campaign)
    {
        this.board = board;
        this.campaign = campaign;
    }
    
    public void render(Graphics gfx)
    {
        renderPortrait(gfx);
        renderBarHealth(gfx);
        renderBarMystic(gfx);
        renderBarEnergy(gfx);
        renderBorder(gfx);
    }
    
    private void renderBorder(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("interface/hudPlayer.png"), 50, 600, null);
    }
    
    private void renderPortrait(Graphics gfx)
    {
        gfx.drawImage(Drawing.getImage("portrait/" + "Jakken_Menu" + ".png"), 55, 605, null);
    }
    
    private void renderBarEnergy(Graphics gfx)
    {
        gfx.setColor(Color.GREEN);
        gfx.fillRect(245, 645, this.board.getPlayer().getStatEnergyPercent() * 4, 15);
    }
    
    private void renderBarHealth(Graphics gfx)
    {
        gfx.setColor(Color.RED);
        gfx.fillRect(245, 605, this.board.getPlayer().getStatHealthPercent() * 4, 15);
    }
    
    private void renderBarMystic(Graphics gfx)
    {
        gfx.setColor(Color.BLUE);
        gfx.fillRect(245, 625, this.board.getPlayer().getStatMysticPercent() * 4, 15);
    }
    
    public void tick()
    {
        
    }
    
}
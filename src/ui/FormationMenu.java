package ui;

import gfx.Drawing;
import gfx.Theme;
import input.InputKeyboardKey;
import java.awt.Graphics;
import java.util.ArrayList;
import player.Campaign;
import player.Formation;
import world.Board;

public class FormationMenu
{
    private Board board;
    private Campaign campaign;
    private ArrayList<Formation> formation;
    private int cursorNow, cursorMax;
    
    public FormationMenu(Board board, Campaign campaign)
    {
        this.board = board;
        this.campaign = campaign;
        this.formation = new ArrayList<Formation>();
        this.cursorNow = 0;
        this.cursorMax = this.formation.size();
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        //
    }
    
    public void render(Graphics gfx)
    {
        // Background (TEMP)
        gfx.setColor(Theme.getColour("PARCHMENT"));
        gfx.fillRect(75, 225, 245, 342);
        
        // Border
        gfx.drawImage(Drawing.getImage("interface/formationBorder.png"), 50, 200, null);
    }
    
}
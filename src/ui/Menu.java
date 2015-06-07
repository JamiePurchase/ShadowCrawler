package ui;

import app.Application;
import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Menu
{
    // Options
    private int cursor;
    private String cursorString;
    private ArrayList<MenuOption> options;
    
    // Background
    private boolean bkgRender;
    private BufferedImage bkgImage;
    private int bkgPosX, bkgPosY;
    private boolean bkgOpaque;
    
    public Menu()
    {
        // Options
        this.cursor = 0;
        this.cursorString = "->";
        this.options = new ArrayList<MenuOption>();
        
        // Background
        this.bkgRender = false;
        this.bkgImage = null;
        this.bkgOpaque = false;
        this.bkgPosX = 0;
        this.bkgPosY = 0;
    }
    
    public void addOption(String value, int posX, int posY)
    {
        this.options.add(new MenuOption(value, posX, posY));
    }
    
    public int getCursor()
    {
        return this.cursor;
    }
    
    private int getOptionCount()
    {
        return this.options.size();
    }
    
    public void render(Graphics gfx)
    {
        if(this.bkgRender) {this.renderBackground(gfx);}
        gfx.setColor(Application.getThemeColour("FORE"));
        gfx.setFont(Application.getThemeFont("HEADER"));
        this.renderOptions(gfx);
        this.renderCursor(gfx);
    }
    
    private void renderBackground(Graphics gfx)
    {
        if(this.bkgOpaque) {Drawing.drawImageOpaque(gfx, this.bkgImage, this.bkgPosX, this.bkgPosY, 0.5f);}
        else {gfx.drawImage(this.bkgImage, this.bkgPosX, this.bkgPosY, null);}
    }
    
    private void renderCursor(Graphics gfx)
    {
        gfx.drawString(this.cursorString, this.options.get(this.cursor).getPosX() - 35, this.options.get(this.cursor).getPosY());
    }
    
    private void renderOptions(Graphics gfx)
    {
        for(int opt = 0; opt < this.getOptionCount(); opt++)
        {
            gfx.drawString(this.options.get(opt).getValue(), this.options.get(opt).getPosX(), this.options.get(opt).getPosY());
        }
    }
    
    public void setBackground(BufferedImage image, int posX, int posY, boolean opaque)
    {
        this.bkgRender = true;
        this.bkgImage = image;
        this.bkgPosX = posX;
        this.bkgPosY = posY;
        this.bkgOpaque = opaque;
    }
    
    public void tick()
    {
        if(Application.getInputKeyboard().getKeyPressed() != "NONE")
        {
            if(Application.getInputKeyboard().getKeyPressed() == "UP")
            {
                if(this.cursor > 0) {this.cursor -= 1;}
                Application.getInputKeyboard().keyPressedDone();
            }
            if(Application.getInputKeyboard().getKeyPressed() == "DOWN")
            {
                if(this.cursor < this.getOptionCount()) {this.cursor += 1;}
                Application.getInputKeyboard().keyPressedDone();
            }
        }
    }
    
}
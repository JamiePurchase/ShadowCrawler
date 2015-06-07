package ui;

import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;

public class HintBar
{
    private String textMain, textRight;
    
    public HintBar(String text)
    {
        this.textMain = text;
        this.textRight = "";
    }
    
    public HintBar(String text1, String text2)
    {
        this.textMain = text1;
        this.textRight = text2;
    }
    
    public void render(Graphics gfx)
    {
        // Background
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/titleFooter1bkg2.png"), 0, 718, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/titleFooter1border.png"), 0, 718, null);
        
        // Text
        gfx.setFont(Theme.getFont("STANDARD"));
        gfx.setColor(Theme.getColour("TEXT"));
        if(!this.textMain.isEmpty()) {gfx.drawString(this.textMain, 36, 752);}
        if(!this.textRight.isEmpty()) {Drawing.write(gfx, this.textRight, 1330, 752, "RIGHT");}
    }
    
    public void tick()
    {
        // NOTE: just in case we need animated icons or something
    }
    
}
package ui;

import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;

public class MessageBar
{
    // Text
    private String title, text1, text2;
    
    // Tick
    private int tickNow, tickMax;
    
    public MessageBar(String title, String text1, String text2)
    {
        this.title = title;
        this.text1 = text1;
        this.text2 = text2;
    }
    
    public void render(Graphics gfx)
    {
        this.renderPane(gfx);
        this.renderText(gfx);
    }
    
    private void renderPane(Graphics gfx)
    {
        Drawing.drawImageOpaque(gfx, Drawing.getImage("interface/chatPane1bkg.png"), 0, 568, 0.8f);
        gfx.drawImage(Drawing.getImage("interface/chatPane1border.png"), 0, 568, null);
    }
    
    private void renderText(Graphics gfx)
    {
        // Message Title
        gfx.setFont(Theme.getFont("CHATSPEAKER"));
        gfx.setColor(Theme.getColour("TEXTSHADOW"));
        gfx.drawString(this.title.toUpperCase(), 147, 626);
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString(this.title.toUpperCase(), 145, 625);
        
        // Message Text
        gfx.setFont(Theme.getFont("CHATSPEECH"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString(this.text1, 130, 665);
        gfx.drawString(this.text2, 130, 705);
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow > tickMax) {this.tickNow = 0;}
    }
    
}
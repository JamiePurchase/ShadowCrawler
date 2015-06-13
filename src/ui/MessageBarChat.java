package ui;

import gfx.Drawing;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MessageBarChat extends MessageBar
{
    private BufferedImage portrait;
    
    public MessageBarChat(String speaker, String speech1, String speech2, String portrait)
    {
        super(speaker, speech1, speech2);
        this.portrait = Drawing.getImage("portrait/" + portrait + "_Chat.png");
    }
    
    public void render(Graphics gfx)
    {
        renderPortrait(gfx);
        super.render(gfx);
    }
    
    private void renderPortrait(Graphics gfx)
    {
        gfx.drawImage(this.portrait, 140, 441, null);
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
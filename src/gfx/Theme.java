package gfx;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

public class Theme
{
    private static Map<String, Color> pallete;
    private static Map<String, Font> typeface;

    public Theme()
    {
        // Colour Pallette
        this.pallete = new HashMap<String, Color>();
        pallete.put("DAMAGEVISUAL", Drawing.getColorRGB(150, 50, 50));
        pallete.put("STANDARD", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TEXT", Drawing.getColorRGB(220, 220, 220));
        pallete.put("TEXTSHADOW", Drawing.getColorRGB(50, 50, 50));
        
        // Typeface Styles
        this.typeface = new HashMap<String, Font>();
        typeface.put("CHATSPEAKER", new Font("Andalus", Font.BOLD, 26));
        typeface.put("CHATSPEECH", new Font("Andalus", Font.PLAIN, 26));
        typeface.put("DAMAGEVISUAL", new Font("Trebuchet MS", Font.PLAIN, 64));
        typeface.put("MENUOPTION", new Font("Trebuchet MS", Font.PLAIN, 32));
        typeface.put("STANDARD", new Font("Andalus", Font.PLAIN, 26));
    }
    
    public void addColour(String id, Color color)
    {
        pallete.put(id, color);
    }
    
    public void addFont(String id, Font font)
    {
        typeface.put(id, font);
    }
    
    public static Color getColour(String id)
    {
        return pallete.get(id);
    }
    
    public static Font getFont(String id)
    {
        return typeface.get(id);
    }
    
}
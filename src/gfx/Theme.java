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
        pallete.put("MARGIN", Drawing.getColorRGB(35, 70, 35));
        pallete.put("PARCHMENT", Drawing.getColorRGB(225, 220, 130));
        pallete.put("PROGRESSBKG", Drawing.getColorRGB(0, 0, 0));
        pallete.put("PROGRESSFORE", Drawing.getColorRGB(100, 100, 100));
        pallete.put("STANDARD", Drawing.getColorRGB(0, 0, 0));
        pallete.put("TEXT", Drawing.getColorRGB(220, 220, 220));
        pallete.put("TEXTSHADOW", Drawing.getColorRGB(50, 50, 50));
        
        
        // Typeface Styles
        this.typeface = new HashMap<String, Font>();
        typeface.put("CHATSPEAKER", new Font("Andalus", Font.BOLD, 26));
        typeface.put("CHATSPEECH", new Font("Andalus", Font.PLAIN, 26));
        typeface.put("DAMAGEVISUAL", new Font("Trebuchet MS", Font.PLAIN, 64));
        typeface.put("MENUHEADER", new Font("Andalus", Font.PLAIN, 44));
        typeface.put("MENUNAME", new Font("Andalus", Font.BOLD, 26));
        typeface.put("MENUOPTION", new Font("Trebuchet MS", Font.PLAIN, 32));
        typeface.put("MENUSTATS", new Font("Trebuchet MS", Font.PLAIN, 22));
        typeface.put("PROGRESS", new Font("Andalus", Font.BOLD, 26));
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
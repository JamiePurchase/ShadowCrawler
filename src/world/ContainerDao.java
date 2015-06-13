package world;

import gfx.Drawing;
import gfx.Tileset;
import item.ItemIngredient;
import item.ItemWeapon;
import java.util.ArrayList;
import maths.Range;

public class ContainerDao
{
    
    public ContainerDao()
    {
        //
    }

    public static EntityContainer createContainer(String ref, Board board, int posX, int posY, String template)
    {
        // Default data
        String name = "Jar";
        int lock = 0;
        String anim = "Jar1";
        ArrayList<Loot> loot = new ArrayList<Loot>();
        int meshW = 32;
        int meshH = 32;
        int meshX = 0;
        int meshY = 0;
        
        // Templates
        if(template.equals("JAR_TOMB"))
        {
            name = "Aged Cask";
            lock = 0;
            anim = "Jar1";
            loot.addAll(getLoot(template));
            loot.addAll(getLoot("GENERIC_TOMB"));
        }
        
        // Create a container and return it
        return new EntityContainer(ref, board, name, lock, posX, posY, getTileset(anim), meshW, meshH, meshX, meshY, loot);
    }
    
    private static ArrayList<Loot> getLoot(String template)
    {
        // Temporary array list
        ArrayList<Loot> loot = new ArrayList<Loot>();
        
        // Tempaltes
        if(template.equals("JAR_TOMB"))
        {
            loot.add(new Loot(new ItemWeapon(), 50, new Range(0, 10)));
        }
        
        // Generic extras
        if(template.equals("GENERIC_TOMB"))
        {
            loot.add(new Loot(new ItemIngredient(), 50));
        }
            
        // Create the loot and return it
        return loot;
    }
    
    private static Tileset getTileset(String file)
    {
        return new Tileset("spr|con|" + file, Drawing.getImage("spritesheet/container/" + file + ".png"), 32, 32, 1, 4);
    }
    
}
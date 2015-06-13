package world;

import java.io.Serializable;

import item.Item;
import maths.Range;

public class Loot
{
    private Item item;
    private int chance;
    private Range level;
    
    public Loot(Item item, int chance)
    {
        this.item = item;
        this.chance = chance;
        this.level = new Range(0, 99);
    }
    
    public Loot(Item item, int chance, Range level)
    {
        this.item = item;
        this.chance = chance;
        this.level = level;
    }
    
}
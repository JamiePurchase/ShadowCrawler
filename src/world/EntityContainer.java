package world;

import gfx.Tileset;
import java.util.ArrayList;

public class EntityContainer extends Entity
{
    private String name;
    private int lock;
    private ArrayList<Loot> loot;
    
    public EntityContainer(String ref, Board board, String name, int lock, int posX, int posY, Tileset tileset, int meshW, int meshH, int meshX, int meshY, ArrayList<Loot> loot)
    {
        super(ref, board, posX, posY, tileset);
        this.name = name;
        this.lock = lock;
        this.setMesh(meshW, meshH, meshX, meshY);
        this.loot = loot;
    }
    
    public void addLoot(Loot add)
    {
        this.loot.add(add);
    }
    
}
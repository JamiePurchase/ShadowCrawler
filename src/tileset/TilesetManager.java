package tileset;

public class TilesetManager
{
    //private Map<Tileset> tilesets;
    private boolean loadDone;
    
    public TilesetManager()
    {
        this.loadDone = false;
    }
    
    public boolean getLoadDone()
    {
        return this.loadDone;
    }
    
    public void load()
    {
        this.loadDone = true;
    }
    
}
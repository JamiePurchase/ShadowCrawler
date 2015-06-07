package item;

import java.awt.image.BufferedImage;

public abstract class Item
{
    // Info
    private String name;
    private String description;
    private boolean quest;
    private int weight;
    
    // Anim
    private BufferedImage icon;
    
    // Trade
    private int tradeGold;
    private boolean tradeForbid;
    
    public String getDescription()
    {
        return this.description;
    }
    
    public BufferedImage getIcon()
    {
        return this.icon;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public int getTradeGold()
    {
        return this.tradeGold;
    }
    
    public boolean getTradeForbid()
    {
        return this.tradeForbid;
    }
    
    public int getWeight()
    {
        return this.weight;
    }
    
}
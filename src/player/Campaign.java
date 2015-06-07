package player;

import item.Inventory;
import item.Item;
import java.util.ArrayList;

public class Campaign
{
    // Time
    private long timeOverall;
    private long timeSession;
    
    // Inventory
    private Inventory inventory;
    
    // Quests
    private QuestLog questLog;
    
    public Campaign()
    {
        // NOTE: this is a timestamp of when the user started playing in this session
        // NOTE: if the player views total time (in the menu) we will use this as a reference
        // NOTE: when the campaign is saved, we need to add the total session time to timeOverall
        timeSession = System.nanoTime();
    }
    
    public void rewardEnemy(int gold, ArrayList<Item> item, int xp)
    {
        // GOLD: add this to the party stockpile
        // ITEM: shouldn't this be dropped on the ground ??
        // XP: get the active player and apply growth
    }
    
    public void save()
    {
        
    }
    
}
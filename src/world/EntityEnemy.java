package world;

import app.Application;
import gfx.Tileset;
import item.Item;
import java.util.ArrayList;
import maths.Mathematics;

public class EntityEnemy extends Entity
{
    // Reward
    private int rewardGoldMin, rewardGoldMax;
    private ArrayList<Item> rewardItem;
    private int rewardXP;
    
    public EntityEnemy(String ref, Board board, int tileX, int tileY, Tileset tileset)
    {
        super(ref, board, tileX, tileY, tileset);
        
        // Rewards
        this.rewardGoldMin = 25;
        this.rewardGoldMax = 75;
        this.rewardItem = new ArrayList<Item>();
        //this.rewardItem.add(new ItemWeapon());
        this.rewardXP = 24;
    }
    
    private void death()
    {
        // Rewards (consider when to do this - create a bag of loot on the floor)
        Application.getCampaign().rewardEnemy(this.getRewardGold(), this.getRewardItem(), this.getRewardXP());
    }
    
    public int getRewardGold()
    {
        return Mathematics.randomInt(this.rewardGoldMin, this.rewardGoldMax);
    }
    
    public ArrayList<Item> getRewardItem()
    {
        // NOTE: we need to randomly pick some of the potential rewards
        return this.rewardItem;
    }
    
    public int getRewardXP()
    {
        return this.rewardXP;
    }
    
}
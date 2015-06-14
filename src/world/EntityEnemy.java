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
    
    // AI Strategy
    // NOTE: we will need too keep a track of what the plan is and which character is being targetted
    
    // Awareness
    private boolean awareBattle, awareLocation;
    // NOTE: the idea is that awareBattle means the enemy is not resting, they know the players are nearby
    // awareLocation means that the enemy can see and can target the party (unique value for each character?)
    
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
    
    public void tick()
    {
        // Actions and Effects
        super.tick();
        
        // AI Strategy
    }
    
}
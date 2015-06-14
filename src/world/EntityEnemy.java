package world;

import app.Application;
import app.Console;
import gfx.Drawing;
import gfx.Tileset;
import item.Item;
import java.awt.Graphics;
import java.util.ArrayList;
import maths.Mathematics;

public class EntityEnemy extends Entity
{
    // Reward
    private int rewardGoldMin, rewardGoldMax;
    private ArrayList<Item> rewardItem;
    private int rewardXP;
    
    // AI Strategy
    private Entity aiTarget;
    private boolean aiReady;
    // NOTE: we will need too keep a track of what the plan is and which character is being targetted
    
    // Awareness
    private boolean awareBattle, awareLocation;
    // NOTE: the idea is that awareBattle means the enemy is not resting, they know the players are nearby
    // awareLocation means that the enemy can see and can target the party (unique value for each character?)
    
    // Temp
    private Tileset animAttack;
    
    public EntityEnemy(String ref, Board board, int tileX, int tileY, Tileset tileset)
    {
        super(ref, board, tileX, tileY, tileset);
        
        // Rewards
        this.rewardGoldMin = 25;
        this.rewardGoldMax = 75;
        this.rewardItem = new ArrayList<Item>();
        //this.rewardItem.add(new ItemWeapon());
        this.rewardXP = 24;
        
        // AI Strategy
        this.aiTarget = null;
        this.aiReady = false;
        
        // TEMP
        animAttack = new Tileset("spr|ene|Skeleton_Mace", Drawing.getImage("spritesheet/creature/Skeleton/Skeleton_Mace.png"), 192, 192, 6, 4);
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
    
    public void render(Graphics gfx)
    {
        if(getMeshRender()) {super.renderMesh(gfx);}
        gfx.drawImage(super.getRenderImage(this.animAttack), super.getRenderPosX(), super.getRenderPosY(), null);
    }
    
    public void tick()
    {
        // Actions and Effects
        super.tick();
        
        // AI Strategy
        if(this.aiReady && !this.getBusy())
        {
            if(this.getMeshAttack().intersects(this.aiTarget.getMesh()))
            {
                // Attack attempt
                this.attack();
            }
            else {this.moveTowards(this.aiTarget);}
        }
        else
        {
            this.aiTarget = super.getBoard().getEntityAlly(0);
            this.aiReady = true;
            // NOTE: the current plan is chase the enemy mindlessly and attack until depleted of energy
            // clearly, this needs some more work: enemies should move back at times and the ai strategy
            // should not be too repetitive - it would be better to see variation in their actions
        }
    }
    
}
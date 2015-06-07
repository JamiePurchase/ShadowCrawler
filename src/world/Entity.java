package world;

import app.Application;
import gfx.Drawing;
import gfx.Tileset;
import item.Item;
import item.ItemWeapon;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import maths.Mathematics;

public class Entity
{
    private String ref;
    private Board board;
    private int tileX, tileY;
    private String face;
    private boolean busy;
    private Tileset sprite;
    
    // Stats
    private int statHealthNow, statHealthMax;
    
    // Status
    private boolean statusKO;
    
    // Action
    private String action;
    private boolean actionHold;
    private int actionTickNow, actionTickMax, actionFrameNow, actionFrameMax;
    private boolean actionRepeat;
    private String actionResume;
    private boolean actionDamage;
    private int actionDamageFrame;
    private Damage actionDamageObject;
    
    // Reward
    private int rewardGoldMin, rewardGoldMax;
    private ArrayList<Item> rewardItem;
    private int rewardXP;
    
    public Entity(String ref, Board board, int tileX, int tileY, Tileset tileset)
    {
        this.ref = ref;
        this.board = board;
        this.tileX = tileX;
        this.tileY = tileY;
        this.face = "S";
        this.busy = false;
        this.sprite = tileset;
        
        // Stats
        this.statHealthNow = 100;
        this.statHealthMax = 100;
        
        // Status
        this.statusKO = false;
        
        // Action
        this.setAction("IDLE");
        
        // Reward (TEMP)
        this.rewardGoldMin = 25;
        this.rewardGoldMax = 75;
        this.rewardItem = new ArrayList<Item>();
        //this.rewardItem.add(new ItemWeapon());
        this.rewardXP = 24;
    }
    
    public String getAction()
    {
        return this.action;
    }
    
    public boolean getBusy()
    {
        return this.busy;
    }
    
    private BufferedImage getRenderImage()
    {
        // NOTE: can we assume that all entities will have tilesets laid-out in the exact fashion?
        // NOTE: if not, we need to inject specific data for every entity we create
        if(this.action.equals("WALK"))
        {
            int tileX = this.actionFrameNow;
            int tileY = 11;
            if(this.face.equals("N")) {tileY = 9;}
            if(this.face.equals("W")) {tileY = 10;}
            if(this.face.equals("E")) {tileY = 12;}
            return this.sprite.getTileAt(tileX, tileY);
        }
        if(this.action.equals("ATTACK"))
        {
            // NOTE: we must be aware that the animated sprites are not all the same size
            
            // Temp
            int posX = this.getRenderPosX() - 64;
            int posY = this.getRenderPosY() - 64;
            int imgX = this.actionFrameNow;
            int imgY = 3;
            if(this.face.equals("N")) {imgY = 1;}
            if(this.face.equals("W")) {imgY = 2;}
            if(this.face.equals("E")) {imgY = 4;}
            return new Tileset("spr|Jakken_Sword5", Drawing.getImage("spritesheet/Jakken_Sword5.png"), 192, 192, 6, 4).getTileAt(imgX, imgY);
        }
        if(this.action.equals("GUARD"))
        {
            
        }
        if(this.action.equals("KO"))
        {
            return this.sprite.getTileAt(this.actionFrameNow, 21);
        }
        
        // IDLE
        int tileX = 1;
        int tileY = 11;
        if(this.face.equals("N")) {tileY = 9;}
        if(this.face.equals("W")) {tileY = 10;}
        if(this.face.equals("E")) {tileY = 12;}
        return this.sprite.getTileAt(tileX, tileY);
    }
    
    private int getRenderPosX()
    {
        if(this.action.equals("WALK"))
        {
            if(this.face == "E") {return (this.tileX - this.board.getScrollX()) * 32 + (this.actionFrameNow * 4);}
            if(this.face == "W") {return (this.tileX - this.board.getScrollX()) * 32 - (this.actionFrameNow * 4);}
        }
        if(this.action.equals("ATTACK"))
        {
            return ((this.tileX - this.board.getScrollX()) * 32) - 64;
        }
        
        // Idle
        return (this.tileX - this.board.getScrollX()) * 32;
    }
    
    private int getRenderPosY()
    {
        if(this.action.equals("WALK"))
        {
            if(this.face == "N") {return (this.tileY - this.board.getScrollY()) * 32 - (this.actionFrameNow * 4);}
            if(this.face == "S") {return (this.tileY - this.board.getScrollY()) * 32 + (this.actionFrameNow * 4);}
        }
        if(this.action.equals("ATTACK"))
        {
            return ((this.tileY - this.board.getScrollY()) * 32) - 64;
        }
        
        // Idle
        return (this.tileY - this.board.getScrollY()) * 32;   
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
    
    public boolean getStatusKO()
    {
        return this.statusKO;
    }
    
    public int getTileX()
    {
        return this.tileX;
    }
    
    public int getTileY()
    {
        return this.tileY;
    }
    
    public void guard()
    {
        this.setAction("GUARD");
    }
    
    public void guardDone()
    {
        this.setAction("IDLE");
    }
    
    public void inflictDamage(Damage damage)
    {
        this.reduceHealth(damage.getDamageBase());
        this.board.damageVisual(damage.getDamageBase(), this.getTileX(), this.getTileY());
        // NOTE: basic damage should be changed (potential critical, might even miss)
        // NOTE: add random int between 0 and 9 for variation
        // NOTE: consider defence and elemental weakness/resistance
    }
    
    public void move(String direction)
    {
        this.setDirection(direction);
        if(this.moveValid(direction))
        {
            this.setAction("WALK");
        }
        //this.moveCollide();
    }
    
    private boolean moveValid(String direction)
    {
        int targetX = this.tileX;
        int targetY = this.tileY;
        if(direction.equals("E")) {targetX += 1;}
        if(direction.equals("N")) {targetY -= 1;}
        if(direction.equals("S")) {targetY += 1;}
        if(direction.equals("W")) {targetX -= 1;}
        return this.board.getTileValid(targetX, targetY);
    }
    
    private void reduceHealth(int amount)
    {
        this.statHealthNow -= amount;
        if(this.statHealthNow < 1)
        {
            System.out.println("Entity knocked out");
            this.statHealthNow = 0;
            this.setAction("KO");
            // NOTE: we need to setAction to dying, with instructions to fade away
            // NOTE: once the animation is finished, the board object should remove this entity
        }
    }
    
    public void render(Graphics gfx)
    {
        gfx.drawImage(this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY(), null);
    }
    
    public void setAction(String action)
    {
        // NOTE: need to carefully consider how to save all the data associated with unique actions
        
        // Temp
        if(action.equals("IDLE"))
        {
            this.action = "IDLE";
            this.actionHold = false;
            this.actionTickNow = 0;
            this.actionTickMax = 6;
            this.actionFrameNow = 1;
            this.actionFrameMax = 1;
            this.actionRepeat = true;
            this.actionResume = null;
            this.actionDamage = false;
            this.actionDamageFrame = 0;
            this.actionDamageObject = null;
            this.busy = false;
        }
        
        // Temp
        if(action.equals("WALK"))
        {
            this.action = "WALK";
            this.actionTickNow = 0;
            this.actionTickMax = 2;
            this.actionFrameNow = 1;
            //this.actionFrameImg
            // NOTE: perhaps we should have individual array lists of bufferedImages for each action
            // NOTE: it may be wise to do attach four (one for each direction)
            this.actionFrameMax = 8;
            this.actionRepeat = false;
            this.actionResume = "IDLE";
            this.busy = true;
        }
        
        // Temp
        if(action.equals("ATTACK"))
        {
            this.action = "ATTACK";
            this.actionHold = false;
            this.actionTickNow = 0;
            this.actionTickMax = 2;
            this.actionFrameNow = 1;
            this.actionFrameMax = 6;
            this.actionRepeat = false;
            this.actionResume = "IDLE";
            this.actionDamage = true;
            this.actionDamageFrame = 5;
            int targetX = this.tileX;
            int targetY = this.tileY;
            int dmgW = 1;
            int dmgH = 1;
            if(this.face.equals("E"))
            {
                targetX += 1;
                dmgW = 2;
            }
            if(this.face.equals("N"))
            {
                targetY -= 2;
                dmgH = 2;
            }
            if(this.face.equals("S"))
            {
                targetY += 1;
                dmgH = 2;
            }
            if(this.face.equals("W"))
            {
                targetX -= 2;
                dmgW = 2;
            }
            this.actionDamageObject = new Damage(25, "MELEE", targetX, targetY, dmgW, dmgH);
            this.busy = true;
        }
        
        // Temp
        if(action.equals("GUARD"))
        {
            this.action = "GUARD";
            this.actionHold = true;
            this.actionTickNow = 0;
            this.actionTickMax = 6;
            this.actionFrameNow = 1;
            this.actionFrameMax = 1;
            this.actionRepeat = true;
            this.actionResume = "";
            this.actionDamage = false;
            this.actionDamageFrame = 0;
        }
        
        // Temp
        if(action.equals("KO"))
        {
            this.action = "KO";
            this.actionHold = false;
            this.actionTickNow = 0;
            this.actionTickMax = 12;
            this.actionFrameNow = 1;
            this.actionFrameMax = 6;
            this.actionRepeat = false;
            this.actionResume = "DEATH";
            this.actionDamage = false;
            this.actionDamageFrame = 0;
        }
        
        // Temp
        if(action.equals("DEATH"))
        {
            this.statusKO = true;
            this.action = "DEATH";
            this.actionHold = false;
            this.actionTickNow = 0;
            this.actionTickMax = 0;
            this.actionFrameNow = 0;
            this.actionFrameMax = 0;
            this.actionRepeat = false;
            this.actionResume = "";
            this.actionDamage = false;
            this.actionDamageFrame = 0;
            
            // Rewards (consider when to do this - create a bag of loot on the floor)
            Application.getCampaign().rewardEnemy(this.getRewardGold(), this.getRewardItem(), this.getRewardXP());
        }
    }
    
    public void setDirection(String direction)
    {
        this.face = direction;
    }
    
    public void tick()
    {        
        this.actionTickNow += 1;
        if(this.actionTickNow > this.actionTickMax)
        {
            // Frame Start
            this.actionTickNow = 0;
            this.actionFrameNow += 1;
            
            // Damage Event
            if(this.actionDamage && this.actionFrameNow == this.actionDamageFrame)
            {
                this.board.damageInflict(this.actionDamageObject);
            }
            
            // Frames Done
            if(this.actionFrameNow > this.actionFrameMax)
            {
                // Entity has walked to another tile
                if(this.action.equals("WALK"))
                {
                    if(this.face == "N") {this.tileY -= 1;}
                    if(this.face == "S") {this.tileY += 1;}
                    if(this.face == "E") {this.tileX += 1;}
                    if(this.face == "W") {this.tileX -= 1;}
                }
                
                // Action complete (repeat or resume?)
                if(this.actionRepeat) {this.actionFrameNow = 1;}
                else {this.setAction(this.actionResume);}
            }
        }
    }
    
}
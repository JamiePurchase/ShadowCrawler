package world;

import app.Console;
import gfx.Drawing;
import gfx.Tileset;
import effect.Effect;
import effect.EffectCharge;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity
{
    private String ref;
    private Board board;
    private int posX, posY;
    private String face;
    private boolean busy;
    private Tileset sprite;
    
    // Mesh
    private int meshSizeX, meshSizeY;
    private int meshOffsetX, meshOffsetY;
    private boolean meshRender;
    
    // Stats
    private int statHealthNow, statHealthMax;
    private int statEnergyNow, statEnergyMax;
    private int statMysticNow, statMysticMax;
    
    // Status
    private boolean statusKO;
    
    // Action
    private String action;
    private boolean actionHold;
    private int actionTickNow, actionTickMax, actionFrameNow, actionFrameMax;
    private int actionOffsetX;
    private int actionOffsetY;
    private boolean actionRepeat, actionRemain;
    private String actionResume;
    private boolean actionDamage;
    private int actionDamageFrame;
    private Damage actionDamageObject;
    private Effect actionEffect;
    
    // AI
    private boolean aiActive;
    
    public Entity(String ref, Board board, int posX, int posY, Tileset tileset)
    {
        this.ref = ref;
        this.board = board;
        this.posX = posX;
        this.posY = posY;
        this.face = "S";
        this.busy = false;
        this.sprite = tileset;
        
        // Mesh
        this.meshSizeX = 64;
        this.meshSizeY = 64;
        this.meshOffsetX = 0;
        this.meshOffsetY = 0;
        this.meshRender = true;
        
        // Stats
        this.statHealthNow = 100;
        this.statHealthMax = 100;
        this.statEnergyNow = 100;
        this.statEnergyMax = 100;
        this.statMysticNow = 100;
        this.statMysticMax = 100;
        
        // Status
        this.statusKO = false;
        
        // Action
        this.setAction("IDLE");
        
        // AI
        this.aiActive = true;
    }
    
    public void attack()
    {
        this.setAction("ATTACK");
        this.reduceEnergy(12);
    }
    
    public void cast()
    {
        this.setAction("CAST");
    }
    
    public void charge(boolean action)
    {
        if(action) {this.setAction("CHARGE");}
        else {this.setAction("IDLE");}
    }
    
    private void death()
    {
        
    }
    
    public String getAction()
    {
        return this.action;
    }
    
    public boolean getBusy()
    {
        return this.busy;
    }
    
    public String getFace()
    {
        return this.face;
    }
    
    public Rectangle getMesh()
    {
        return new Rectangle(this.getPosX() - this.meshOffsetX, this.getPosY() - this.meshOffsetY, this.meshSizeX, this.meshSizeY);
    }
    
    private Rectangle getMeshTarget(String direction)
    {
        int posX = this.getPosX();
        int posY = this.getPosY();
        if(direction == "N") {posY -= 4;}
        if(direction == "E") {posX += 4;}
        if(direction == "S") {posY += 4;}
        if(direction == "W") {posX -= 4;}
        return new Rectangle(posX - this.meshOffsetX, posY - this.meshOffsetY, this.meshSizeX, this.meshSizeY);
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
            return new Tileset("spr|chr|Jakken_Sword5", Drawing.getImage("spritesheet/character/Jakken/Jakken_Sword5.png"), 192, 192, 6, 4).getTileAt(imgX, imgY);
        }
        if(this.action.equals("CAST"))
        {
            // Temp
            int tileX = this.actionFrameNow;
            int tileY = 3;
            if(this.face.equals("N")) {tileY = 1;}
            if(this.face.equals("W")) {tileY = 2;}
            if(this.face.equals("E")) {tileY = 4;}
            return this.sprite.getTileAt(tileX, tileY);
        }
        if(this.action.equals("CHARGE"))
        {
            // Temp
            int tileX = this.actionFrameNow;
            if(this.actionFrameNow > 2) {tileX = 3;}
            int tileY = 3;
            if(this.face.equals("N")) {tileY = 1;}
            if(this.face.equals("W")) {tileY = 2;}
            if(this.face.equals("E")) {tileY = 4;}
            return this.sprite.getTileAt(tileX, tileY);
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
        return this.board.getScreenPosX(this.posX) - this.actionOffsetX;
    }
    
    private int getRenderPosY()
    {
        return this.board.getScreenPosY(this.posY) - this.actionOffsetY;
    }
    
    public int getStatEnergyPercent()
    {
        return (int)((this.statEnergyNow * 100.0f) / this.statEnergyMax);
    }
    
    public int getStatHealthPercent()
    {
        return (int)((this.statHealthNow * 100.0f) / this.statHealthMax);
    }
    
    public int getStatMysticPercent()
    {
        return (int)((this.statMysticNow * 100.0f) / this.statMysticMax);
    }
    
    public boolean getStatusKO()
    {
        return this.statusKO;
    }
    
    public int getPosX()
    {
        return this.posX;
    }
    
    public int getPosY()
    {
        return this.posY;
    }
    
    public String getRef()
    {
        return this.ref;
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
        this.board.damageVisual(damage.getDamageBase(), this.getPosX(), this.getPosY());
        // NOTE: basic damage should be changed (potential critical, might even miss)
        // NOTE: add random int between 0 and 9 for variation
        // NOTE: consider defence and elemental weakness/resistance
    }
    
    public void move(String direction)
    {
        this.setDirection(direction);
        this.setAction("WALK");
        this.busy = true;
    }
    
    public void moveHalt()
    {
        this.setAction("IDLE");
        this.busy = false;
    }
    
    private void movePush(String direction)
    {
        if(this.board.getIntersect(this.getMeshTarget(direction)) == null)
        {
            if(this.face == "N") {this.posY -= 4;}
            if(this.face == "S") {this.posY += 4;}
            if(this.face == "E") {this.posX += 4;}
            if(this.face == "W") {this.posX -= 4;}
        }
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
    
    private void reduceEnergy(int amount)
    {
        this.statEnergyNow -= amount;
        if(this.statEnergyNow < 1) {this.statEnergyNow = 0;}
    }
    
    public void render(Graphics gfx)
    {
        if(meshRender) {this.renderMesh(gfx);}
        gfx.drawImage(this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY(), null);
    }
    
    private void renderMesh(Graphics gfx)
    {
        gfx.setColor(Color.CYAN);
        gfx.drawRect(this.board.getScreenPosX(this.getMesh().x), this.board.getScreenPosY(this.getMesh().y), this.getMesh().width, this.getMesh().height);
    }
    
    public void setAction(String action)
    {
        // NOTE: need to carefully consider how to save all the data associated with unique actions
        this.actionEffect = null;
        
        // Temp
        if(action.equals("IDLE"))
        {
            this.action = "IDLE";
            this.actionHold = false;
            this.actionTickNow = 0;
            this.actionTickMax = 6;
            this.actionFrameNow = 1;
            this.actionFrameMax = 1;
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = true;
            this.actionResume = null;
            this.actionRemain = false;
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
            this.actionTickMax = 6;
            this.actionFrameNow = 1;
            //this.actionFrameImg
            // NOTE: perhaps we should have individual array lists of bufferedImages for each action
            // NOTE: it may be wise to do attach four (one for each direction)
            this.actionFrameMax = 8;
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = true;
            this.actionResume = null;
            this.actionRemain = false;
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
            this.actionOffsetX = 64;
            this.actionOffsetY = 64;
            this.actionRepeat = false;
            this.actionResume = "IDLE";
            this.actionRemain = false;
            this.actionDamage = true;
            this.actionDamageFrame = 5;
            int targetX = this.getPosX();
            int targetY = this.getPosY();
            int targetW = 64;
            int targetH = 64;
            if(this.face.equals("E")) {targetX += 64;}
            if(this.face.equals("N")) {targetY -= 64;}
            if(this.face.equals("S")) {targetY += 64;}
            if(this.face.equals("W")) {targetX -= 64;}
            this.actionDamageObject = new Damage(25, "MELEE", targetX, targetY, targetW, targetH);
            this.busy = true;
        }
        
        // Temp
        if(action.equals("CAST"))
        {
            this.action = "CAST";
            this.actionTickNow = 0;
            this.actionTickMax = 6;
            this.actionFrameNow = 1;
            //this.actionFrameImg
            // NOTE: perhaps we should have individual array lists of bufferedImages for each action
            // NOTE: it may be wise to do attach four (one for each direction)
            this.actionFrameMax = 7;
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = false;
            this.actionResume = "IDLE";;
            this.actionRemain = false;
            this.busy = true;
        }
        
        // Temp
        if(action.equals("CHARGE"))
        {
            this.action = "CHARGE";
            this.actionTickNow = 0;
            this.actionTickMax = 0;
            this.actionFrameNow = 1;
            //this.actionFrameImg
            // NOTE: perhaps we should have individual array lists of bufferedImages for each action
            // NOTE: it may be wise to do attach four (one for each direction)
            this.actionFrameMax = 3;
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = true;
            this.actionResume = "";
            this.actionRemain = true;
            this.actionEffect = new EffectCharge(this);
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
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = true;
            this.actionResume = "";
            this.actionRemain = true;
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
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = false;
            this.actionResume = "DEATH";;
            this.actionRemain = false;
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
            this.actionOffsetX = 0;
            this.actionOffsetY = 0;
            this.actionRepeat = false;
            this.actionResume = "";;
            this.actionRemain = true;
            this.actionDamage = false;
            this.actionDamageFrame = 0;
            this.death();
        }
    }
    
    public void setAI(boolean value)
    {
        this.aiActive = value;
        if(value == true)
        {
            // NOTE: we may need to give the entity a push to build a strategy
        }
        else
        {
            // NOTE: we may need to abandon some ongoing actions
        }
    }
    
    public void setDirection(String direction)
    {
        this.face = direction;
    }
    
    public void setMesh(int wide, int high, int offsetX, int offsetY)
    {
        this.meshSizeX = wide;
        this.meshSizeY = high;
        this.meshOffsetX = offsetX;
        this.meshOffsetY = offsetY;
    }
    
    public void tick()
    {
        this.tickAction();
        this.tickEffect();
    }
    
    private void tickAction()
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
            
            // Walking
            if(this.action.equals("WALK"))
            {
                this.movePush(this.face);
            }
            
            // Frames Done
            if(this.actionFrameNow > this.actionFrameMax)
            {
                // Action complete (repeat or resume?)
                if(this.actionRepeat)
                {
                    if(!this.actionRemain) {this.actionFrameNow = 1;}
                }
                else {this.setAction(this.actionResume);}
            }
        }
    }
    
    private void tickEffect()
    {
        /*for(int e = 0; e < actionEffects.size(); e++)
        {
            actionEffects.get(e).tick();
        }*/
    }
    
}
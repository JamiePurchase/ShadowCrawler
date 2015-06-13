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
    
    // Status
    private boolean statusKO;
    
    // Action
    private String action;
    private boolean actionHold;
    private int actionTickNow, actionTickMax, actionFrameNow, actionFrameMax;
    private boolean actionRepeat, actionRemain;
    private String actionResume;
    private boolean actionDamage;
    private int actionDamageFrame;
    private Damage actionDamageObject;
    private Effect actionEffect;
    
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
        
        // Status
        this.statusKO = false;
        
        // Action
        this.setAction("IDLE");
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
    
    public Rectangle getMesh()
    {
        return new Rectangle(this.getPosX() - this.meshOffsetX, this.getPosY() - this.meshOffsetY, this.meshSizeX, this.meshSizeY);
    }
    
    public int getPosTileX()
    {
        return this.getPosX() / 32;
    }
    
    public int getPosTileY()
    {
        return this.getPosY() / 32;
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
        if(this.action.equals("ATTACK"))
        {
            return (this.posX - this.board.getScrollPosX()) - 64;
        }
        
        // Idle
        return this.posX - this.board.getScrollPosX();
    }
    
    private int getRenderPosY()
    {
        if(this.action.equals("ATTACK"))
        {
            return (this.posY - this.board.getScrollPosY()) - 64;
        }
        
        // Idle
        return this.posY - this.board.getScrollPosY();
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
        // Debug
        Console.echo(this.ref + " is moving " + direction);
        
        this.setDirection(direction);
        //if(this.moveValid(direction))
        //{

            // temp
            this.busy = true;
        
            this.setAction("WALK");
        //}
        //this.moveCollide();
        Console.echo(this.ref + " action = " + this.action);
    }
    
    public void moveHalt(String direction)
    {
        this.setAction("IDLE");
        this.busy = false;
    }
    
    private boolean moveValid(String direction)
    {
        int targetX = this.getPosTileX();
        int targetY = this.getPosTileY();
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
        if(meshRender) {this.renderMesh(gfx);}
        gfx.drawImage(this.getRenderImage(), this.getRenderPosX(), this.getRenderPosY(), null);
    }
    
    private void renderMesh(Graphics gfx)
    {
        gfx.setColor(Color.CYAN);
        gfx.drawRect(this.getMesh().x, this.getMesh().y, this.getMesh().width, this.getMesh().height);
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
            this.actionRepeat = false;
            this.actionResume = "IDLE";
            this.actionRemain = false;
            this.actionDamage = true;
            this.actionDamageFrame = 5;
            int targetX = this.posX;
            int targetY = this.posY;
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
            this.actionRepeat = false;
            this.actionResume = "";;
            this.actionRemain = true;
            this.actionDamage = false;
            this.actionDamageFrame = 0;
            this.death();
        }
    }
    
    public void setDirection(String direction)
    {
        this.face = direction;
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
                if(this.face == "N") {this.posY -= 4;}
                if(this.face == "S") {this.posY += 4;}
                if(this.face == "E") {this.posX += 4;}
                if(this.face == "W") {this.posX -= 4;}
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
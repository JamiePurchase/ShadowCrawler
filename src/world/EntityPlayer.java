package world;

import app.Application;
import app.Console;
import gfx.Tileset;
import input.InputKeyboard;

public class EntityPlayer extends Entity
{
    private InputKeyboard keyboard;
    
    public EntityPlayer(String ref, Board board, int tileX, int tileY, Tileset tileset)
    {
        super(ref, board, tileX, tileY, tileset);
        this.keyboard = Application.getInputKeyboard();
    }
    
    private void death()
    {
        // Game over?
    }
    
    public void tick()
    {
        this.tickKeyboard();
        super.tick();
    }
    
    public void tickKeyboard()
    {
        if(!this.getBusy())
        {
            // Walk
            if(this.keyboard.getKeyObject("DOWN").isPressed()) {this.move("S");}
            else if(this.keyboard.getKeyObject("LEFT").isPressed()) {this.move("W");}
            else if(this.keyboard.getKeyObject("RIGHT").isPressed()) {this.move("E");}
            else if(this.keyboard.getKeyObject("UP").isPressed()) {this.move("N");}
            
            // Charge (hold)
            if(this.keyboard.getKeyObject("CTRL").isPressed()) {this.charge(true);}
            else {this.charge(false);}
            
            // Alt (hold to guard) - press to parry? skill?
            //if(this.inputKeyboard.getKeyObject("ALT").isPressed()) {this.tempPlayer.guard();}
        }
        else
        {
            if(this.getAction().equals("GUARD"))
            {
                if(this.keyboard.getKeyObject("ALT").isPressed()) {this.guard();}
                else {this.guardDone();}
            }
        }
    }
    
}
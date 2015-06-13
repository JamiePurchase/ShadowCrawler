package world;

import app.Application;
import app.Console;
import gfx.Tileset;
import input.InputKeyboard;
import input.InputKeyboardKey;
import state.StateBoard;

public class EntityPlayer extends Entity
{
    private InputKeyboard keyboard;
    
    public EntityPlayer(String ref, Board board, int tileX, int tileY, Tileset tileset)
    {
        super(ref, board, tileX * 32, tileY * 32, tileset);
        this.keyboard = Application.getInputKeyboard();
    }
    
    private void death()
    {
        // Game over?
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        if(!this.getBusy())
        {
            // Attack
            if(key.getRef().equals("SPACE")) {this.setAction("ATTACK");}
            
            // Walk
            if(key.getRef().equals("DOWN")) {this.move("S");}
            if(key.getRef().equals("LEFT")) {this.move("W");}
            if(key.getRef().equals("RIGHT")) {this.move("E");}
            if(key.getRef().equals("UP")) {this.move("N");}
            
            // Charge (hold)
            if(key.getRef().equals("CTRL")) {this.charge(true);}
            
            // Alt (hold to guard) - press to parry? skill?
            //if(this.inputKeyboard.getKeyObject("ALT").isPressed()) {this.tempPlayer.guard();}
        }
        else
        {
            // Walk
            if(key.getRef().equals("DOWN")) {this.moveHalt("S");}
            if(key.getRef().equals("LEFT")) {this.moveHalt("W");}
            if(key.getRef().equals("RIGHT")) {this.moveHalt("E");}
            if(key.getRef().equals("UP")) {this.moveHalt("N");}
            
            // Charge
            if(key.getRef().equals("CTRL")) {this.charge(false);}
            if(this.getAction().equals("GUARD"))
            {
                if(this.keyboard.getKeyObject("ALT").isPressed()) {this.guard();}
                else {this.guardDone();}
            }
        }
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
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
    
    public EntityPlayer(String ref, Board board, int posX, int posY, Tileset tileset)
    {
        super(ref, board, posX, posY, tileset);
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
            if(key.getRef().equals("SPACE")) {this.attack();}
            
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
            
            if(this.getAction().equals("GUARD"))
            {
                if(this.keyboard.getKeyObject("ALT").isPressed()) {this.guard();}
                else {this.guardDone();}
            }
        }
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        // Walk
        if(key.getType().equals("DIRECTION")) {this.moveHalt();}
        
        // Charge
        if(key.getRef().equals("CTRL")) {this.charge(false);}
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
package world;

import app.Application;
import app.Console;
import gfx.Drawing;
import gfx.Tileset;
import input.InputKeyboard;
import input.InputKeyboardKey;
import java.awt.Graphics;
import state.StateBoard;

public class EntityPlayer extends Entity
{
    private InputKeyboard keyboard;
    
    // TEMP
    private Tileset animAttack;
    
    public EntityPlayer(String ref, Board board, int posX, int posY, Tileset tileset)
    {
        super(ref, board, posX, posY, tileset);
        this.keyboard = Application.getInputKeyboard();
        
        // TEMP
        animAttack = new Tileset("spr|chr|Jakken_Sword5", Drawing.getImage("spritesheet/character/Jakken/Jakken_Sword5.png"), 192, 192, 6, 4);
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
    
    public void render(Graphics gfx)
    {
        if(getMeshRender()) {super.renderMesh(gfx);}
        gfx.drawImage(super.getRenderImage(this.animAttack), super.getRenderPosX(), super.getRenderPosY(), null);
    }
    
    public void tick()
    {
        super.tick();
    }
    
}
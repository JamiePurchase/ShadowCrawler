package menu;

import app.Application;
import gfx.Drawing;
import gfx.Theme;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import player.PartyCharacter;
import state.StateBoard;

public class MenuEquipment extends Menu
{
    // TEMP
    private PartyCharacter character;
    
    // temp
    private int tempWeapon;
    
    public MenuEquipment(StateBoard board)
    {
        super(board);
        
        // TEMP
        character = new PartyCharacter("Jakken");
        
        // temp
        tempWeapon = 1;
        // NOTE: we should retrieve equipment data from the character object
    }
    
    public void render(Graphics gfx)
    {
        // Frame
        this.renderFrame(gfx);
        
        // Name
        /*gfx.setFont(Theme.getFont("MENUNAME"));
        gfx.setColor(Theme.getColour("TEXT"));
        Drawing.write(gfx, "JAKKEN", 683, 125, "CENTER");*/
        
        // Equipment
        gfx.setFont(Theme.getFont("MENUOPTION"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("Weapon", 480, 150);
        gfx.drawString("Armour", 480, 250);
        gfx.drawString("Amulet", 480, 350);
        gfx.drawString("Ring", 480, 450);
        
        // Portrait
        this.renderPortrait(gfx);
        
        // Stats (main)
        gfx.setFont(Theme.getFont("MENUSTATS"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("Health", 700, 650);
        
        // Stats (detailed)
        gfx.setFont(Theme.getFont("MENUSTATS"));
        gfx.setColor(Theme.getColour("TEXT"));
        gfx.drawString("Attack", 1000, 150);
        gfx.drawString("Defence", 1000, 225);
        gfx.drawString("Elemental", 1000, 550);
        gfx.drawString("Resistance", 1000, 600);
    }
    
    private void renderPortrait(Graphics gfx)
    {
        String image1 = "portrait/" + this.character.getInfoName() + ".png";
        String image2 = "portrait/" + this.character.getInfoName() + "_Sword" + this.tempWeapon + ".png";
        gfx.drawImage(Drawing.getImage(image1), 570, 100, null);
        gfx.drawImage(Drawing.getImage(image2), 570, 100, null);
    }
    
    public void tick()
    {
        if(Application.getInputKeyboard().getKeyPressed() == "ENTER")
        {
            Application.getInputKeyboard().keyPressedDone();
            this.getState().pauseDone();
        }
        if(Application.getInputKeyboard().getKeyPressed() == "SPACE")
        {
            Application.getInputKeyboard().keyPressedDone();
            this.tempWeapon = 5;
        }
    }
    
}
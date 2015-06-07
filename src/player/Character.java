package player;

import item.ItemWeapon;
import java.util.ArrayList;
import mystic.Spell;

public class Character
{
    // Stats
    private int statHealthNow, statHealthMax;
    private int statEnergyNow, statEnergyMax;
    private int statMysticNow, statMysticMax;
    
    // Equipment
    private ItemWeapon equipWeapon;
    //private ItemAmulet equipAmulet;
    
    // Mystic
    private Spell spellActive;
    private ArrayList<Spell> spellList;
    
    public Character()
    {
        
    }
    
    // NOTE: there should be various unlockable skills to choose from as you level up
    // NOTE: these are combat skills (eg: holding the attack button for a powerful charge)
    // NOTE: and many different mystic skills that inflict elemental damage and other effects
    
}
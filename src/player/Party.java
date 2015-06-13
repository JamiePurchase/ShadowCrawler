package player;

import java.util.ArrayList;

public class Party
{
    private ArrayList<PartyCharacter> characters;
    private FormationManager formations;
    
    public Party()
    {
        this.characters = new ArrayList<PartyCharacter>();
        this.formations = new FormationManager();
    }
    
}
package player;

import java.util.ArrayList;

public class FormationManager
{
    private ArrayList<Formation> formations;
    
    public FormationManager()
    {
        this.formations = new ArrayList<Formation>();
        
        // Temp
        this.formations.add(new Formation());
    }
    
    public ArrayList<Formation> getFormations()
    {
        return this.formations;
    }
    
}
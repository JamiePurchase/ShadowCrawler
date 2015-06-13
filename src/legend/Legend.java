package legend;

public class Legend
{
    private String title, description;
    private boolean hidden, unlock; 
    
    public Legend(String name, String desc, boolean hidden, boolean unlock)
    {
        this.title = name;
        this.description = desc;
        this.hidden = hidden;
        this.unlock = unlock;
    }
    
    // NOTE: inner image = 92 x 92, starts at 4,4
}
package world;

public class ActionDamage extends Action
{
    private int damageFrame;
    private Damage damageObject;
    
    public ActionDamage(String ref, boolean hold, int tickMax, int frameMax, boolean repeat, boolean remain, Action resume)
    {
        super(ref, hold, tickMax, frameMax, repeat, remain, resume);
    }
    
}
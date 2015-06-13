package effect;

import world.Entity;

public class EffectCharge extends Effect
{
    private int chargeAmount;
    
    public EffectCharge(Entity entity)
    {
        super(entity, "CHARGE", 0, 0);
    }
    
    public void tickAction()
    {
        
    }
    
}
package effect;

import world.Entity;

public abstract class Effect
{
    private Entity entity;
    private String ref;
    private int tickNow, tickMax;
    
    public Effect(Entity entity, String ref, int tick, int frame)
    {
        this.entity = entity;
        this.ref = ref;
        this.tickNow = 0;
        this.tickMax = tick;
    }
    
    public void tick()
    {
        this.tickNow += 1;
        if(this.tickNow > this.tickMax)
        {
            this.tickNow = 0;
            this.tickAction();
        }
    }
    
    abstract void tickAction();
    
}
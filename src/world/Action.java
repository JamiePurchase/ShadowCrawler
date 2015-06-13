package world;

import effect.Effect;

public class Action
{
    private String ref;
    private boolean hold;
    private int tickNow, tickMax, frameNow, frameMax;
    private boolean repeat, remain;
    private Action resume;
    private Effect effect;
    
    public Action(String ref, boolean hold, int tickMax, int frameMax, boolean repeat, boolean remain, Action resume)
    {
        
    }
    
}
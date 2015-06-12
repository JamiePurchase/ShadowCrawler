package state;

import java.awt.Graphics;

public abstract class State
{
    public abstract void render(Graphics gfx);
    public abstract void terminate();
    public abstract void tick();
}
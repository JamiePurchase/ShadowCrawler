package state;

import input.InputKeyboardKey;
import java.awt.Graphics;

public abstract class State
{
    public abstract void keyPressed(InputKeyboardKey key);
    public abstract void keyReleased(InputKeyboardKey key);
    public abstract void render(Graphics gfx);
    public abstract void terminate();
    public abstract void tick();
}
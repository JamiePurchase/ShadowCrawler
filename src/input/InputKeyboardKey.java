package input;

public class InputKeyboardKey
{
    private boolean pressed;
    
    public InputKeyboardKey()
    {
        this.pressed = false;
    }
    
    public boolean isPressed()
    {
        return this.pressed;
    }
    
    public void press()
    {
        this.pressed = true;
    }
    
    public void release()
    {
        this.pressed = false;
    }
}
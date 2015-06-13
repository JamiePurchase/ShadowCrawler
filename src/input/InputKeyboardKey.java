package input;

public class InputKeyboardKey
{
    private String ref;
    private boolean pressed;
    
    public InputKeyboardKey(String ref)
    {
        this.ref = ref;
        this.pressed = false;
    }
    
    public String getRef()
    {
        return this.ref;
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
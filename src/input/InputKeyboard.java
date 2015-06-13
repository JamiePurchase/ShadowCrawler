package input;

import app.Console;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InputKeyboard implements KeyListener
{
    // New
    public static Map<String, InputKeyboardKey> keyboard;
    
    private static String keyPressed = "NONE";
    private static boolean keyPressedNow = false;
    private static String keyReleased = "NONE";
    private static boolean keyReleasedEvent = false;
    
    public InputKeyboard()
    {
        // Hashmap of Keys
        this.keyboard = new HashMap<String, InputKeyboardKey>();
        
        // Command Keys
        this.keyboard.put("ENTER", new InputKeyboardKey());
        this.keyboard.put("ESCAPE", new InputKeyboardKey());
        this.keyboard.put("SPACE", new InputKeyboardKey());
        
        // Directional Keys
        this.keyboard.put("DOWN", new InputKeyboardKey());
        this.keyboard.put("LEFT", new InputKeyboardKey());
        this.keyboard.put("RIGHT", new InputKeyboardKey());
        this.keyboard.put("UP", new InputKeyboardKey());
        
        // Ability Keys
        this.keyboard.put("CTRL", new InputKeyboardKey());
        this.keyboard.put("ALT", new InputKeyboardKey());
    }
    
    private String getKeyName(KeyEvent e)
    {
        // Command Keys
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {return "ENTER";}
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {return "ESCAPE";}
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {return "SPACE";}
        
        // Directional Keys
        if(e.getKeyCode() == KeyEvent.VK_DOWN) {return "DOWN";}
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {return "LEFT";}
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {return "RIGHT";}
        if(e.getKeyCode() == KeyEvent.VK_UP) {return "UP";}
        
        // Ability Keys
        if(e.getKeyCode() == KeyEvent.VK_ALT) {return "ALT";}
        if(e.getKeyCode() == KeyEvent.VK_CONTROL) {return "CTRL";}
        
        // Unknown Key
        return "UNKNOWN";
    }
    
    public InputKeyboardKey getKeyObject(String name)
    {
        return this.keyboard.get(name);
    }

    public static String getKeyPressed()
    {
        return keyPressed;
    }
    
    public static boolean getKeyPressedNow()
    {
        return keyPressedNow;
    }

    public static String getKeyReleased()
    {
        return keyReleased;
    }

    public void keyTyped(KeyEvent e)
    {
        //
    }

    public void keyPressed(KeyEvent e)
    {
        Console.echoRed("Key Pressed : " + this.getKeyName(e));
        if(!this.getKeyName(e).equals("UNKNOWN"))
        {
            this.keyboard.get(this.getKeyName(e)).press();
            this.keyPressed = this.getKeyName(e);
            this.keyPressedNow = true;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if(!this.getKeyName(e).equals("UNKNOWN"))
        {
            this.keyboard.get(this.getKeyName(e)).release();
            this.keyReleased = this.getKeyName(e);
            this.keyReleasedEvent = true;
        }
    }
    
    public static void keyPressedDone()
    {
        keyPressed = "NONE";
        keyPressedNow = false;
    }

    public static void keyReleasedDone()
    {
        keyReleased = "NONE";
        keyReleasedEvent = false;
    }
}
package editor;

import gfx.Theme;
import input.InputKeyboard;
import input.InputMouse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JPanel;
import state.State;
import world.BoardDao;

public class Editor extends JPanel implements Runnable
{
    // Editor
    private static String appTitle;
    private static int appSizeX, appSizeY;
    private static Window appFrame;
    private Thread appThread;
    private boolean appRunning;
    private static State appState;
    private static Theme appTheme;
    
    // Input
    private static InputKeyboard inputKeyboard;
    private static InputMouse inputMouse;

    public Editor()
    {
        // Application
        this.appTitle = "Shadow Crawler Editor";
        this.appSizeX = 1344;
        this.appSizeY = 672;
        this.appTheme = new Theme();

        // Input
        inputKeyboard = new InputKeyboard();
        inputMouse = new InputMouse();
    }
    
    private void createWindow()
    {
        this.appFrame = new Window(this.appTitle, this.appSizeX, this.appSizeY);
        //this.appState = new StateBoard();
        this.appState = new StateAudio();
    }
    
    public static int getAppHeight()
    {
        return appSizeY;
    }
    
    public static int getAppWidth()
    {
        return appSizeX;
    }
        
    public static InputKeyboard getInputKeyboard()
    {
        return inputKeyboard;
    }

    public static InputMouse getInputMouse()
    {
        return inputMouse;
    }
        
    public static String getResourcePath()
    {
        return "C:/Users/Jamie/Documents/NetBeansProjects/ShadowCrawler/res/";
    }
    
    public static State getState()
    {
        return appState;
    }
    
    public static Color getThemeColour(String ref)
    {
        return appTheme.getColour(ref);
    }
    
    public static Font getThemeFont(String ref)
    {
        return appTheme.getFont(ref);
    }
    
    private void render()
    {
        BufferStrategy buffer = appFrame.getCanvas().getBufferStrategy();
        if(buffer == null)
        {
            appFrame.getCanvas().createBufferStrategy(3);
            return;
        }
        Graphics gfx = buffer.getDrawGraphics();
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, this.appSizeX, this.appSizeY);
        if(this.getState() != null) {this.getState().render(gfx);}
        buffer.show();
        gfx.dispose();
    }
    
    public void run()
    {
        // Render speed
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // Create window
        createWindow();

        // Main game loop
        while(this.appRunning)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if(delta >= 1)
            {			
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000)
            {
                ticks = 0;
                timer = 0;
            }
        }

        // End game
        stop();
    }
    
    public static void setState(State newState)
    {
        appState = newState;
        appFrame.setFocus();
    }
        
    public synchronized void start()
    {
        if(this.appRunning == false)
        {
            this.appRunning = true;
            this.appThread = new Thread(this);
            this.appThread.start();
        }
    }

    public synchronized void stop()
    {
        if(this.appRunning == true)
        {
            try {this.appThread.join();}
            catch (InterruptedException e) {System.out.println(e);}
        }
    }

    private void tick()
    {
        this.getState().tick();
    }
    
}
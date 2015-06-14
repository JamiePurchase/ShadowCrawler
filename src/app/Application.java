package app;

import audio.AudioManager;
import file.FileRead;
import gfx.Theme;
import input.InputKeyboard;
import input.InputKeyboardKey;
import input.InputMouse;
import state.State;
import state.StateInit;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.swing.JPanel;
import player.Campaign;
import state.StateTitle;
import tileset.TilesetManager;
import ui.HintBar;

public class Application extends JPanel implements Runnable
{
    // Application
    private static String appTitle;
    private static int appSizeX, appSizeY;
    private static Window appFrame;
    private Thread appThread;
    private boolean appRunning;
    private static State appState;
    private static State appStateWait;
    private static AudioManager appAudio;
    private static Campaign appCampaign;
    
    // Version
    private static String verInfo, verDate;
    
    // Input
    private static InputKeyboard inputKeyboard;
    private static InputMouse inputMouse;
    
    // Graphics
    private static Theme gfxTheme;
    private static TilesetManager gfxTiles;
    
    // Quickstart
    private static boolean quickstart;

    public Application()
    {
        // Application
        this.appTitle = "Shadow Crawler";
        this.appSizeX = 1366;
        this.appSizeY = 768;
        appAudio = new AudioManager();
        
        // Version
        this.versionInit();

        // Input
        inputKeyboard = new InputKeyboard();
        inputMouse = new InputMouse();
        
        // Graphics
        gfxTheme = new Theme();
        gfxTiles = new TilesetManager();
    }
    
    private void createWindow()
    {
        this.appFrame = new Window(this.appTitle, this.appSizeX, this.appSizeY);
        if(this.quickstart) {this.appState = new StateTitle();}
        else {this.appState = new StateInit();}
        this.appStateWait = null;
    }
    
    public static int getAppHeight()
    {
        return appSizeY;
    }
    
    public static int getAppWidth()
    {
        return appSizeX;
    }
    
    public static AudioManager getAudio()
    {
        return appAudio;
    }
    
    public static Campaign getCampaign()
    {
        return appCampaign;
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
        return "C:/Users/Jamie/Documents/NetBeansProjects/ShadowCrawler/src/res/";
    }
    
    public static State getState()
    {
        return appState;
    }
    
    public static Color getThemeColour(String ref)
    {
        return gfxTheme.getColour(ref);
    }
    
    public static Font getThemeFont(String ref)
    {
        return gfxTheme.getFont(ref);
    }
    
    public static void keyPressed(InputKeyboardKey key)
    {
        /*if(key.getRef().equals("ESCAPE")) {System.exit(0);}
        else {appState.keyPressed(key);}*/
        
        appState.keyPressed(key);
    }
    
    public static void keyReleased(InputKeyboardKey key)
    {
        appState.keyReleased(key);
    }
    
    public static void loadAudio()
    {
        appAudio.load();
    }
    
    public static boolean loadAudioDone()
    {
        return appAudio.getLoadDone();
    }
    
    public static void loadTiles()
    {
        gfxTiles.load();
    }
    
    public static boolean loadTilesDone()
    {
        return gfxTiles.getLoadDone();
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
    
    public static void resumeState()
    {
        setState(appStateWait);
        appStateWait = null;
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
    
    public static void setCampaign(Campaign newCampaign)
    {
        appCampaign = newCampaign;
    }
    
    public static void setState(State newState)
    {
        setState(newState, false);
    }
    
    public static void setState(State newState, boolean wait)
    {
        //if(wait) {appStateWait = appState;}
        if(appState != null) {appState.terminate();}
        appState = newState;
        appFrame.setFocus();
    }
        
    public synchronized void start(boolean quickstart)
    {
        if(this.appRunning == false)
        {
            if(quickstart) {startQuickstart();}
            this.appRunning = true;
            this.appThread = new Thread(this);
            this.appThread.start();
        }
    }
    
    private void startQuickstart()
    {
        this.quickstart = true;
        appAudio.load();
        gfxTiles.load();
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
        // Audio Manager
        this.appAudio.tick();
        
        // State Tick
        this.getState().tick();
    }
    
    public static HintBar versionHint()
    {
        String hint1 = "Shadow Crawler (v" + verInfo + ") built with Java in Netbeans IDE 8.0.2.";
        String hint2 = "Last updated  " + verDate;
        return new HintBar(hint1, hint2);
    }
    
    private void versionInit()
    {
        FileRead fr = new FileRead("application/info.froth");
        try
        {
            String[] info = fr.FileReadData();
            verInfo = info[0];
            verDate = info[1];
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    
}
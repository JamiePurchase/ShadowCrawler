package audio;

import file.FileRead;
import java.io.IOException;

public class AudioManager
{
    private static boolean active;
    private static boolean musicActive;
    private static String musicFile;
    private static boolean soundActive;
    private static String soundFile;
    private static int soundTick;
    private static int volume;
    private boolean loadDone;

    public AudioManager()
    {
        // Data is loaded during the init state
        this.active = true;
        this.musicActive = false;
        this.soundActive = false;
        this.volume = 100;
        this.loadDone = false;
    }

    public void changeMusic(String music)
    {
        if(musicActive){stopMusic();}
        playMusic(music);
    }
    
    public boolean getLoadDone()
    {
        return this.loadDone;
    }

    public boolean getMusicActive()
    {
        return musicActive;
    }

    public boolean getSoundActive()
    {
        return soundActive;
    }
    
    public void load()
    {
        // Player
        AudioPlayer.init();
        
        // Music Files
        this.loadFile("audio/music.froth");
        
        // Sound Files
        //this.loadFile("audio/sound.froth");
        
        // Speech Files
        this.loadFile("audio/speech.froth");
        
        // Done
        this.loadDone = true;
    }
    
    private void loadFile(String file)
    {
        FileRead fr = new FileRead(file);
        try
        {
            String[] music = fr.FileReadData();
            for(int m = 0; m < music.length; m++)
            {
                String[] musicData = music[m].split("\\|");
                AudioPlayer.load(musicData[1], musicData[0]);
            }
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }

    public void playMusic(String music)
    {
        AudioPlayer.play(music);
        musicActive = true;
        musicFile = music;
    }

    public void playSound(String sound)
    {
        AudioPlayer.play(sound);
        soundActive = true;
        soundFile = sound;
        soundTick = 0;
    }

    public void setVolume(int newVolume)
    {
        volume = newVolume;
        if(newVolume < 1) {active = false;}
        else {active = true;}
    }

    public void stopMusic()
    {
        AudioPlayer.stop(musicFile);
        musicActive = false;
        musicFile = "";
    }

    public void stopSound()
    {
        //AudioPlayer.stop(soundFile);
        soundActive = false;
        soundFile = "";
    }

    public void tick()
    {
        if(getSoundActive())
        {
            soundTick += 1;
            //if(soundTick>1){stopSound();}
        }
    }
}
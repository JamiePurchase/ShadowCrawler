package state;

import app.Application;
import gfx.Drawing;
import input.InputKeyboardKey;
import java.awt.Graphics;
import player.Campaign;
import player.CampaignDao;

public class StateLoader extends State
{
    private String task;
    
    public StateLoader(String task)
    {
        this.task = task;
        if(task == "CAMPAIGN_NEW")
        {
            Campaign campaign = CampaignDao.newCampaign();
            Application.setCampaign(campaign);
        }
    }

    public void keyPressed(InputKeyboardKey key)
    {
        //
    }

    public void keyReleased(InputKeyboardKey key)
    {
        //
    }

    public void render(Graphics gfx)
    {
        Drawing.fillScreen(gfx, 0, 0, 0);
        if(this.task == "CAMPAIGN_NEW")
        {
            //
        }
    }

    public void terminate()
    {
        //
    }

    public void tick()
    {
        // TEMP
        if(this.task == "CAMPAIGN_NEW") {Application.setState(new StateScene());}
    }
    
}
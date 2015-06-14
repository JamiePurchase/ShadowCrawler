package player;

import item.Inventory;
import journal.JournalManager;

public class CampaignDao
{
    
    public static Campaign newCampaign()
    {
        // Build all of the necessary components
        GameStats stats = new GameStats();
        Party party = new Party();
        Inventory items = new Inventory();
        QuestLog quests = new QuestLog();
        JournalManager journal = new JournalManager();
        return new Campaign(null, stats, party, items, quests, journal);
    }
    
    /*public static Campaign loadCampaign()
    {
        // NOTE: need to load EVERYTHING from the data files?
        // can some things be left unloaded until they're needed (journal, overall stats)?
    }*/
    
}
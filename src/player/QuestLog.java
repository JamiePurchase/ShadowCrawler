package player;

import java.util.HashMap;
import java.util.Map;

public class QuestLog
{
    Map<String, Quest> quests;
    
    public QuestLog()
    {
        quests = new HashMap<String, Quest>();
    }
}
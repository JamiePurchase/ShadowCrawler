package ui;

public class MenuOption
{
    private String value;
    private int areaX;
    private int areaY;
    
    public MenuOption(String value, int posX, int posY)
    {
        this.value = value;
        this.areaX = posX;
        this.areaY = posY;
    }
    
    public int getPosX()
    {
        return this.areaX;
    }
    
    public int getPosY()
    {
        return this.areaY;
    }
    
    public String getValue()
    {
        return this.value;
    }
}
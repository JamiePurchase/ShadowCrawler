package map;

import java.awt.image.BufferedImage;

public class MapScenery
{
    private BufferedImage image;
    private int posX, posY;
    
    public MapScenery(BufferedImage image, int posX, int posY)
    {
        this.image = image;
        this.posX = posX;
        this.posY = posY;
    }
    
}
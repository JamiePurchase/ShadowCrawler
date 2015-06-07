package gfx;

import java.awt.image.BufferedImage;

public class Tileset
{
    private BufferedImage tileSheet;
    private int tileWide;
    private int tileHigh;
    private int tileCols;
    private int tileRows;
    
    public Tileset(BufferedImage sheetFile)
    {
        // Debug
        System.out.println("New Tileset (sheetFile " + sheetFile.getWidth() + "x" + sheetFile.getHeight() + ")");
        
        this.tileSheet = sheetFile;
        this.tileCols = sheetFile.getWidth() / 32;
        this.tileRows = sheetFile.getHeight() / 32;
        this.tileWide = 32;
        this.tileHigh = 32;
    }
    
    public Tileset(BufferedImage sheetFile, int imgWide, int imgHigh, int imgCols, int imgRows)
    {
        this.tileSheet = sheetFile;
        this.tileCols = imgCols;
        this.tileRows = imgRows;
        this.tileWide = imgWide;
        this.tileHigh = imgHigh;
    }
    
    public int getImageHeight()
    {
        return this.tileHigh;
    }
    
    public int getImageWidth()
    {
        return this.tileWide;
    }
    
    public BufferedImage getTileAt(int col, int row)
    {
        //System.out.println("Tileset tileGetAt(" + col + ", " + row + ")");
        if(col <= this.tileCols && row <= this.tileRows)
        {
            int tilePosX = (col - 1) * this.tileWide;
            int tilePosY = (row - 1) * this.tileHigh;
            //System.out.println("tilePosX = " + tilePosX + ", tilePosY = " + tilePosY);
            return this.tileSheet.getSubimage(tilePosX, tilePosY, this.tileWide, this.tileHigh);
        }
        return this.tileSheet.getSubimage(0, 0, this.tileWide, this.tileHigh);
    }
    
    private int getTileCount()
    {
        return this.tileCols * this.tileRows;
    }
    
    public int getTileCountX()
    {
        return this.tileCols;
    }
    
    public int getTileCountY()
    {
        return this.tileRows;
    }
    
    public BufferedImage[] getTileset()
    {
        BufferedImage[] tilesetArray = new BufferedImage[this.getTileCount()];
        int tilesetCount = 0;
        for(int row = 1; row <= this.tileRows; row++)
        {
            for(int col = 1; col <= this.tileCols; col++)
            {
                tilesetArray[tilesetCount] = this.getTileAt(col, row);
                tilesetCount += 1;
            }
        }
        return tilesetArray;
    }
    
}
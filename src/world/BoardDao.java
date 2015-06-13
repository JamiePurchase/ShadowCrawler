package world;

import file.FileWrite;
import gfx.Drawing;
import gfx.Tileset;
import java.awt.Color;
import java.io.IOException;

public class BoardDao
{
    
    public static Board loadBoard(String file, boolean editor)
    {
        Board board = new Board();
        board.setBackground(Color.BLACK);
        
        // Editor
        if(editor) {board.setPane(0, 37, 1344, 608);}
        
        // Temp
        Tileset crypt = new Tileset("tst|crypt", Drawing.getImage("tileset/crypt.png"));
        
        // Temp
        Tile ground = new Tile(crypt, 1, 1, false);
        Tile wall1 = new Tile(crypt, 2, 3, true);
        Tile wall2 = new Tile(crypt, 2, 2, true);
        
        // Temp
        board.setTerrain(0, 0, ground);
        
        // Temp
        board.setTerrain(9, 15, ground);
        board.setTerrain(10, 15, ground);
        board.setTerrain(11, 15, ground);
        board.setTerrain(12, 15, ground);
        board.setTerrain(13, 15, ground);
        board.setTerrain(14, 15, ground);
        board.setTerrain(15, 15, ground);
        board.setTerrain(16, 15, ground);
        board.setTerrain(17, 15, ground);
        board.setTerrain(18, 15, ground);
        board.setTerrain(19, 15, ground);
        board.setTerrain(20, 15, ground);
        board.setTerrain(21, 15, ground);
        board.setTerrain(22, 15, ground);
        board.setTerrain(23, 15, ground);
        board.setTerrain(24, 15, ground);
        board.setTerrain(25, 15, ground);
        board.setTerrain(26, 15, ground);
        board.setTerrain(27, 15, ground);
        board.setTerrain(28, 15, ground);
        board.setTerrain(29, 15, ground);
        board.setTerrain(30, 15, ground);
        board.setTerrain(31, 15, ground);
        board.setTerrain(32, 15, ground);
        board.setTerrain(9, 16, ground);
        board.setTerrain(10, 16, ground);
        board.setTerrain(11, 16, ground);
        board.setTerrain(12, 16, ground);
        board.setTerrain(13, 16, ground);
        board.setTerrain(14, 16, ground);
        board.setTerrain(15, 16, ground);
        board.setTerrain(16, 16, ground);
        board.setTerrain(17, 16, ground);
        board.setTerrain(18, 16, ground);
        board.setTerrain(19, 16, ground);
        board.setTerrain(20, 16, ground);
        board.setTerrain(21, 16, ground);
        board.setTerrain(22, 16, ground);
        board.setTerrain(23, 16, ground);
        board.setTerrain(24, 16, ground);
        board.setTerrain(25, 16, ground);
        board.setTerrain(26, 16, ground);
        board.setTerrain(27, 16, ground);
        board.setTerrain(28, 16, ground);
        board.setTerrain(29, 16, ground);
        board.setTerrain(30, 16, ground);
        board.setTerrain(31, 16, ground);
        board.setTerrain(32, 16, ground);
        board.setTerrain(9, 17, ground);
        board.setTerrain(10, 17, ground);
        board.setTerrain(11, 17, ground);
        board.setTerrain(12, 17, ground);
        board.setTerrain(13, 17, ground);
        board.setTerrain(14, 17, ground);
        board.setTerrain(15, 17, ground);
        board.setTerrain(16, 17, ground);
        board.setTerrain(17, 17, ground);
        board.setTerrain(18, 17, ground);
        board.setTerrain(19, 17, ground);
        board.setTerrain(20, 17, ground);
        board.setTerrain(21, 17, ground);
        board.setTerrain(22, 17, ground);
        board.setTerrain(23, 17, ground);
        board.setTerrain(24, 17, ground);
        board.setTerrain(25, 17, ground);
        board.setTerrain(26, 17, ground);
        board.setTerrain(27, 17, ground);
        board.setTerrain(28, 17, ground);
        board.setTerrain(29, 17, ground);
        board.setTerrain(30, 17, ground);
        board.setTerrain(31, 17, ground);
        board.setTerrain(32, 17, ground);
        board.setTerrain(9, 18, ground);
        board.setTerrain(10, 18, ground);
        board.setTerrain(11, 18, ground);
        board.setTerrain(12, 18, ground);
        board.setTerrain(13, 18, ground);
        board.setTerrain(14, 18, ground);
        board.setTerrain(15, 18, ground);
        board.setTerrain(16, 18, ground);
        board.setTerrain(17, 18, ground);
        board.setTerrain(18, 18, ground);
        board.setTerrain(19, 18, ground);
        board.setTerrain(20, 18, ground);
        board.setTerrain(21, 18, ground);
        board.setTerrain(22, 18, ground);
        board.setTerrain(23, 18, ground);
        board.setTerrain(24, 18, ground);
        board.setTerrain(25, 18, ground);
        board.setTerrain(26, 18, ground);
        board.setTerrain(27, 18, ground);
        board.setTerrain(28, 18, ground);
        board.setTerrain(29, 18, ground);
        board.setTerrain(30, 18, ground);
        board.setTerrain(31, 18, ground);
        board.setTerrain(32, 18, ground);
        
        // Wall
        board.setTerrain(9, 11, wall2);
        board.setTerrain(10, 11, wall2);
        board.setTerrain(11, 11, wall2);
        board.setTerrain(12, 11, wall2);
        board.setTerrain(13, 11, wall2);
        board.setTerrain(14, 11, wall2);
        board.setTerrain(15, 11, wall2);
        board.setTerrain(16, 11, wall2);
        board.setTerrain(17, 11, wall2);
        board.setTerrain(18, 11, wall2);
        board.setTerrain(19, 11, wall2);
        board.setTerrain(20, 11, wall2);
        board.setTerrain(21, 11, wall2);
        board.setTerrain(22, 11, wall2);
        board.setTerrain(23, 11, wall2);
        board.setTerrain(24, 11, wall2);
        board.setTerrain(25, 11, wall2);
        board.setTerrain(26, 11, wall2);
        board.setTerrain(27, 11, wall2);
        board.setTerrain(28, 11, wall2);
        board.setTerrain(29, 11, wall2);
        board.setTerrain(30, 11, wall2);
        board.setTerrain(31, 11, wall2);
        board.setTerrain(32, 11, wall2);
        board.setTerrain(19, 12, wall2);
        board.setTerrain(9, 12, wall2);
        board.setTerrain(10, 12, wall2);
        board.setTerrain(11, 12, wall2);
        board.setTerrain(12, 12, wall2);
        board.setTerrain(13, 12, wall2);
        board.setTerrain(14, 12, wall2);
        board.setTerrain(15, 12, wall2);
        board.setTerrain(16, 12, wall2);
        board.setTerrain(17, 12, wall2);
        board.setTerrain(18, 12, wall2);
        board.setTerrain(20, 12, wall2);
        board.setTerrain(21, 12, wall2);
        board.setTerrain(22, 12, wall2);
        board.setTerrain(23, 12, wall2);
        board.setTerrain(24, 12, wall2);
        board.setTerrain(25, 12, wall2);
        board.setTerrain(26, 12, wall2);
        board.setTerrain(27, 12, wall2);
        board.setTerrain(28, 12, wall2);
        board.setTerrain(29, 12, wall2);
        board.setTerrain(30, 12, wall2);
        board.setTerrain(31, 12, wall2);
        board.setTerrain(32, 12, wall2);
        board.setTerrain(9, 13, wall2);
        board.setTerrain(10, 13, wall2);
        board.setTerrain(11, 13, wall2);
        board.setTerrain(12, 13, wall2);
        board.setTerrain(13, 13, wall2);
        board.setTerrain(14, 13, wall2);
        board.setTerrain(15, 13, wall2);
        board.setTerrain(16, 13, wall2);
        board.setTerrain(17, 13, wall2);
        board.setTerrain(18, 13, wall2);
        board.setTerrain(19, 13, wall2);
        board.setTerrain(20, 13, wall2);
        board.setTerrain(21, 13, wall2);
        board.setTerrain(22, 13, wall2);
        board.setTerrain(25, 13, wall2);
        board.setTerrain(26, 13, wall2);
        board.setTerrain(27, 13, wall2);
        board.setTerrain(28, 13, wall2);
        board.setTerrain(29, 13, wall2);
        board.setTerrain(30, 13, wall2);
        board.setTerrain(9, 14, wall1);
        board.setTerrain(10, 14, wall1);
        board.setTerrain(11, 14, wall1);
        board.setTerrain(12, 14, wall1);
        board.setTerrain(13, 14, wall1);
        board.setTerrain(14, 14, wall1);
        board.setTerrain(15, 14, wall1);
        board.setTerrain(16, 14, wall1);
        board.setTerrain(17, 14, wall1);
        board.setTerrain(18, 14, wall1);
        board.setTerrain(19, 14, wall1);
        board.setTerrain(20, 14, wall1);
        board.setTerrain(21, 14, wall1);
        board.setTerrain(22, 14, wall1);
        board.setTerrain(25, 14, wall1);
        board.setTerrain(26, 14, wall1);
        board.setTerrain(27, 14, wall1);
        board.setTerrain(28, 14, wall1);
        board.setTerrain(29, 14, wall1);
        board.setTerrain(30, 14, wall1);
        
        // Temp (door)
        board.setTerrain(23, 13, new Tile(crypt, 3, 2, true));
        board.setTerrain(24, 13, new Tile(crypt, 4, 2, true));
        board.setTerrain(23, 14, new Tile(crypt, 3, 3, true));
        board.setTerrain(24, 14, new Tile(crypt, 4, 3, true));
        
        // Temp (crack)
        board.setTerrain(31, 13, new Tile(crypt, 6, 2, true));
        board.setTerrain(32, 13, new Tile(crypt, 7, 2, true));
        board.setTerrain(31, 14, new Tile(crypt, 6, 3, true));
        board.setTerrain(32, 14, new Tile(crypt, 7, 3, true));
        
        // Containers
        board.addContainer("", 13, 17, "JAR_TOMB");
        
        // Vectors
        board.addVector("", 9, 11, 14, 4, true);
        board.addVector("DOOR1", 23, 13, 2, 2, true, new BoardJoin("Gateway", 5));
        board.addVector("", 25, 11, 5, 4, true);
        
        return board;
    }
    
    public static void saveBoard(Board board)
    {
        // Overwrite existing file with one line
        FileWrite fileWrite = new FileWrite(board.getFile(true), false);
        try
        {
            fileWrite.FileWriteLine(board.getFile());
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
        
        // Append other lines
        fileWrite = new FileWrite(board.getFile(true), true);
        try
        {
            fileWrite.FileWriteLine(board.getSizeX() + "|" + board.getSizeY());
            /*fileWrite.FileWriteLine("!! Tilesets");
            fileWrite.FileWriteLine(this.boardTileset[0]);
            fileWrite.FileWriteLine(this.boardTileset[1]);
            fileWrite.FileWriteLine(this.boardTileset[2]);*/
            fileWrite.FileWriteLine("!! Grid");
            for(int y = 0; y < board.getSizeY(); y++)
            {
                for(int x = 0; x < board.getSizeX(); x++)
                {
                    fileWrite.FileWriteLine(board.getTerrain(x, y).getData());
                }
            }
            fileWrite.FileWriteLine("!! End of file");
        }
        catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    
}
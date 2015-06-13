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
        
        // Vectors
        board.addVector(new Vector(board, "", 608, 352, 320, 128, true));
        
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
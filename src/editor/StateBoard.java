package editor;

import gfx.Drawing;
import gfx.Theme;
import gfx.Tileset;
import input.InputKeyboardKey;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import state.State;
import world.Board;
import world.BoardDao;

public class StateBoard extends State
{
    private Board board;
    
    // Brush
    private String brush;
    private Tileset brushTileset;
    private int brushTileX, brushTileY;
    
    // Options
    private boolean optAnimate, optGridlines, optSolidity;
    
    // Modal
    private String modal;
    private boolean modalActive;
    
    public StateBoard()
    {
        this.board = BoardDao.loadBoard("", true);
        this.board.setInput(Editor.getInputKeyboard(), Editor.getInputMouse());
        
        // TEST
        //BoardDao.saveBoard(this.board);
        
        // Brush
        this.brush = "TERRAIN";
        this.brushTileset = new Tileset("tst|crypt", Drawing.getImage("tileset/crypt.png"));
        this.brushTileX = 1;
        this.brushTileY = 1;
        
        // Options
        this.optAnimate = true; // this pauses/resumes animated scenery
        this.optGridlines = false; // this displays gridlines across the board
        this.optSolidity = false; // this displays markers for tile solidity
        
        // Modal
        this.modal = "";
        this.modalActive = false;
        
        // Nexus
        Editor.getInputMouse().nexusAdd("BOARD", 0, 37, Editor.getAppWidth(), 608);
        Editor.getInputMouse().nexusAdd("TOOLBAR_NEW", 0, 0, 100, 36);
        Editor.getInputMouse().nexusAdd("TOOLBAR_OPEN", 100, 0, 100, 36);
        Editor.getInputMouse().nexusAdd("TOOLBAR_SAVE", 200, 0, 100, 36);
        Editor.getInputMouse().nexusAdd("TOOLBAR_SETTINGS", 300, 0, 100, 36);
        Editor.getInputMouse().nexusAdd("BRUSH_TERRAIN", 500, 5, 26, 26);
    }
    
    private BufferedImage getBrushTerrain()
    {
        return this.brushTileset.getTileAt(this.brushTileX, this.brushTileY);
    }
    
    public boolean getOptAnimate()
    {
        return this.optAnimate;
    }
    
    public boolean getOptGridlines()
    {
        return this.optGridlines;
    }
    
    public boolean getOptSolidity()
    {
        return this.optSolidity;
    }
    
    private int getTileX(int click)
    {
        int tileX = 0;
        while(click > 32)
        {
            click -= 32;
            tileX += 1;
        }
        return tileX + this.board.getScrollPosX();
    }
    
    private int getTileY(int click)
    {
        click -= 37;
        int tileY = 0;
        while(click > 32)
        {
            click -= 32;
            tileY += 1;
        }
        return tileY + this.board.getScrollPosY();
    }
    
    public void keyPressed(InputKeyboardKey key)
    {
        
    }
    
    public void keyReleased(InputKeyboardKey key)
    {
        
    }

    @Override
    public void render(Graphics gfx)
    {
        this.renderToolbar(gfx);
        this.board.render(gfx);
        if(this.modalActive)
        {
            if(this.modal.equals("TILESET")) {this.renderModalTileset(gfx);}
        }
        
        // NOTE: last 27 pixels downwards will be the status bar / zoom controls
        // NOTE: add a mouse nexus for the status bar / zoom controls
    }
    
    private void renderModal(Graphics gfx, int modalX, int modalY, int modalW, int modalH)
    {
        gfx.setColor(Color.BLUE);
        gfx.fillRect(modalX - 5, modalY - 5, modalW + 10, modalH + 10);
        gfx.setColor(Color.BLACK);
        gfx.drawRect(modalX - 5, modalY - 5, modalW + 10, modalH + 10);
        gfx.fillRect(modalX, modalY, modalW, modalH);
        
        // NOTE: consider adding a bit of a title bar and requesting a string for the title
    }
    
    public void renderModalTileset(Graphics gfx)
    {
        // Modal Dimensions
        int modalW = this.brushTileset.getTileCountX() * this.brushTileset.getImageWidth();
        int modalH = this.brushTileset.getTileCountY() * this.brushTileset.getImageHeight();
        int modalX = (Editor.getAppWidth() / 2) - (modalW / 2);
        int modalY = (Editor.getAppHeight() / 2) - (modalH / 2);
        
        // Modal Background
        this.renderModal(gfx, modalX, modalY, modalW, modalH);
        
        // Modal
        for(int tileX = 0; tileX < this.brushTileset.getTileCountX(); tileX++)
        {
            for(int tileY = 0; tileY < this.brushTileset.getTileCountY(); tileY++)
            {
                gfx.drawImage(this.brushTileset.getTileAt(tileX + 1, tileY + 1), modalX + (tileX * this.brushTileset.getImageWidth()), modalY + (tileY * this.brushTileset.getImageHeight()), null);
            }
        }
    }
    
    public void renderToolbar(Graphics gfx)
    {
        // Toolbar Background
        gfx.setColor(Drawing.getColorRGB(64, 128, 128));
        gfx.fillRect(0, 0, Editor.getAppWidth(), 36);
        gfx.setColor(Drawing.getColorRGB(96, 160, 160));
        gfx.fillRect(0, 35, Editor.getAppWidth(), 1);
        
        // Toolbar Text
        gfx.setColor(Drawing.getColorRGB(0, 0, 0));
        gfx.setFont(Theme.getFont("STANDARD"));
        gfx.drawString("NEW", 25, 30);
        gfx.drawString("OPEN", 125, 30);
        gfx.drawString("SAVE", 225, 30);
        gfx.drawString("SETTINGS", 325, 30);
        
        // Toolbar Icons
        gfx.setColor(Color.BLACK);
        gfx.fillRect(500, 5, 26, 26);
    }
    
    public void terminate()
    {
        // something
    }

    @Override
    public void tick()
    {
        // Check for mouse input
        if(Editor.getInputMouse().mouseActionPressed) {tickClick();}
        
        // Board animations?
        this.board.tick();
    }
    
    public void tickClick()
    {
        String ref = Editor.getInputMouse().mouseNexusClick;
        if(this.modalActive)
        {
            // NOTE: pick a tile or click on the close button (title bar needs to be added)
        }
        else
        {
            if(Editor.getInputMouse().mouseActionPressedL)
            {
                if(ref.equals("TOOLBAR_NEW"))
                {
                    // NEW BOARD (prompt unsaved changes)
                    Editor.getInputMouse().mouseActionDone();
                }
                if(ref.equals("BRUSH_TERRAIN"))
                {
                    this.modal = "TILESET";
                    this.modalActive = true;
                    Editor.getInputMouse().mouseActionDone();
                }
                if(ref.equals("BOARD"))
                {
                    // Calculate board tile
                    int tileX = this.getTileX(Editor.getInputMouse().mouseCoordsX);
                    int tileY = this.getTileY(Editor.getInputMouse().mouseCoordsY);
                    Editor.getInputMouse().mouseActionDone();

                    // Brush type
                    if(this.brush.equals("TERRAIN")) {this.tickEditTerrain(tileX, tileY);}
                }
            }
            if(Editor.getInputMouse().mouseActionPressedR)
            {

            }
        }
    }
    
    public void tickEditTerrain(int tileX, int tileY)
    {        
        this.board.getTerrain(tileX, tileY).setImage(this.getBrushTerrain());
        this.board.redrawTerrain();
    }
    
}
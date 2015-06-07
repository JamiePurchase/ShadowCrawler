package input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class InputMouse extends MouseAdapter implements MouseMotionListener
{
    // Location
    public int mouseCoordsX;
    public int mouseCoordsY;
    public boolean mouseOnScreen;

    // Actions
    public boolean mouseActionPressed = false;
    public boolean mouseActionPressedL = false;
    public boolean mouseActionPressedR = false;
    public boolean mouseActionPressedW = false;

    // Drag
    public boolean mouseDragActive = false;
    public int mouseDragStartX;
    public int mouseDragStartY;
    public int mouseDragEndX;
    public int mouseDragEndY;

    // Nexus
    public ArrayList<InputNexus> mouseNexus;
    public String mouseNexusClick;
    
    public InputMouse()
    {
        mouseNexus = new ArrayList<InputNexus>();
        mouseNexusClick = "";
    }

    public void mouseActionDone()
    {
        mouseActionPressed = false;
        mouseActionPressedL = false;
        mouseActionPressedR = false;
        mouseActionPressedW = false;
        mouseNexusClick = "";
    }
    
    public void mouseDragged(MouseEvent e)
    {
    	mouseDragEndX = e.getX();
    	mouseDragEndY = e.getY();
    }

    public void mouseEntered (MouseEvent e)
    {
        mouseOnScreen = true;
    }

    public void mouseExited (MouseEvent e)
    {
        mouseOnScreen = false;
    }
    
    public void mouseMoved(MouseEvent e)
    {
        mouseCoordsX = e.getX();
        mouseCoordsY = e.getY();
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        mouseCoordsX = e.getX();
        mouseCoordsY = e.getY();
        String mouseButton = "";
        mouseActionPressed = true;
        if(e.getButton() == MouseEvent.BUTTON1)
        {
            mouseActionPressedL = true;
            mouseButton = "L";
        }
        if(e.getButton() == MouseEvent.BUTTON2)
        {
            mouseActionPressedW = true;
            mouseButton = "W";
        }
        if(e.getButton() == MouseEvent.BUTTON3)
        {
            mouseActionPressedR = true;
            mouseButton = "R";
        }
        mouseNexusClick = nexusCheckRef();

        // Debug
        System.out.println("Mouse click (" + mouseButton + ") ref: " + mouseNexusClick);

        // Temp (not applicable to all buttons!)
        mouseDragActive = true;
        mouseDragStartX = mouseCoordsX;
        mouseDragStartY = mouseCoordsY;
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
        mouseDragActive = false;
        if(e.getButton() == MouseEvent.BUTTON1){mouseActionPressedL = false;}
        if(e.getButton() == MouseEvent.BUTTON2){mouseActionPressedW = false;}
        if(e.getButton() == MouseEvent.BUTTON3){mouseActionPressedR = false;}
    }

    public void nexusAdd(String ref, int posX, int posY, int width, int height)
    {
        mouseNexus.add(new InputNexus(ref, posX, posY, width, height));
    }
    
    public void nexusAdd(InputNexus nexus)
    {
        mouseNexus.add(nexus);
    }

    public int nexusCheck()
    {
        System.out.println("InputMouse.nexusCheck()");
        return nexusCheck(mouseCoordsX, mouseCoordsY);
    }

    public int nexusCheck(int posX, int posY)
    {
        System.out.println("InputMouse.nexusCheck(" + posX + ", " + posY + ")");
        for(int x = 0; x < mouseNexus.size(); x++)
        {
            if(posX >= mouseNexus.get(x).posX1 && posX <= mouseNexus.get(x).posX2 && posY >= mouseNexus.get(x).posY1 && posY <= mouseNexus.get(x).posY2)
            {
                return x;
            }
        }
        return 0;
    }

    public String nexusCheckRef()
    {
        return nexusCheckRef(this.mouseCoordsX, this.mouseCoordsY);
    }

    public String nexusCheckRef(int posX, int posY)
    {
        for(int x = 0; x < mouseNexus.size(); x++)
        {
            if(posX >= mouseNexus.get(x).posX1 && posX <= mouseNexus.get(x).posX2 && posY >= mouseNexus.get(x).posY1 && posY <= mouseNexus.get(x).posY2)
            {
                return mouseNexus.get(x).reference;
            }
        }
        return "";
    }

    public void nexusClear()
    {
        this.mouseNexus = new ArrayList<InputNexus>();
    }

}
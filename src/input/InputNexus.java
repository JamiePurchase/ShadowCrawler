package input;

public class InputNexus
{
    public String reference;
    public int posX1;
    public int posY1;
    public int posX2;
    public int posY2;
    public int width;
    public int height;

    public InputNexus(String ref, int x, int y, int w, int h)
    {
        reference = ref; 
        posX1 = x;
        posY1 = y;
        posX2 = x + w;
        posY2 = y + h;
        width = w;
        height = h;
    }

}
package world;

import java.awt.Color;
import java.awt.Rectangle;

public class VectorJoin extends Vector
{
    private BoardJoin join;
    
    public VectorJoin(Board board, String ref, Rectangle rect, boolean solid, BoardJoin join)
    {
        super(board, ref, rect, solid);
        this.join = join;
        super.setRenderColor(Color.RED);
    }
    
    public BoardJoin getJoin()
    {
        return this.join;
    }
    
}
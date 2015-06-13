package maths;

public class Range
{
    private int rangeMin, rangeMax;
    
    public Range(int min, int max)
    {
        this.rangeMin = min;
        this.rangeMax = max;
    }
    
    public boolean getInRange(int value)
    {
        if(value >= this.rangeMin && value <= this.rangeMax) {return true;}
        return false;
    }
    
}
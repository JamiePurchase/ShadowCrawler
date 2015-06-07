package maths;

import java.util.Random;

public class Mathematics
{

    public static int randomInt(int min, int max)
    {
        return new Random().nextInt((max - min) + 1) + min;
    }
    
}
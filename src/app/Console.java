package app;

public class Console
{
    
    public static void echo(Object object)
    {
        System.out.println(object);
    }
    
    public static void echoRed(String text)
    {
        System.out.println((char)27 + "[31m" + text + (char)27 + "[0m");
    }
    
}
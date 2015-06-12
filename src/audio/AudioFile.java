package audio;

public class AudioFile
{
    private String path, ref;
    
    public AudioFile(String path, String ref)
    {
        this.path = path;
        this.ref = ref;
    }
    
    public String getPath()
    {
        return this.path;
    }
    
    public String getRef()
    {
        return this.ref;
    }
}
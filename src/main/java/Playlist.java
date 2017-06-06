import java.util.ArrayList;
import java.util.List;

public class Playlist {
    public String name;
    public Playlist(String name){
        this.name = name;
    }
    public List<Song> songList = new ArrayList<Song>();

}

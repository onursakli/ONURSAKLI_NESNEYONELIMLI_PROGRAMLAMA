
public class Song {
    public String name, duration, url;
    public Album album;
    public Song(String name, Album album, String duration, String url) {
        this.name = name;
        this.album = album;
        this.duration = duration;
        this.url = url;
    }
}

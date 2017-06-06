import java.util.List;

public interface Medify {
    User registerUser(String userName,String password);
    List<User> findAllUsers();
    Singer createSinger(Singer singer);
    List<Singer> findAllSinger();
    List<Singer> findSingersBy(String name);
    void createAlbum(Singer singer,Album album);
    List<Album> findAllAlbums();
    List<Album> findAlbumsBy(String name);
    List<Album> findAlbumsBy(Singer singer);
    void createPlaylist(User user,Playlist playList);
    List<Playlist> findPlaylistBy(User user);
    List<Playlist> findPlaylistBy(User user, String name);
    Playlist getPlaylistDetail(User user, String name);
    void createSong(Singer singer,Album album,Song song);
    Song getSongDetail(Singer singer,String name);
}

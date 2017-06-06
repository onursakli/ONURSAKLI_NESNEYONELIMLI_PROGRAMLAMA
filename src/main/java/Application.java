import java.util.ArrayList;
import java.util.List;

public class Application implements Medify {
    public List<User> userList = new ArrayList<User>();
    public List<Singer> singerList  = new ArrayList<Singer>();
    public List<Album> albumList = new ArrayList<Album>();
    public List<SingerAlbum> singerAlbumList = new ArrayList<SingerAlbum>();
    public List<UserPlaylist> userPlaylistList = new ArrayList<UserPlaylist>();
    public List<Song> songList = new ArrayList<Song>();
    public List<SingerSong> singerSongList = new ArrayList<SingerSong>();
    public List<Playlist> playlistList = new ArrayList<Playlist>();
    public List<PlaylistSong> playlistSongList = new ArrayList<PlaylistSong>();

    public User registerUser(String userName, String password) {
        User user = new User(userName,password);
        userList.add(user);
        return user;
    }

    public List<User> findAllUsers() {
        return userList;
    }

    public Singer createSinger(Singer singer) {
        singerList.add(singer);
        return singer;
    }

    public List<Singer> findAllSinger() {
        return singerList;
    }

    public List<Singer> findSingersBy(String name) {
        List<Singer> singers = new ArrayList<Singer>();
        for(Singer singer:singerList)
            if(singer.firstName.equalsIgnoreCase(name))
                singers.add(singer);
        return singers;
    }

    public void createAlbum(Singer singer, Album album) {
        SingerAlbum singerAlbum = new SingerAlbum(singer,album);
        System.out.println("singer:"+singer.firstName+singer.lastName+" album:"+album.name);
        albumList.add(album);
        singerList.add(singer);
        singerAlbumList.add(singerAlbum);
    }

    public List<Album> findAllAlbums() {
        return albumList;
    }

    public List<Album> findAlbumsBy(String name) {
        List<Album> albums = new ArrayList<Album>();
        for(Album album:albumList)
            if(album.name.equalsIgnoreCase(name))
                albums.add(album);
        return albums;
    }

    public List<Album> findAlbumsBy(Singer singer) {

        List<Album> albums = new ArrayList<Album>();
        for(SingerAlbum singerAlbum:singerAlbumList) {

            System.out.println("singeralbum singername:"+singerAlbum.singer.firstName+"albumname"+singerAlbum.album.name);
            if (singerAlbum.singer.equals(singer)) {
                albums.add(singerAlbum.album);
                System.out.println("albums.add" + singerAlbum.album.name);
            }
        }
        return albums;
    }

    public void createPlaylist(User user, Playlist playList) {
        UserPlaylist userPlaylist = new UserPlaylist(user,playList);

        if (userList.contains(user) == false)
            userList.add(user);

        playlistList.add(playList);
        userPlaylistList.add(userPlaylist);
    }

    public List<Playlist> findPlaylistBy(User user) {
        List<Playlist> playlists = new ArrayList<Playlist>();
        for(UserPlaylist userPlaylist:userPlaylistList)
            if(userPlaylist.user.equals(user))
                playlists.add(userPlaylist.playlist);
        return playlists;
    }

    public List<Playlist> findPlaylistBy(User user, String name) {
        List<Playlist> playlists = new ArrayList<Playlist>();
        for (UserPlaylist userPlaylist:userPlaylistList) {
            if (userPlaylist.user.equals(user)) {
                if (userPlaylist.playlist.name.equalsIgnoreCase(name)) {
                    playlists.add(userPlaylist.playlist);
                }
            }
        }
        return playlists;
    }

    public Playlist getPlaylistDetail(User user, String name) {
        for(UserPlaylist userPlaylist:userPlaylistList)
            if(userPlaylist.user.equals(user))
                if(userPlaylist.playlist.name.equalsIgnoreCase(name)) {
                    for(PlaylistSong playlistSong:playlistSongList){
                        if(playlistSong.playlist.equals(userPlaylist.playlist))
                            userPlaylist.playlist.songList.add(playlistSong.song);
                    }
                    return userPlaylist.playlist;
                }
        return null;
    }

    public void createSong(Singer singer, Album album, Song song) {
        songList.add(song);
        SingerSong singerSong = new SingerSong(singer,song);
        singerSongList.add(singerSong);
        SingerAlbum singerAlbum = new SingerAlbum(singer,album);
        singerAlbumList.add(singerAlbum);
        singerList.add(singer);
    }
    public Song findSongBy(String name) {
        for(Song song:songList)
            if(song.name.equalsIgnoreCase(name))
                return song;
        return null;
    }

    public Song getSongDetail(Singer singer, String name) {
        for(SingerSong singerSong:singerSongList)
            if(singerSong.singer.equals(singer))
                if(singerSong.song.name.equalsIgnoreCase(name))
                    return singerSong.song;
        return null;
    }
    public PlaylistSong createPlaylistSong(Playlist playlist,Song song){
        PlaylistSong playlistSong  =new PlaylistSong(playlist,song);
        if (!playlistList.contains(playlist))
            this.playlistList.add(playlist);

        this.songList.add(song);
        this.playlistSongList.add(playlistSong);
        return playlistSong;
    }
    public List<Song> findSongBy(Playlist playlist){
        List<Song> songList = new ArrayList<Song>();
        for (PlaylistSong playlistSong:playlistSongList){
            if(playlistSong.playlist.equals(playlist))
                songList.add(playlistSong.song);
        }
        return songList;
    }
}

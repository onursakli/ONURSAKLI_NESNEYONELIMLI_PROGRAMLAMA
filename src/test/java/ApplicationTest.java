import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {
    public Application application = new Application();
    @Test
    public void testRegisterUser() {
        User user = application.registerUser("onursakli", "123456");

        assertTrue(application.userList.contains(user) && user.userName.equalsIgnoreCase("onursakli"));
    }
    @Test
    public void testFindAllUsers() {
        testRegisterUser();
        testRegisterUser();

        assertTrue(application.findAllUsers().size() == 2 && application.findAllUsers().get(0).userName.equalsIgnoreCase("onursakli"));
    }
    @Test
    public void testCreateSinger() {
        Singer singer = new Singer("Hadise","ACIKGOZ");
        application.createSinger(singer);

        assertTrue(application.findSingersBy("Hadise").contains(singer));
    }
    @Test
    public void testFindAllSinger() {
        testCreateSinger();

        assertTrue(application.findAllSinger().size() == 1);
    }
    @Test
    public void testFindSingersBy() {
        testCreateSinger();
        Singer singer = new Singer("Hadise","KAPALIGOZ");
        application.createSinger(singer);

        assertTrue(application.findSingersBy("HADise").size() == 2);
    }
    @Test
    public void testCreateAlbum() {
        Singer singer = new Singer("Ramiz","Karaeski");
        Album album = new Album("GECELER OLSUN");
        application.createAlbum(singer,album);

        assertTrue(application.findAlbumsBy(singer).contains(album));
    }
    @Test
    public void testFindAlbumsBy() {
        testCreateAlbum();

        assertTrue(application.findAlbumsBy("geceler OLSUN").size() == 1 );
    }
    @Test
    public void testFindAlbumsBySinger() {
        testCreateAlbum();
        Singer singer = application.findSingersBy("Ramiz").get(0);
        Album album = application.findAlbumsBy("geceler olsun").get(0);

        assertTrue( application.findAlbumsBy(singer).contains(album));
    }
    @Test
    public void testCreatePlaylist() {
        User user = new User("onursakli","123456");
        Playlist playlist = new Playlist("HAREKETLI LISTEM");
        application.createPlaylist(user,playlist);

        assertTrue(application.findPlaylistBy(user).contains(playlist));
    }
    @Test
    public void testFindPlaylistByUser() {
        testCreatePlaylist();
        User user = application.userList.get(0);
        for (User user1:application.userList)
            System.out.println(user1.userName +" -");
        Playlist playlist = application.findPlaylistBy(user).get(0);

        assertTrue(application.findPlaylistBy(user).contains(playlist));
    }
    @Test
    public void testFindPlaylistBy() {
        User user = new User("onursakli","123456");
        Playlist playlist = new Playlist("HAREKETLI LISTEM");
        application.createPlaylist(user,playlist);
        application.createPlaylist(user,playlist);
        application.createPlaylist(user,playlist);

        assertTrue(application.findPlaylistBy(user,"HAREKETLI LISTEM").size() == 3);
    }
    @Test
    public void testGetPlaylistDetail() {
        testCreatePlaylist();
        User user = application.userList.get(0);
        Playlist playlist = application.findPlaylistBy(user,"HAREKETLI LISTEM").get(0);
        Song song = new Song("SIR DUKE",new Album("ALBUM 1"),"3.20","https://open.spotify.com/track/1VsQdMNLHs9gVjvlpgfxL4");
        Song song2 = new Song("DO IT RIGHT",new Album("ALBUM 2"),"3.20","https://open.spotify.com/track/767AsV1cQwmOU4PJG96MzO");
        application.createPlaylistSong(playlist,song);
        application.createPlaylistSong(playlist,song2);

        Playlist playlistDetails = application.getPlaylistDetail(user,"HAREKETLI LISTEM");
        assertTrue(playlistDetails.songList.get(0).name.equalsIgnoreCase("SIR DUKE") &&
                playlistDetails.songList.get(0).url.equalsIgnoreCase("https://open.spotify.com/track/1VsQdMNLHs9gVjvlpgfxL4") &&
                playlistDetails.songList.get(1).name.equalsIgnoreCase("DO IT RIGHT") &&
                playlistDetails.songList.get(1).url.equalsIgnoreCase("https://open.spotify.com/track/767AsV1cQwmOU4PJG96MzO"));
    }
    @Test
    public void testCreateSong() {
        Singer singer = new Singer("Emrah","KARADUMAN");
        Album album = new Album("Toz Duman");
        Song song = new Song("Cevapsız çınlama",album,"4.54","https://open.spotify.com/track/2W6XpjOKbmZ1NNVNtwBr5b");
        application.createSong(singer,album,song);

        assertTrue(application.findSongBy(song.name).name.equalsIgnoreCase(song.name));
    }
    @Test
    public void testGetSongDetail() {
        testCreateSong();
        Song song = application.getSongDetail(application.singerList.get(0),"Cevapsız çınlama");

        assertTrue(song.name.equalsIgnoreCase("Cevapsız çınlama") &&
                song.url.equalsIgnoreCase("https://open.spotify.com/track/2W6XpjOKbmZ1NNVNtwBr5b") &&
                song.album.name.equalsIgnoreCase("Toz Duman") &&
                song.duration.equalsIgnoreCase("4.54"));
    }
    @Test
    public void testCreatePlaylistSong(){
        Playlist playlist = new Playlist("SLOW MUZIKLERIM");
        Song song = new Song("Castle on the Hill",new Album("Yolun Acik olsun"),"5.55","https://open.spotify.com/track/6PCUP3dWmTjcTtXY02oFdT");
        Song song2 = new Song("The Cure",new Album("Yolun Yol degil"),"2.21","https://open.spotify.com/track/51PIvodunv6NmX5250zxAh");
        Song song3 = new Song("Green Light",new Album("Zarra Larison"),"3.44","https://open.spotify.com/track/1WC5UN1U79gJlxGLYDGIO7");

        application.createPlaylistSong(playlist,song);
        application.createPlaylistSong(playlist,song2);
        application.createPlaylistSong(playlist,song3);

        assertTrue(application.playlistList.size() == 1 && application.findSongBy(playlist).size() == 3);
    }
}
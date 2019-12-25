//Inner class is used to encapsulate the inner (private) class from the outside world. At the same time keeping the class clean. 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Playlist {

    private String PlaylistName;
    private List<Song> songList;
    //inner class
    private class Song
    {
        private String songName;
        private String composer;

        public Song(String songName,String composer) {
            this.songName = songName;
            this.composer = composer;
        }

        @Override
        public String toString() {
            return "Playing [" +
                    "songName='" + songName + '\'' +
                    ", composer='" + composer + '\'' +
                    ']';
        }
    }//inner class

    public void addSongsToPaylist(String songName, String composer)
    {
        if(this.songList == null)
            this.songList=new ArrayList<Song>();
        this.songList.add(new Song(songName,composer));
    } //addSongsToPaylist
    public void playAll()
    {
        Iterator iterator =this.songList.iterator();
        while(iterator.hasNext())
        {
            System.out.println(""+iterator.next().toString());
        }
    }//playSongs

    public void setPlaylistName(String playlistName) {
        PlaylistName = playlistName;
    }
}


public class InnerClassDemo {

    public static void main(String[] args) {
        Playlist playlist=new Playlist();
        playlist.setPlaylistName("Hindi Song");
        playlist.addSongsToPaylist("Happy Song","");
        playlist.addSongsToPaylist("Baby Shark","Jony");

        playlist.playAll();

    }//main
}



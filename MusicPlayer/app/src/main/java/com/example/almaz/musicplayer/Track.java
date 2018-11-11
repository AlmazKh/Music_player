package com.example.almaz.musicplayer;

/**
 * Created by Almaz on 11.11.2018.
 */

public class Track {
    private int id;
    private String trackName;
    private String singerName;

    public Track(int id, String trackName, String singerName) {
        this.id = id;
        this.trackName = trackName;
        this.singerName = singerName;
    }

    public int getId() {
        return id;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getSingerName() {
        return singerName;
    }
}


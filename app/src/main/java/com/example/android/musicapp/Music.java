package com.example.android.musicapp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Music {

    private String title;
    private String artist;

    public Music(String songArtist, String songTitle) {
        artist = songArtist;
        title = songTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }


}

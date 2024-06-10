package com.example.finalterm;

public class Music {
    private String title;
    private String artist;
    private int imageResource;
    private int musicResource;

    public Music(String title, String artist, int imageResource, int musicResource) {
        this.title = title;
        this.artist = artist;
        this.imageResource = imageResource;
        this.musicResource = musicResource;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getImageResource() {
        return imageResource;
    }

    public int getMusicResource() {
        return musicResource;
    }
}

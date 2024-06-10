package com.example.finalterm;

public class Music {
    private String title; // 음악 제목
    private String artist; // 음악 아티스트
    private int imageResource; // 앨범 이미지 리소스 ID
    private int musicResource; // 음악 리소스 ID

    public Music(String title, String artist, int imageResource, int musicResource) {
        this.title = title; // 음악 제목 초기화
        this.artist = artist; // 아티스트 초기화
        this.imageResource = imageResource; // 앨범 이미지 리소스 ID 초기화
        this.musicResource = musicResource; // 음악 리소스 ID 초기화
    }

    public String getTitle() {
        return title; // 음악 제목 반환
    }

    public String getArtist() {
        return artist; // 음악 아티스트 반환
    }

    public int getImageResource() {
        return imageResource; // 앨범 이미지 리소스 ID 반환
    }

    public int getMusicResource() {
        return musicResource; // 음악 리소스 ID 반환
    }
}

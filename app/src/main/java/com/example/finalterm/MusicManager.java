package com.example.finalterm;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MusicManager {
    private static MusicManager instance;
    private List<Music> allMusics = new ArrayList<>(); // 모든 음악을 저장할 리스트
    private Context context;
    private MediaPlayer mediaPlayer;
    private Music currentSelectedMusic; // 현재 선택된 노래를 저장할 변수
    private List<Music> playbackList = new ArrayList<>(); // 재생 목록

    private MusicManager(Context context) {
        this.context = context;
        initializeMusics(); // 음악 데이터를 초기화
    }

    public static MusicManager getInstance(Context context) {
        if (instance == null) {
            instance = new MusicManager(context.getApplicationContext());
        }
        return instance;
    }

    // 음악 데이터를 초기화하는 메서드
    private void initializeMusics() {
        // 음악 데이터 추가
        addMusic(new Music("Black Space", "Taylor Swift", R.drawable.lp_taylor_1989, R.raw.ms_taylor_blackspace));
        addMusic(new Music("Lover", "Taylor Swift", R.drawable.lp_taylor_lover, R.raw.ms_taylor_lover));
        addMusic(new Music("The Lucky One", "Taylor Swift", R.drawable.lp_taylor_red, R.raw.ms_taylor_theluckyone));
        addMusic(new Music("The Way I Loved You", "Taylor Swift", R.drawable.lp_taylor_fearless, R.raw.ms_taylor_thewayiloved));
        addMusic(new Music("Hi", "박혜경", R.drawable.lp_phk_saraphim, R.raw.ms_phk_hi));
        addMusic(new Music("고백", "박혜경", R.drawable.lp_phk_01, R.raw.ms_phk_confession));
        addMusic(new Music("우린 1년을 만났죠", "박혜경", R.drawable.lp_phk_saraphim, R.raw.ms_phk_wemetanyear));
        addMusic(new Music("오렌지 마말레이드", "자우림", R.drawable.lp_jaurim_wonderland, R.raw.ms_jaurim_orange));
        addMusic(new Music("미안해 널 좋아해", "자우림", R.drawable.lp_jaurim_apa, R.raw.ms_jaurim_sorryiloveyou));
        addMusic(new Music("LoveLoveLove", "나상현씨밴드", R.drawable.lp_nsh_lovelovelove, R.raw.ms_nsh_lovelovelove));
        addMusic(new Music("축제", "나상현씨밴드", R.drawable.lp_nsh_festival, R.raw.ms_nsh_festival));
        addMusic(new Music("찬란", "나상현씨밴드", R.drawable.lp_nsh_brilliant, R.raw.ms_nsh_brilliant));
        addMusic(new Music("Summer", "The Volunteers", R.drawable.lp_thevolun_thevolun, R.raw.ms_thevolun_summer));
        addMusic(new Music("New Plant", "The Volunteers", R.drawable.lp_thevolun_newplant, R.raw.ms_thevolun_newplant));
        addMusic(new Music("Our Love is great", "백예린", R.drawable.lp_yr_ourlove, R.raw.ms_yr_nightfly));
        addMusic(new Music("Bye Bye My Blue", "백예린", R.drawable.lp_yr_byebye, R.raw.ms_yr_byebye));
        addMusic(new Music("Bunny", "백예린", R.drawable.lp_yr_everyletter, R.raw.ms_yr_bunny));
        addMusic(new Music("Antifreeze", "백예린", R.drawable.lp_yr_gift, R.raw.ms_yr_antifreeze));
        addMusic(new Music("I'll be your family!", "백예린", R.drawable.lp_yr_tellus, R.raw.ms_yr_illbeyourfamilly));
        addMusic(new Music("Oh, My Love", "S.E.S", R.drawable.lp_ses_1, R.raw.ms_ses_ohmylove));
        addMusic(new Music("애인찾기", "SES", R.drawable.lp_ses_2, R.raw.ms_ses_findlover));
        addMusic(new Music("Just A Feeling", "S.E.S", R.drawable.lp_ses_choosemylife, R.raw.ms_ses_justafeeling));
        addMusic(new Music("Like A Shooting Star", "S.E.S", R.drawable.lp_ses_reachout, R.raw.ms_ses_likeashootingstar));
        addMusic(new Music("Sweet Dream", "장나라", R.drawable.lp_nara_sweetdream, R.raw.ms_nara_sweetdream));
        addMusic(new Music("고백하기 좋은 날", "장나라", R.drawable.lp_nara_loveschool, R.raw.ms_nara_agoodday));
        addMusic(new Music("나도 여자랍니다", "장나라", R.drawable.lp_nara_third, R.raw.ms_nara_imawoman));
    }

    // 음악 데이터를 리스트에 추가하는 메서드
    private void addMusic(Music music) {
        allMusics.add(music);
    }

    // 음악을 재생하는 메서드
    public void playMusic(int musicResource) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, musicResource);
        mediaPlayer.start();
    }

    // 음악 재생을 멈추는 메서드
    public void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public int getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    public int getCurrentPosition() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int position) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(position);
        }
    }

    private Set<Music> favoriteMusics = new HashSet<>(); // 보관함에 있는 음악을 저장할 집합

    // 즐겨찾기 음악을 추가하는 메서드
    public void addFavoriteMusic(Music music) {
        favoriteMusics.add(music);
    }

    // 즐겨찾기 음악을 제거하는 메서드
    public void removeFavoriteMusic(Music music) {
        favoriteMusics.remove(music);
    }

    // 특정 음악이 즐겨찾기에 있는지 확인하는 메서드
    public boolean isFavoriteMusic(Music music) {
        return favoriteMusics.contains(music);
    }

    // 즐겨찾기 음악 목록을 반환하는 메서드
    public Set<Music> getFavoriteMusics() {
        return favoriteMusics;
    }

    // 현재 선택된 음악을 반환하는 메서드
    public Music getCurrentSelectedMusic() {
        return currentSelectedMusic;
    }

    // 현재 선택된 음악을 설정하는 메서드
    public void setCurrentSelectedMusic(Music music) {
        currentSelectedMusic = music;
    }

    // 재생 목록에 음악을 추가하는 메서드
    public void addToPlaybackList(Music music) {
        playbackList.add(music);
    }

    // 재생 목록을 반환하는 메서드
    public List<Music> getPlaybackList() {
        return playbackList;
    }

    // 음악 제목으로 음악을 검색하는 메서드
    public List<Music> searchMusicByTitle(String query) {
        List<Music> searchResults = new ArrayList<>();
        for (Music music : allMusics) {
            if (music.getTitle().toLowerCase().contains(query.toLowerCase())) {
                searchResults.add(music);
            }
        }
        return searchResults;
    }
}

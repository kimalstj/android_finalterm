package com.example.finalterm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageButton buttonHeart, buttonState, buttonNext, buttonPrevious, buttonList;
    private ImageView lpImage, tonearmImage;
    private TextView titleTextView, singerTextView;

    private boolean isPlaying = false;
    private boolean isHearted = false;
    private ArrayList<Track> playlist = new ArrayList<>();
    private int currentTrackIndex = 0;

    private class Track {
        String title;
        String singer;
        int resourceId;

        Track(String title, String singer, int resourceId) {
            this.title = title;
            this.singer = singer;
            this.resourceId = resourceId;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_layout);

        buttonHeart = findViewById(R.id.button_heart);
        buttonState = findViewById(R.id.button_state);
        buttonNext = findViewById(R.id.button_next);
        buttonPrevious = findViewById(R.id.button_previous);
        buttonList = findViewById(R.id.button_list);
        lpImage = findViewById(R.id.lp);
        tonearmImage = findViewById(R.id.tonearm);
        titleTextView = findViewById(R.id.title);
        singerTextView = findViewById(R.id.singer);

        // 현재 트랙 설정
        setTrack(playlist.get(currentTrackIndex));

        // 버튼 이벤트 설정
        buttonHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isHearted = !isHearted;
                buttonHeart.setImageResource(isHearted ? R.drawable.ic_fullheart : R.drawable.ic_emptyheart);
                // 음악 보관함에 추가하는 코드 여기에 작성
            }
        });

        buttonState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    pauseMusic();
                } else {
                    playMusic();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTrackIndex < playlist.size() - 1) {
                    currentTrackIndex++;
                    setTrack(playlist.get(currentTrackIndex));
                    playMusic();
                }
            }
        });

        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentTrackIndex > 0) {
                    currentTrackIndex--;
                    setTrack(playlist.get(currentTrackIndex));
                    playMusic();
                }
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 재생 목록 화면으로 전환하는 코드 여기에 작성
            }
        });
    }

    private void setTrack(Track track) {
        titleTextView.setText(track.title);
        singerTextView.setText(track.singer);
        mediaPlayer = MediaPlayer.create(this, track.resourceId);
    }

    private void playMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            isPlaying = true;
            buttonState.setImageResource(R.drawable.ic_pause);
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlaying = false;
            buttonState.setImageResource(R.drawable.ic_play);
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}

package com.example.finalterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private MusicManager musicManager;
    private boolean isPlaying = false;
    private ImageButton buttonCover;
    private Button buttonTitle;
    private LinearLayout playbackListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicManager = MusicManager.getInstance(this);

        buttonTitle = findViewById(R.id.button_title);
        ImageButton buttonHome = findViewById(R.id.button_home);
        ImageButton buttonFind = findViewById(R.id.button_find);
        buttonCover = findViewById(R.id.button_cover);
        ImageButton buttonState = findViewById(R.id.button_state);
        ImageButton buttonNext = findViewById(R.id.button_next);
        playbackListLayout = findViewById(R.id.lists);

        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLayout(R.layout.find_layout);
            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLayout(R.layout.my_layout);
            }
        });

        buttonCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLayout(R.layout.music_layout);
            }
        });

        buttonState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    musicManager.stopMusic();
                    buttonState.setImageResource(R.drawable.ic_play);
                } else {
                    Music currentMusic = musicManager.getCurrentSelectedMusic();
                    if (currentMusic != null) {
                        musicManager.playMusic(currentMusic.getMusicResource());
                        buttonState.setImageResource(R.drawable.ic_pause);
                        buttonCover.setImageResource(currentMusic.getImageResource());
                        buttonTitle.setText(currentMusic.getTitle());

                        // 재생 목록에 음악 추가
                        musicManager.addToPlaybackList(currentMusic);
                        updatePlaybackList();
                    }
                }
                isPlaying = !isPlaying;
            }
        });

        loadLayout(R.layout.my_layout);
    }

    private void loadLayout(int layoutResId) {
        View musicBar = findViewById(R.id.muiscBar);

        if (layoutResId == R.layout.music_layout) {
            musicBar.setVisibility(View.GONE);
        } else {
            musicBar.setVisibility(View.VISIBLE);
        }

        getLayoutInflater().inflate(layoutResId, findViewById(R.id.container), true);
    }

    private void updatePlaybackList() {
        playbackListLayout.removeAllViews();
        for (Music music : musicManager.getPlaybackList()) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.playback_item, playbackListLayout, false);

            TextView titleView = itemView.findViewById(R.id.music_title);
            TextView artistView = itemView.findViewById(R.id.music_artist);
            ImageView coverView = itemView.findViewById(R.id.music_cover);

            titleView.setText(music.getTitle());
            artistView.setText(music.getArtist());
            coverView.setImageResource(music.getImageResource());

            playbackListLayout.addView(itemView);
        }
    }

    public void updateCoverAndTitle(Music music) {
        if (buttonCover != null && buttonTitle != null) {
            buttonCover.setImageResource(music.getImageResource());
            buttonTitle.setText(music.getTitle());
        }
    }
}

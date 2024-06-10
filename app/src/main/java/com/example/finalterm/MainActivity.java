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

        // MusicManager 인스턴스 초기화
        musicManager = MusicManager.getInstance(this);

        // UI 요소 초기화
        buttonTitle = findViewById(R.id.button_title);
        ImageButton buttonHome = findViewById(R.id.button_home);
        ImageButton buttonFind = findViewById(R.id.button_find);
        buttonCover = findViewById(R.id.button_cover);
        ImageButton buttonState = findViewById(R.id.button_state);
        ImageButton buttonNext = findViewById(R.id.button_next);
        playbackListLayout = findViewById(R.id.lists);

        // Find 버튼 클릭 시 find_layout 로드
        buttonFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLayout(R.layout.find_layout);
            }
        });

        // Home 버튼 클릭 시 my_layout 로드
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadLayout(R.layout.my_layout);
            }
        });

        // Cover 버튼 클릭 시 music_layout 로드
        buttonCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadLayout(R.layout.music_layout);
            }
        });

        // 재생/일시정지 버튼 클릭 시 동작
        buttonState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    // 음악 일시정지
                    musicManager.stopMusic();
                    buttonState.setImageResource(R.drawable.ic_play);
                } else {
                    // 음악 재생
                    Music currentMusic = musicManager.getCurrentSelectedMusic();
                    if (currentMusic != null) {
                        musicManager.playMusic(currentMusic.getMusicResource());
                        buttonState.setImageResource(R.drawable.ic_pause);
                        buttonCover.setImageResource(currentMusic.getImageResource());
                        buttonTitle.setText(currentMusic.getTitle());
                        // 재생 목록에 현재 음악 추가
                        musicManager.addToPlaybackList(currentMusic);
                        updatePlaybackList();
                    }
                }
                isPlaying = !isPlaying;
            }
        });

        // 초기 레이아웃 로드
        loadLayout(R.layout.my_layout);
    }

    // 주어진 레이아웃을 로드하는 메서드
    private void loadLayout(int layoutResId) {
        View musicBar = findViewById(R.id.muiscBar);

        // 레이아웃에 따라 musicBar 표시 여부 결정
        if (layoutResId == R.layout.music_layout) {
            musicBar.setVisibility(View.GONE);
        } else {
            musicBar.setVisibility(View.VISIBLE);
        }

        getLayoutInflater().inflate(layoutResId, findViewById(R.id.container), true);
    }

    // 재생 목록을 업데이트하는 메서드
    private void updatePlaybackList() {
        playbackListLayout.removeAllViews();
        for (Music music : musicManager.getPlaybackList()) {
            View itemView = LayoutInflater.from(this).inflate(R.layout.playback_item, playbackListLayout, false);

            // 재생 목록 항목의 UI 요소 초기화
            TextView titleView = itemView.findViewById(R.id.music_title);
            TextView artistView = itemView.findViewById(R.id.music_artist);
            ImageView coverView = itemView.findViewById(R.id.music_cover);

            // UI 요소에 음악 정보 설정
            titleView.setText(music.getTitle());
            artistView.setText(music.getArtist());
            coverView.setImageResource(music.getImageResource());

            playbackListLayout.addView(itemView);
        }
    }

    // 현재 커버 이미지와 제목을 업데이트하는 메서드
    public void updateCoverAndTitle(Music music) {
        if (buttonCover != null && buttonTitle != null) {
            buttonCover.setImageResource(music.getImageResource());
            buttonTitle.setText(music.getTitle());
        }
    }
}

package com.example.finalterm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    private MusicManager musicManager; // MusicManager 인스턴스
    private LinearLayout leftColumn, rightColumn; // 왼쪽 및 오른쪽 열 레이아웃

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        // MusicManager 인스턴스 초기화
        musicManager = MusicManager.getInstance(this);

        // 레이아웃 요소 초기화
        leftColumn = findViewById(R.id.leftColumn);
        rightColumn = findViewById(R.id.rightColumn);

        // 즐겨찾기 음악 표시
        displayFavoriteMusic();
    }

    // 즐겨찾기 음악을 레이아웃에 표시하는 메서드
    private void displayFavoriteMusic() {
        // 즐겨찾기 음악 목록을 가져와서 리스트에 저장
        List<Music> favoriteMusics = new ArrayList<>(musicManager.getFavoriteMusics());
        for (int i = 0; i < favoriteMusics.size(); i++) {
            Music music = favoriteMusics.get(i);
            // 새로운 ImageView 생성
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            imageView.setImageResource(music.getImageResource());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(25, 25, 25, 25);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭 시 음악 재생
                    musicManager.playMusic(music.getMusicResource());
                }
            });

            // 왼쪽과 오른쪽 열에 번갈아가며 이미지뷰 추가
            if (i % 2 == 0) {
                leftColumn.addView(imageView);
            } else {
                rightColumn.addView(imageView);
            }
        }
    }
}

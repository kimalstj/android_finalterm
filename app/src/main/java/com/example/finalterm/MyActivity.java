package com.example.finalterm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {
    private MusicManager musicManager;
    private LinearLayout leftColumn, rightColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        musicManager = MusicManager.getInstance(this);

        leftColumn = findViewById(R.id.leftColumn);
        rightColumn = findViewById(R.id.rightColumn);

        displayFavoriteMusic();
    }

    private void displayFavoriteMusic() {
        List<Music> favoriteMusics = new ArrayList<>(musicManager.getFavoriteMusics());
        for (int i = 0; i < favoriteMusics.size(); i++) {
            Music music = favoriteMusics.get(i);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            imageView.setImageResource(music.getImageResource());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(25, 25, 25, 25);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 음악 재생
                    musicManager.playMusic(music.getMusicResource());
                }
            });

            // 왼쪽과 오른쪽 열에 번갈아가며 추가
            if (i % 2 == 0) {
                leftColumn.addView(imageView);
            } else {
                rightColumn.addView(imageView);
            }
        }
    }
}

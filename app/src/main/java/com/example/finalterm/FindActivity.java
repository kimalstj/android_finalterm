package com.example.finalterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class FindActivity extends AppCompatActivity {
    private MusicManager musicManager; // MusicManager 인스턴스
    private EditText findView; // 검색어를 입력하는 EditText
    private LinearLayout searchResultsContainer; // 검색 결과를 표시하는 컨테이너

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_layout);

        // View 초기화
        findView = findViewById(R.id.findView);
        searchResultsContainer = findViewById(R.id.search_result);
        ImageButton searchButton = findViewById(R.id.search_button);

        // MusicManager 초기화
        musicManager = MusicManager.getInstance(this);

        // 검색 버튼 클릭 리스너 설정
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = findView.getText().toString();
                performSearch(searchText);
            }
        });
    }

    // 검색 수행 메서드
    private void performSearch(String query) {
        searchResultsContainer.removeAllViews();  // 이전 검색 결과 제거

        // 제목으로 음악 검색
        List<Music> results = musicManager.searchMusicByTitle(query);
        if (results.isEmpty()) {
            Toast.makeText(this, "음악을 찾을 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            for (Music music : results) {
                // 검색 결과 항목의 뷰를 생성
                View resultView = LayoutInflater.from(this).inflate(R.layout.search_result_item, searchResultsContainer, false);

                ImageView albumCover = resultView.findViewById(R.id.album_cover);
                TextView titleTextView = resultView.findViewById(R.id.music_title);
                TextView artistTextView = resultView.findViewById(R.id.music_artist);

                // 음악 세부 사항 설정
                albumCover.setImageResource(music.getImageResource());
                titleTextView.setText(music.getTitle());
                artistTextView.setText(music.getArtist());

                // 클릭 시 음악 재생 설정
                resultView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        musicManager.playMusic(music.getMusicResource());
                    }
                });

                // 검색 결과 컨테이너에 결과 뷰 추가
                searchResultsContainer.addView(resultView);
            }
        }
    }
}

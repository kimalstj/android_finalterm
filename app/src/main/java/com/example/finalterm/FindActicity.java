package com.example.finalterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.List;

public class FindActicity extends Fragment {
    private MusicManager musicManager;
    private EditText findView;
    private LinearLayout searchResultsContainer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.find_layout, container, false);

        musicManager = MusicManager.getInstance(getContext());
        findView = view.findViewById(R.id.findView);
        searchResultsContainer = view.findViewById(R.id.search_result);

        Button searchButton = view.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = findView.getText().toString();
                if (!query.isEmpty()) {
                    List<Music> results = musicManager.searchMusicByTitle(query);
                    displaySearchResults(results);
                } else {
                    Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void displaySearchResults(List<Music> results) {
        searchResultsContainer.removeAllViews();
        for (Music music : results) {
            View resultView = LayoutInflater.from(getContext()).inflate(R.layout.search_result_item, searchResultsContainer, false);
            TextView titleView = resultView.findViewById(R.id.music_title);
            ImageView coverView = resultView.findViewById(R.id.music_cover);

            titleView.setText(music.getTitle());
            coverView.setImageResource(music.getImageResource());

            resultView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    musicManager.setCurrentSelectedMusic(music);
                    MainActivity mainActivity = (MainActivity) getActivity();
                    if (mainActivity != null) {
                        mainActivity.updateCoverAndTitle(music);
                    }
                }
            });

            searchResultsContainer.addView(resultView);
        }
    }
}

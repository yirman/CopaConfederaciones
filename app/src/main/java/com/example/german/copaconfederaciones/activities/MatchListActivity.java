package com.example.german.copaconfederaciones.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.retrofit.ServiceGenerator;
import com.example.german.copaconfederaciones.utils.Constants;
import com.example.german.copaconfederaciones.utils.PreferenceManager;

public class MatchListActivity extends AppCompatActivity {

    private RecyclerView matchesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        String accessToken = PreferenceManager
                .get(this)
                .getString(Constants.ACCESS_TOKEN, "");


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        this.matchesList = findViewById(R.id.matches_recycler_view);
//        this.matchesList.setLayoutManager(linearLayoutManager);

    }
}

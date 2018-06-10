package com.example.german.copaconfederaciones.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.adapters.MatchAdapter;
import com.example.german.copaconfederaciones.models.get.MatchData;
import com.example.german.copaconfederaciones.retrofit.ServiceGenerator;
import com.example.german.copaconfederaciones.utils.Constants;
import com.example.german.copaconfederaciones.utils.PreferenceManager;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchListActivity extends AppCompatActivity {

    private RecyclerView matchesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        String accessToken = PreferenceManager
                .get(this)
                .getString(Constants.ACCESS_TOKEN, "");

        Call<MatchData> matchesService = ServiceGenerator.getMatches(accessToken);

        matchesService.enqueue(new Callback<MatchData>() {
            @Override
            public void onResponse(Call<MatchData> call, Response<MatchData> response) {

                final Realm realm = Realm.getDefaultInstance();
                final MatchData matchData = response.body();
                matchData.setId(1);

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(matchData);
                    }
                });

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MatchListActivity.this);
                matchesList = findViewById(R.id.matches_recycler_view);
                matchesList.setLayoutManager(linearLayoutManager);

                MatchAdapter adapter = new MatchAdapter(matchData.getData().getItems());
                matchesList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MatchData> call, Throwable t) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}

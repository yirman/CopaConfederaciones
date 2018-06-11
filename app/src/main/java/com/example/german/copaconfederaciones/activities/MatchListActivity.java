package com.example.german.copaconfederaciones.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

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

    private Realm realm;
    private MatchData matchData;
    private RecyclerView matchesList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        this.realm = Realm.getDefaultInstance();
        this.matchesList = findViewById(R.id.matches_recycler_view);
        this.progressBar = findViewById(R.id.progress_bar);

        this.realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                matchData = realm.where(MatchData.class).findFirst();
            }
        });

        if( this.matchData == null ){

            String accessToken = PreferenceManager
                    .get(this)
                    .getString(Constants.ACCESS_TOKEN, "");

            Call<MatchData> matchesService = ServiceGenerator.getMatches(accessToken);

            matchesService.enqueue(new Callback<MatchData>() {
                @Override
                public void onResponse(Call<MatchData> call, Response<MatchData> response) {

                    matchData = response.body();
                    matchData.setId(1);

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.insertOrUpdate(matchData);
                        }
                    });

                    loadMatchData(matchData);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<MatchData> call, Throwable t) {

                }
            });
        }
        else{
            this.loadMatchData(matchData);
            progressBar.setVisibility(View.GONE);
        }


    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void loadMatchData(MatchData matchData){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MatchListActivity.this);
        matchesList.setLayoutManager(linearLayoutManager);

        MatchAdapter adapter = new MatchAdapter(matchData.getData().getItems());
        matchesList.setAdapter(adapter);
        matchesList.addItemDecoration(
                new MatchAdapter.Decoration(
                        0,
                        20,
                        20,
                        20,
                        R.color.Crimson,
                        R.color.Ash)
        );
        matchesList.setVisibility(View.VISIBLE);
    }
}

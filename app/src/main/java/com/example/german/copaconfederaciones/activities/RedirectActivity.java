package com.example.german.copaconfederaciones.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.utils.Constants;
import com.example.german.copaconfederaciones.utils.PreferenceManager;

public class RedirectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect);

        String accessToken = PreferenceManager.get(this)
                .getString(Constants.ACCESS_TOKEN, "");

        if(accessToken.isEmpty())
            startActivity(new Intent(this, LoginActivity.class));
        else
            startActivity(new Intent(this, MatchListActivity.class));

        this.finish();
    }
}

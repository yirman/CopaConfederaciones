package com.example.german.copaconfederaciones;

import android.app.Application;
import android.os.Build;

import com.squareup.leakcanary.LeakCanary;

import io.realm.*;

/**
 * Created by German on 9/6/2018.
 */

public class CopaConfederaciones extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(getResources().getString(R.string.app_name))
                .schemaVersion(io.realm.BuildConfig.VERSION_CODE + 1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
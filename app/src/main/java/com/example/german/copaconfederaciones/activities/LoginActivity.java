package com.example.german.copaconfederaciones.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.models.post.App;
import com.example.german.copaconfederaciones.models.post.Configuration;
import com.example.german.copaconfederaciones.models.post.Data;
import com.example.german.copaconfederaciones.models.post.Device;
import com.example.german.copaconfederaciones.models.post.PostResponse;
import com.example.german.copaconfederaciones.models.post.Profile;
import com.example.german.copaconfederaciones.models.post.User;
import com.example.german.copaconfederaciones.retrofit.ServiceGenerator;
import com.example.german.copaconfederaciones.utils.Constants;
import com.example.german.copaconfederaciones.utils.PreferenceManager;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.READ_PHONE_STATE},
//                        Constants.MY_PERMISSION_READ_PHONE_STATE);
//            }


//        Dexter.withActivity(this)
//                .withPermission(Manifest.permission.READ_PHONE_STATE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        Log.e("TAG", "puto");
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//
//                    }
//                }).check();

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {

                List<PermissionGrantedResponse> grantedPermissionResponses = report.getGrantedPermissionResponses();

                for(PermissionGrantedResponse p: grantedPermissionResponses){
                    Log.e(TAG, p.toString());
                }

            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

            }
        }).check();

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                    Configuration userConfiguration = createUserConfiguration();
                    Log.e(TAG, Configuration.TAG + new Gson().toJson(userConfiguration));

                    Call<PostResponse> login = ServiceGenerator.login(userConfiguration);

                    login.enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                            Log.e(TAG, String.valueOf(response.code()));

                            if(response.isSuccessful()){
                                Log.e(TAG, PostResponse.TAG + new Gson().toJson(response.body()));

                                Data data = response.body().getData();

                                String token = data.getTokenType() +
                                        " " +
                                        data.getAccessToken();

                                PreferenceManager.edit(LoginActivity.this)
                                        .putString(Constants.ACCESS_TOKEN, token)
                                        .commit();

                                Intent intent = new Intent(LoginActivity.this, MatchListActivity.class);
                                startActivity(intent);
                                LoginActivity.this.finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<PostResponse> call, Throwable t) {
                            Log.e(TAG, t.getMessage());
                        }
                    });
                }

            }
        });
    }

    private Configuration createUserConfiguration() {

        String language = Locale.getDefault().getLanguage();
        Profile profile = new Profile(language);

        User user = new User(profile);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        String deviceId = Constants.DEFAULT_PHONE_ID;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED)
            deviceId = telephonyManager.getDeviceId();

        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        String release = Build.VERSION.RELEASE;
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        String width = String.valueOf(size.x);
        String height = String.valueOf(size.y);
        String platform = Constants.PLATFORM;

        Device device = new Device(deviceId, manufacturer, release, width, height, model, platform);

        String versionName = Constants.FIXED_APP_VERSION;

        App app = new App(versionName);

        return new Configuration(user, device, app);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}



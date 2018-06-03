package com.example.german.copaconfederaciones.activities;

import android.Manifest;
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

import com.example.german.copaconfederaciones.BuildConfig;
import com.example.german.copaconfederaciones.R;
import com.example.german.copaconfederaciones.models.App;
import com.example.german.copaconfederaciones.models.Configuration;
import com.example.german.copaconfederaciones.models.Device;
import com.example.german.copaconfederaciones.models.PostResponse;
import com.example.german.copaconfederaciones.models.Profile;
import com.example.german.copaconfederaciones.models.User;
import com.example.german.copaconfederaciones.retrofit.Service;
import com.example.german.copaconfederaciones.utils.Constants;
import com.google.gson.Gson;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_DENIED){

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        Constants.MY_PERMISSION_READ_PHONE_STATE);
            }

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {

                    Configuration userConfiguration = createUserConfiguration();
                    Log.e(TAG, Configuration.TAG + new Gson().toJson(userConfiguration));


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    Service service = retrofit.create(Service.class);

                    Call<PostResponse> login = service.login(createUserConfiguration());

                    login.enqueue(new Callback<PostResponse>() {
                        @Override
                        public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                            Log.e(TAG, String.valueOf(response.code()));

                            if(response.isSuccessful()){
                                Log.e(TAG, PostResponse.TAG + new Gson().toJson(response.body()));
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

        String versionName = BuildConfig.VERSION_NAME;

        App app = new App(versionName);

        return new Configuration(user, device, app);
    }
}



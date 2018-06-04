package com.example.german.copaconfederaciones.retrofit;

import com.example.german.copaconfederaciones.models.post.Configuration;
import com.example.german.copaconfederaciones.models.post.PostResponse;
import com.example.german.copaconfederaciones.utils.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by German on 3/6/2018.
 */

public class ServiceGenerator {

    private static Retrofit retrofitBuilder(){

        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Call<PostResponse> login(Configuration configuration){
        return retrofitBuilder().create(Service.class).login(configuration);
    }

}

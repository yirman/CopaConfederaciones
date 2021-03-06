package com.example.german.copaconfederaciones.retrofit;


import com.example.german.copaconfederaciones.models.get.MatchData;
import com.example.german.copaconfederaciones.models.post.Configuration;
import com.example.german.copaconfederaciones.models.post.PostResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by German on 2/6/2018.
 */

public interface Service {

    @Headers({
            "Content-Type: application/json",
            "Authorization: Basic cHJ1ZWJhc2RldjpwcnVlYmFzZGV2U2VjcmV0"
    })
    @POST("api/1.0/auth/users/login/anonymous")
    Call<PostResponse> login(@Body Configuration configuration);

    @GET("api/1.0/sport/events")
    Call<MatchData> getMatches(@Header("Authorization") String token);

}

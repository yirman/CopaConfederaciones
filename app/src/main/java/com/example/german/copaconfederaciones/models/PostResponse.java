package com.example.german.copaconfederaciones.models;

/**
 * Created by German on 2/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostResponse {

    public static final String TAG = PostResponse.class.getSimpleName();

    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}

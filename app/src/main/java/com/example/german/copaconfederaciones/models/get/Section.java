package com.example.german.copaconfederaciones.models.get;

/**
 * Created by German on 3/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("title")
    @Expose
    private Title title;


    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

}

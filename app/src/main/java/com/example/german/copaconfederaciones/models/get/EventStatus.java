package com.example.german.copaconfederaciones.models.get;

/**
 * Created by German on 3/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventStatus {

    @SerializedName("name")
    @Expose
    private Name_ name;

    public Name_ getName() {
        return name;
    }

    public void setName(Name_ name) {
        this.name = name;
    }

}

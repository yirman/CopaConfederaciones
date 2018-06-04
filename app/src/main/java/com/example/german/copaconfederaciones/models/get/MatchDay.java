package com.example.german.copaconfederaciones.models.get;

/**
 * Created by German on 3/6/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MatchDay {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("phase")
    @Expose
    private Phase phase;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

}

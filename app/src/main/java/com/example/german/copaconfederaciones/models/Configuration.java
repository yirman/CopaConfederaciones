package com.example.german.copaconfederaciones.models;

/**
 * Created by German on 2/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Configuration {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("device")
    @Expose
    private Device device;

    @SerializedName("app")
    @Expose
    private App app;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

}

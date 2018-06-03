package com.example.german.copaconfederaciones.models.post;

/**
 * Created by German on 2/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Device {

    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("width")
    @Expose
    private String width;

    @SerializedName("heigth")
    @Expose
    private String heigth;

    @SerializedName("model")
    @Expose
    private String model;

    @SerializedName("platform")
    @Expose
    private String platform;

    public Device(String deviceId, String name, String version, String width, String heigth, String model, String platform) {
        this.deviceId = deviceId;
        this.name = name;
        this.version = version;
        this.width = width;
        this.heigth = heigth;
        this.model = model;
        this.platform = platform;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(String heigth) {
        this.heigth = heigth;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

}

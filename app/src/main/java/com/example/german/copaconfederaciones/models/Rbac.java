package com.example.german.copaconfederaciones.models;

/**
 * Created by German on 2/6/2018.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rbac {

    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("template")
    @Expose
    private String template;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

}

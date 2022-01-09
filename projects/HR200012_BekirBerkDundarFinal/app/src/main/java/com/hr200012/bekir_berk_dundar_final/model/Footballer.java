package com.hr200012.bekir_berk_dundar_final.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Footballer {
    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Team")
    @Expose
    public String team;

    @SerializedName("Avatar")
    @Expose
    public String avatar;

    @SerializedName("Description")
    @Expose
    public String description;

    @SerializedName("Cover")
    @Expose
    public String cover;
}
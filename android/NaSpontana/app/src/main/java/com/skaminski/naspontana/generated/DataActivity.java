package com.skaminski.naspontana.generated;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by skaminski on 30.11.2016.
 */

public class DataActivity{
    @SerializedName("userId")
    @Expose
    String userId;
    @SerializedName("id")
    @Expose
    long id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("start_date")
    @Expose
    String start_date;
    @SerializedName("publication_date")
    @Expose
    String publication_date;
    @SerializedName("publisher")
    @Expose
    boolean publisher;
    @SerializedName("category_id")
    @Expose
    int category_id;
}
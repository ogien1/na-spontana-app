package com.skaminski.naspontana.generated;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by skaminski on 13.12.2016.
 */

public class ActivityToCheck {

    @SerializedName("categoryId")
    String categoryId;
    @SerializedName("description")
    String description;
    @SerializedName("facebookId")
    String facebookId;
    @SerializedName("friends")
    List<String> friends;
    @SerializedName("minutesDiff")
    String minutesDiff;
    @SerializedName("name")
    String name;
    @SerializedName("startDate")
    String startDate;

    public ActivityToCheck() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getMinutesDiff() {
        return minutesDiff;
    }

    public void setMinutesDiff(String minutesDiff) {
        this.minutesDiff = minutesDiff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public ActivityToCheck(String categoryId, String description, String facebookId, List<String> friends, String minutesDiff, String name, String startDate) {
        this.categoryId = categoryId;
        this.description = description;
        this.facebookId = facebookId;
        this.friends = friends;
        this.minutesDiff = minutesDiff;
        this.name = name;
        this.startDate = startDate;
    }
}

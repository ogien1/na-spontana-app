package com.skaminski.naspontana.generated;

import android.app.Activity;

import com.google.common.base.Joiner;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skaminski.naspontana.api.ApiUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skaminski on 30.11.2016.
 */

public class ActivityFromApi {

    @SerializedName("participantsID")
    @Expose
    private List<String> participantsID;
    @SerializedName("description")
    @Expose
        private String description;
    @SerializedName("name")
    @Expose
        private String name;
    @SerializedName("startDate")
    @Expose
        private String startDate;
    @SerializedName("category")
    @Expose
        private long category;
    @SerializedName("activityId")
    @Expose
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public List<String> getParticipantsID() {
            return this.participantsID;
        }

    public String getGuestsListString(Activity activity)
    {
        List<String> friendsAtendees = new ArrayList<>();
        for (Datum friend : ApiUtil.friendsList.getData()) {
            if(participantsID.contains(friend.getId())) {
                friendsAtendees.add(friend.getName());
            }
        }
        String friendsJoined = friendsAtendees.isEmpty() ? "" : " w tym " + Joiner.on(", ").join(friendsAtendees);
        return participantsID.size() + 1 + " uczestnik(ów)"  + friendsJoined;
    }

        public void setParticipantsID(List<String> participantsID) {
            this.participantsID = participantsID;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
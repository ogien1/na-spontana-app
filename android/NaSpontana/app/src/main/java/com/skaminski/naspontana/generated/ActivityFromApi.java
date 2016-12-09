package com.skaminski.naspontana.generated;

import android.app.Activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

        public List<String> getParticipantsID() {
            return this.participantsID;
        }

    public String descToString(Activity activity)
    {
        return "test";
//        TokenSave tokenSave = new TokenSave(activity);
//        tokenSave.reload();
//        Gson g = new Gson();
//        List<Datum> list;
//        list = g.fromJson(tokenSave.friendsList, FiendsList.class).getData();
//
//
//        String ret="";
//        for (String s : this.participantsID) {
//            for (Datum datum : list) {
//                if(s.equals(datum.getId()))
//                {
//                    ret=ret+datum.getName() + "\n";
//                }
//            }
//        }
//    return ret;
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
package com.skaminski.naspontana;

import com.skaminski.naspontana.generated.ActivityFromApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by skaminski on 09.12.2016.
 */

public interface Api {
    @GET("activity/test")
    Call<String> getTest();

    @GET("activity/friendsActivities")
    Call<List<ActivityFromApi>> getList(
            @Query("friendId")List<String> friendId);

    @GET("activity/userActivities")
    Call<List<ActivityFromApi>> getMyList(
            @Query("facebookId")String friendId);
}

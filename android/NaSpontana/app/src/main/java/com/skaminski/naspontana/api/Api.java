package com.skaminski.naspontana.api;

import com.skaminski.naspontana.generated.ActivityFromApi;
import com.skaminski.naspontana.generated.ActivityToCheck;
import com.skaminski.naspontana.generated.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("activity/categories")
    Call<List<Category>> getCategoryList();

    @POST("activity/addSimilarActivity")
    Call<Void> checkActivity(
            @Body ActivityToCheck activityToCheck);

    @POST("activity/addActivity")
    Call<Void> addActivity(
            @Body ActivityToCheck activityToCheck);

    @POST("activity/addUserToActivity")
    Call<Void> addUserToActivity(
        @Query("facebookId") String facebookId,
        @Query("activityId") String activityId);
}

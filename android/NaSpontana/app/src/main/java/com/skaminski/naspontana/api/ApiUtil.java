package com.skaminski.naspontana.api;

import android.app.Activity;
import android.util.Log;

import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.skaminski.naspontana.generated.Category;
import com.skaminski.naspontana.other.TokenSave;
import com.skaminski.naspontana.generated.ActivityFromApi;
import com.skaminski.naspontana.generated.Datum;
import com.skaminski.naspontana.generated.FiendsList;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skaminski on 09.12.2016.
 */

public class ApiUtil {

    Api service;
    public static List<Category> categoryList;
    public static  FiendsList friendsList;
    public ApiUtil() {
        String url;
        Retrofit retrofit;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        url = "http://212.191.92.101:6008/naspontana-app/";
        retrofit = new Retrofit.Builder().baseUrl(url).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(Api.class);
    }



    public Call<List<ActivityFromApi>> getList(Activity activity)
    {
        TokenSave tokenSave = new TokenSave(activity);
        tokenSave.reload();
        Gson g = new Gson();
        FiendsList f = g.fromJson(tokenSave.friendsList, FiendsList.class);
        List<String> friendsId = new ArrayList<>();
        for (Datum datum : f.getData()) {
            friendsId.add(datum.getId());
        }
        friendsId.add("12345");
        return service.getList(friendsId);
    }

    public Call<List<ActivityFromApi>> getMyList(Activity activity)
    {
        TokenSave tokenSave = new TokenSave(activity);
        tokenSave.reload();
        Gson g = new Gson();
        LoginResult loginResult= g.fromJson(tokenSave.json, LoginResult.class);
        return service.getMyList(loginResult.getAccessToken().getUserId());
    }

    public Call<List<Category>> getCategories()
    {
        return service.getCategoryList();
    }

    public Api getService()
    {
        return service;
    }


}

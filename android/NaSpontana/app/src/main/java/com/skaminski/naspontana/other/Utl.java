package com.skaminski.naspontana.other;

import android.app.Activity;
import android.util.Log;

import com.facebook.login.LoginResult;
import com.google.gson.Gson;
import com.skaminski.naspontana.generated.Category;

import java.util.List;

/**
 * Created by skaminski on 09.12.2016.
 */

public class Utl {


    public static void saveLoginResult(LoginResult loginResult, Activity activity)
    {
        Gson gson = new Gson();
        String json = gson.toJson(loginResult);
//        DaoPreferences dao = new DaoPreferences(activity);
//        dao.commit(json, "token");
        TokenSave tokenSave = new TokenSave(activity);
        tokenSave.json = json;
        tokenSave.apply();
    }

    public static LoginResult getLoginResult(Activity activity)
    {
//        DaoPreferences dao = new DaoPreferences(activity);
//        String string = "";
//        dao.load(string, "token");

        TokenSave tokenSave = new TokenSave(activity);
        tokenSave.reload();
        String string = tokenSave.json;

        Gson gson = new Gson();
        LoginResult result = gson.fromJson(string, LoginResult.class);
        return result;
    }

    public static void deleteAccount(Activity activity)
    {
        TokenSave tokenSave = new TokenSave(activity);
        tokenSave.clear();
    }

    public static boolean isLoginResultExist(Activity activity)
    {
        try {
            TokenSave tokenSave = new TokenSave(activity);
            tokenSave.reload();
            String string = tokenSave.json;
            Gson gson = new Gson();
            LoginResult result = gson.fromJson(string, LoginResult.class);
            Log.d("dd", result.getAccessToken().getApplicationId());
        }
        catch (Throwable e)
        {
            return false;
        }
        return true;
    }
}

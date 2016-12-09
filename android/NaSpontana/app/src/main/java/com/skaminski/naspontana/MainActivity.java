package com.skaminski.naspontana;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.facebookButton)
    LoginButton facebookButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        if(Utl.isLoginResultExist(this))
        {
            Log.d("d","jest");
            o(Utl.getLoginResult(this));
//            startActivity(new Intent(MainActivity.this.getApplicationContext() , DashboardActivity.class));
        }
        else
        {
            Log.d("d","nie jest");
        }

        callbackManager = CallbackManager.Factory.create();
        facebookButton.setReadPermissions("user_friends");
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Utl.saveLoginResult(loginResult, MainActivity.this);
                o(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    void o(LoginResult loginResult)
    {
        new GraphRequest(
                loginResult.getAccessToken(),
//                AccessToken.getCurrentAccessToken(),
                "/"+loginResult.getAccessToken().getUserId()+"/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        response.getRawResponse();
                        TokenSave tokenSave = new TokenSave(MainActivity.this);
                        tokenSave.friendsList = response.getRawResponse();
                        tokenSave.commit();
                        startActivity(new Intent(MainActivity.this.getApplicationContext() , DashboardActivity.class));

                    }
                }
        ).executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }
}

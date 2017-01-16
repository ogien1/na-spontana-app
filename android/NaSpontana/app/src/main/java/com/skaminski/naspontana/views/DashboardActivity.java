package com.skaminski.naspontana.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.skaminski.naspontana.api.Api;
import com.skaminski.naspontana.R;
import com.skaminski.naspontana.other.TokenSave;
import com.skaminski.naspontana.generated.ActivityFromApi;
import com.skaminski.naspontana.generated.Datum;
import com.skaminski.naspontana.generated.FiendsList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager container;
    @BindView(R.id.content_dashboard)
    RelativeLayout contentDashboard;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(DashboardActivity.this, AddActivity.class));
            }
        });

        TokenSave tokenSave = new TokenSave(this);
        tokenSave.reload();
        Gson g = new Gson();
        FiendsList f = g.fromJson(tokenSave.friendsList, FiendsList.class);
        f.getData();

        container.setOffscreenPageLimit(10);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);
        tabs.setupWithViewPager(container);
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadListOfActivity();
    }

    void downloadListOfActivity() {

        TokenSave tokenSave = new TokenSave(this);
        tokenSave.reload();
        Gson g = new Gson();
        FiendsList f = g.fromJson(tokenSave.friendsList, FiendsList.class);
        List<String> friendsId = new ArrayList<>();
        for (Datum datum : f.getData()) {
            friendsId.add(datum.getId());
        }

        String url;
        Retrofit retrofit;
        Api service;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        url = "http://212.191.92.101:6008/naspontana-app/";
        retrofit = new Retrofit.Builder().baseUrl(url).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(Api.class);
        service.getList(friendsId).enqueue(new Callback<List<ActivityFromApi>>() {
            @Override
            public void onResponse(Call<List<ActivityFromApi>> call, Response<List<ActivityFromApi>> response) {
                Log.d("d", "d");
            }

            @Override
            public void onFailure(Call<List<ActivityFromApi>> call, Throwable t) {
                Log.d("d", "d");
            }
        });
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new AllListFragment();
                case 1:
                    return new ListMyFragment();
                case 2:
                    return new TestFragment();
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Znajomi";
                case 1:
                    return "Ja";
                case 2:
                    return "Test";

            }
            return null;
        }
    }
}

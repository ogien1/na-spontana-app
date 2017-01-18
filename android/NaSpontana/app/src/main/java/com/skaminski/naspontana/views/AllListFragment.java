package com.skaminski.naspontana.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skaminski.naspontana.R;
import com.skaminski.naspontana.api.ApiUtil;
import com.skaminski.naspontana.generated.ActivityFromApi;
import com.skaminski.naspontana.generated.Category;
import com.skaminski.naspontana.other.AllAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllListFragment extends Fragment {


    @BindView(R.id.recycleViewAll)
    RecyclerView recycleViewAll;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    ApiUtil apiUtil;

    public AllListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_my, container, false);
        ButterKnife.bind(this, view);
        apiUtil = new ApiUtil();

        refresh();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });


        apiUtil.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful())
                    ApiUtil.categoryList = response.body();
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("e", "e");
            }
        });
        return view;
    }


    private  void refresh()
    {
        apiUtil.getList(this.getActivity()).enqueue(new Callback<List<ActivityFromApi>>() {
            @Override
            public void onResponse(Call<List<ActivityFromApi>> call, Response<List<ActivityFromApi>> response) {
                refresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    AllAdapter cameraAdapter = new AllAdapter(getContext(), response.body(), getActivity(), true);
                    recycleViewAll.setAdapter(cameraAdapter);
                    recycleViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<ActivityFromApi>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

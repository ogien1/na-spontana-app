package com.skaminski.naspontana.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skaminski.naspontana.R;
import com.skaminski.naspontana.api.ApiUtil;
import com.skaminski.naspontana.generated.ActivityFromApi;
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
public class ListMyFragment extends Fragment {


    @BindView(R.id.recycleViewAll)
    RecyclerView recycleViewAll;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    public ListMyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_my, container, false);
        ButterKnife.bind(this, view);


        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    ApiUtil apiUtil;

    private void updateData() {
        apiUtil = new ApiUtil();

    }

    private void refresh()
    {
        apiUtil.getMyList(this.getActivity()).enqueue(new Callback<List<ActivityFromApi>>() {
            @Override
            public void onResponse(Call<List<ActivityFromApi>> call, Response<List<ActivityFromApi>> response) {
                refresh.setRefreshing(false);
                if (response.isSuccessful()) {
                    AllAdapter cameraAdapter = new AllAdapter(getContext(), response.body(), getActivity(), false);
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

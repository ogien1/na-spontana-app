package com.skaminski.naspontana;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skaminski.naspontana.generated.ActivityFromApi;

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

    public ListMyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_my, container, false);
        ButterKnife.bind(this, view);
        ApiUtil apiUtil = new ApiUtil();
        apiUtil.getMyList(this.getActivity()).enqueue(new Callback<List<ActivityFromApi>>() {
            @Override
            public void onResponse(Call<List<ActivityFromApi>> call, Response<List<ActivityFromApi>> response) {
                if(response.isSuccessful())
                {
                    AllAdapter cameraAdapter = new AllAdapter(getContext(), response.body(), getActivity());
                    recycleViewAll.setAdapter(cameraAdapter);
                    recycleViewAll.setLayoutManager(new LinearLayoutManager(getContext()));
                }
            }

            @Override
            public void onFailure(Call<List<ActivityFromApi>> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

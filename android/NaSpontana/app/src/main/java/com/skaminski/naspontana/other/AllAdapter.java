package com.skaminski.naspontana.other;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.skaminski.naspontana.R;
import com.skaminski.naspontana.api.ApiUtil;
import com.skaminski.naspontana.generated.ActivityFromApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by skaminski on 09.12.2016.
 */

public class AllAdapter extends RecyclerView.Adapter<AllAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.recycle_view_all, parent, false);
        return new ViewHolder(v, activity, thingList);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActivityFromApi activityFromApi = thingList.get(position);
        holder.textViewGoscie.setText(activityFromApi.descToString(activity));
        holder.textViewData.setText(activityFromApi.getStartDate());
        if(clickeable)
        {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new MaterialDialog.Builder(activity)
                            .title("Czy na pewno chcesz dolaczyc?")
                            .positiveText("tak")
                            .negativeText("nie")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    ApiUtil api = new ApiUtil();
                                   String fbId=  Utl.getLoginResult(activity).getAccessToken().getUserId();
                                    api.getService().addUserToActivity(fbId, activityFromApi.getActivityId()).enqueue(new Callback<Void>() {
                                        @Override
                                        public void onResponse(Call<Void> call, Response<Void> response) {
                                            if(response.isSuccessful())
                                                Toast.makeText(activity, "Dolaczyles do wydarzenia", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onFailure(Call<Void> call, Throwable t) {
                                            Log.d("d","d");
                                        }
                                    });
                                }
                            })
                            .show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return     thingList.size();
    }

    private Context context;
    public Activity activity;
    private List<ActivityFromApi> thingList;
    boolean clickeable;

    public AllAdapter(Context context, List<ActivityFromApi> thingList, Activity activity, boolean clickeable) {
        this.context = context;
        this.thingList = thingList;
        this.activity = activity;
        this.clickeable = clickeable;
    }



    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        CircleImageView imageViewIcon;
        TextView textViewData;
        TextView textViewGoscie;
        LinearLayout linearLayout;
        Activity activity;
        List<ActivityFromApi> thingList;

        ViewHolder(View itemView, Activity activity, List<ActivityFromApi> thingList) {
            super(itemView);
            textViewData = (TextView) itemView.findViewById(R.id.recycle_view_time);
            textViewGoscie= (TextView)  itemView.findViewById(R.id.recycle_view_guests);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_list);
            this.thingList = thingList;
            this.activity = activity;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}

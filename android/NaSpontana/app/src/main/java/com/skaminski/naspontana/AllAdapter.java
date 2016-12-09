package com.skaminski.naspontana;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skaminski.naspontana.generated.ActivityFromApi;

import java.util.List;

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
        ActivityFromApi activityFromApi = thingList.get(position);
        holder.textViewGoscie.setText(activityFromApi.descToString(activity));
        holder.textViewData.setText(activityFromApi.getStartDate());
    }

    @Override
    public int getItemCount() {
        return     thingList.size();
    }

    private Context context;
    public Activity activity;
    private List<ActivityFromApi> thingList;

    public AllAdapter(Context context, List<ActivityFromApi> thingList, Activity activity) {
        this.context = context;
        this.thingList = thingList;
        this.activity = activity;
    }



    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //        CircleImageView imageViewIcon;
        TextView textViewData;
        TextView textViewGoscie;

        Activity activity;
        List<ActivityFromApi> thingList;

        ViewHolder(View itemView, Activity activity, List<ActivityFromApi> thingList) {
            super(itemView);
            textViewData = (TextView) itemView.findViewById(R.id.recycle_view_time);
            textViewGoscie= (TextView)  itemView.findViewById(R.id.recycle_view_guests);
            this.thingList = thingList;
            this.activity = activity;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}

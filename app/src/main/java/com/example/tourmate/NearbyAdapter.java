package com.example.tourmate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tourmate.NearbyLocation.NearbyLocation;
import com.example.tourmate.NearbyLocation.Result;

import java.util.List;

public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.ViewHolder> {

    private Context context;
    private List<Result>list;

    public NearbyAdapter(Context context, List<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_item_layout,viewGroup,false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Result result =list.get(i);

        viewHolder.textView.setText(result.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.nameTV);
        }
    }
}

package com.example.bigbillionadmin.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbillionadmin.R;
import com.example.bigbillionadmin.model.BIDS;
import com.example.bigbillionadmin.model.SharePoints;

import java.util.ArrayList;


public class AllBidsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<BIDS> sharePoints;

    public AllBidsAdapter(Activity activity, ArrayList<BIDS> sharePoints) {
        this.activity = activity;
        this.sharePoints = sharePoints;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.allbids_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final BIDS bids = sharePoints.get(position);
        holder.tvNumber.setText(bids.getNumber());
        holder.tvPoints.setText(bids.getPoints());
        holder.tvGameType.setVisibility(View.VISIBLE);
        holder.tvGameType.setText(bids.getGame_type());
        if (bids.getGame_type().equals("haruf")){
            holder.tvGameType.setVisibility(View.VISIBLE);
            holder.tvGameType.setText(bids.getGame_type());
        }



    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return sharePoints.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvNumber,tvPoints,tvGameType;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvGameType = itemView.findViewById(R.id.tvGameType);


        }
    }
}


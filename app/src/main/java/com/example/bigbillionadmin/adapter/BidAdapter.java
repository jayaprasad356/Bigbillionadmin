package com.example.bigbillionadmin.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bigbillionadmin.R;
import com.example.bigbillionadmin.model.BIDS;

import java.util.ArrayList;

public class BidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Button btnSubmit;
    final ArrayList<BIDS> bids;

    public BidAdapter(Activity activity, ArrayList<BIDS> bids) {
        this.activity = activity;
        this.bids = bids;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.bids_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final BIDS bids1 = bids.get(position);
        holder.tvNumber.setText(bids1.getNumber());
        holder.tvPoints.setText(bids1.getPoints());
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return bids.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvNumber,tvPoints;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvPoints = itemView.findViewById(R.id.tvPoints);


        }
    }
}


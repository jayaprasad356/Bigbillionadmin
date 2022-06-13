package com.example.bigbillionadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbillionadmin.R;
import com.example.bigbillionadmin.Update_withdrawalActivity;
import com.example.bigbillionadmin.model.Users;
import com.example.bigbillionadmin.model.Withdrawal;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    Button btnSubmit;
    final ArrayList<Users> users;

    public UsersAdapter(Activity activity, ArrayList<Users> users) {
        this.activity = activity;
        this.users = users;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.user_lyt, parent, false);
        return new ItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ItemHolder holder = (ItemHolder) holderParent;
        final Users user = users.get(position);
        holder.tvMobile.setText(user.getMobile());
        holder.tvName.setText(user.getName());
        holder.tvPoints.setText(user.getPoints());
        holder.tvEarnings.setText(user.getEarn());


    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvMobile,tvPoints,tvEarnings;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvEarnings = itemView.findViewById(R.id.tvEarnings);



        }
    }
}


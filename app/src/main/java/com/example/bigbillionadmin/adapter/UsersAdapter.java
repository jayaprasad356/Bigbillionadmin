package com.example.bigbillionadmin.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigbillionadmin.ManageWalletActivity;
import com.example.bigbillionadmin.R;
import com.example.bigbillionadmin.TransactionListsActivity;
import com.example.bigbillionadmin.helper.ApiConfig;
import com.example.bigbillionadmin.helper.Constant;
import com.example.bigbillionadmin.model.BIDS;
import com.example.bigbillionadmin.model.Users;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
        holder.tvDateTime.setText(user.getDate_created());
        if (user.getUser_status().equals("2")){
            holder.switchBlock.setChecked(true);
        }else {
            holder.switchBlock.setChecked(false);

        }
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ManageWalletActivity.class);
                intent.putExtra("id",user.getId());
                activity.startActivity(intent);
            }
        });
        holder.btnTransacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TransactionListsActivity.class);
                intent.putExtra(Constant.USER_ID,user.getId());
                activity.startActivity(intent);
            }
        });
        holder.switchBlock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    blockUserApi(user.getId(),"2");

                }else {
                    blockUserApi(user.getId(),"1");


                }


            }
        });


    }

    private void blockUserApi(String user_id, String user_status)
    {
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,user_id);
        params.put(Constant.USER_STATUS,user_status);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("ALL_BIDS",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(activity, ""+jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.USER_BLOCK_URL, params, true);

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

        TextView tvName,tvMobile,tvPoints,tvDateTime;
        Button btnUpdate,btnTransacion;
        Switch switchBlock;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvPoints = itemView.findViewById(R.id.tvPoints);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnTransacion = itemView.findViewById(R.id.btnTransacion);
            switchBlock = itemView.findViewById(R.id.switchBlock);



        }
    }
}


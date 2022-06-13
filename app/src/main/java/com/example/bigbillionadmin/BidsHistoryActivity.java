package com.example.bigbillionadmin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bigbillionadmin.adapter.BidAdapter;
import com.example.bigbillionadmin.adapter.HarufBidAdapter;
import com.example.bigbillionadmin.helper.ApiConfig;
import com.example.bigbillionadmin.helper.Constant;
import com.example.bigbillionadmin.helper.Functions;
import com.example.bigbillionadmin.model.BIDS;
import com.example.bigbillionadmin.model.Game;
import com.example.bigbillionadmin.model.HarufBids;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BidsHistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView harufrecyclerView;
    BidAdapter bidAdapter;
    HarufBidAdapter harufBidAdapter;
    Activity activity;
    Button btnSubmit;
    Spinner spinGame,spinDay;
    String date;
    LinearLayout bidsl1,bidsl2;
    ArrayList<BIDS> bids = new ArrayList<>();
    ArrayList<HarufBids> harufBids = new ArrayList<>();
    String spinGameName;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids_history);
        activity = BidsHistoryActivity.this;
        Id = getIntent().getStringExtra("id");


        bidsl1 = findViewById(R.id.bidsl1);
        bidsl2 = findViewById(R.id.bidsl2);
        recyclerView = findViewById(R.id.recyclerView);
        harufrecyclerView = findViewById(R.id.harufrecyclerView);
        btnSubmit = findViewById(R.id.btnSubmit);
        spinGame = findViewById(R.id.spinGame);
        spinDay = findViewById(R.id.spinDay);
        Functions.setData(activity,spinGame);

        spinGame.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Game game = (Game) adapterView.getSelectedItem();
                spinGameName = game.getGamename();
                //Toast.makeText(activity, ""+game.getGamename(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        harufrecyclerView.setLayoutManager(linearLayoutManager2);
        harufrecyclerView.setHasFixedSize(true);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if (spinGame.getSelectedItemPosition() == 0){
                    Toast.makeText(activity, "Please,Select Game", Toast.LENGTH_SHORT).show();
                }
                else if (spinDay.getSelectedItemPosition() == 0  || spinGame.getSelectedItemPosition() == 4){
                    Toast.makeText(activity, "Please,Select Day", Toast.LENGTH_SHORT).show();
                }
                else {
                    String pattern = "yyyy-MM-dd";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

                    if (spinDay.getSelectedItemPosition() == 1){

                        date = simpleDateFormat.format(getMeYesterday());


                    }
                    else if (spinDay.getSelectedItemPosition() == 2){
                        date = simpleDateFormat.format(new Date());


                    }
                    else if (spinDay.getSelectedItemPosition() == 3){
                        date = simpleDateFormat.format(getMeTomorrow());


                    }
                    bidsl1.setVisibility(View.GONE);
                    bidsl2.setVisibility(View.GONE);


                    bidsList();
                    harufbidsList();
                }


            }
        });



    }
    private Date getMeTomorrow(){
        return new Date(System.currentTimeMillis()+24*60*60*1000);
    }
    private Date getMeYesterday(){
        return new Date(System.currentTimeMillis()-24*60*60*1000);
    }
    private void harufbidsList()
    {
        harufBids.clear();

        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,Id);
        params.put(Constant.GAME_NAME,spinGame.getSelectedItem().toString());
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl2.setVisibility(View.VISIBLE);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                HarufBids group = g.fromJson(jsonObject1.toString(), HarufBids.class);
                                harufBids.add(group);
                            } else {
                                break;
                            }
                        }
                        harufBidAdapter = new HarufBidAdapter(activity, harufBids);
                        harufrecyclerView.setAdapter(harufBidAdapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(BidsHistoryActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.HARUFBIDSLIST_URL, params, true);

    }
    private void bidsList()
    {
        bids.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.USER_ID,Id);
        params.put(Constant.GAME_NAME,spinGameName);
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        bidsl1.setVisibility(View.VISIBLE);
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                BIDS group = g.fromJson(jsonObject1.toString(), BIDS.class);
                                bids.add(group);
                            } else {
                                break;
                            }
                        }
                        bidAdapter = new BidAdapter(activity, bids);
                        recyclerView.setAdapter(bidAdapter);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.BIDSLIST_URL, params, true);
    }

}
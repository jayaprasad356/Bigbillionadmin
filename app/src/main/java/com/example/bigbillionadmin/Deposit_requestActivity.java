package com.example.bigbillionadmin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigbillionadmin.adapter.DepositPointsAdapter;
import com.example.bigbillionadmin.helper.ApiConfig;
import com.example.bigbillionadmin.helper.Constant;
import com.example.bigbillionadmin.model.DepositPoints;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Deposit_requestActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    DepositPointsAdapter depositPointsAdapter;
    Activity activity;
    TextView tvDate;
    String date;
    final Calendar myCalendar = Calendar.getInstance();
    ArrayList<DepositPoints> transactions = new ArrayList<>();



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_request);
        activity = Deposit_requestActivity.this;
        recyclerView = findViewById(R.id.recyclerView);
        tvDate = findViewById(R.id.tvDate);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = dateObj.format(formatter);
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=  new DatePickerDialog(activity,R.style.Theme_Bigbillionadmin, datepick, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });
        depositList();
    }
    DatePickerDialog.OnDateSetListener datepick = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            monthOfYear = monthOfYear + 1;
            NumberFormat f = new DecimalFormat("00");
            String format = "%1$02d"; // two digits
            String month = String.format(format, monthOfYear);
            String day = String.format(format, dayOfMonth);

            tvDate.setText(year +"-"+month + "-"+day);
            date = tvDate.getText().toString().trim();
            depositList();
        }
    };

    private void depositList()
    {
        transactions.clear();
        Map<String, String> params = new HashMap<>();
        params.put(Constant.DATE,date);
        ApiConfig.RequestToVolley((result, response) -> {
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        JSONObject object = new JSONObject(response);
                        JSONArray jsonArray = object.getJSONArray(Constant.DATA);
                        Gson g = new Gson();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            if (jsonObject1 != null) {
                                DepositPoints group = g.fromJson(jsonObject1.toString(), DepositPoints.class);
                                transactions.add(group);
                            } else {
                                break;
                            }
                        }
                        depositPointsAdapter = new DepositPointsAdapter(activity, transactions);
                        recyclerView.setAdapter(depositPointsAdapter);
                    }
                    else {
                        Toast.makeText(activity, ""+String.valueOf(jsonObject.getString(Constant.MESSAGE)), Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(activity, String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }
        }, activity, Constant.POINTS_LIST_URL, params, true);
    }
}
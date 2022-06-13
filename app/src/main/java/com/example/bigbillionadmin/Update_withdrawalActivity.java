package com.example.bigbillionadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.bigbillionadmin.helper.ApiConfig;
import com.example.bigbillionadmin.helper.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Update_withdrawalActivity extends AppCompatActivity {
    Button btnUpdate;
    RadioButton rPaid,rCancel;
    String Id;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_withdrawal);
        Id = getIntent().getStringExtra("id");
        btnUpdate = findViewById(R.id.btnUpdate);
        rPaid = findViewById(R.id.rPaid);
        rCancel = findViewById(R.id.rCancel);
        activity = Update_withdrawalActivity.this;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rPaid.isChecked() || rCancel.isChecked()){
                    updateStatus();

                }
                else {
                    Toast.makeText(Update_withdrawalActivity.this, "Please Select Option", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void updateStatus()
    {
        String Status = "0";
        if (rPaid.isChecked()){
            Status = "1";

        }
        else {
            Status = "2";

        }
        Map<String, String> params = new HashMap<>();
        params.put(Constant.STATUS, Status);
        params.put(Constant.WITHDRAWAL_ID, Id);
        ApiConfig.RequestToVolley((result, response) -> {
            Log.d("WITH_RES",response);
            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(this, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(activity, WithdrawalActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, String.valueOf(response) + String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, activity, Constant.UPDATE_WITHDRAWALS_URL, params, true);

    }
}
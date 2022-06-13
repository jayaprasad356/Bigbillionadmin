package com.example.bigbillionadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class HomeActivity extends AppCompatActivity {
    RelativeLayout deposit_req,withdrawal,declare_result,show_biddings,sharing_point,winners,users,settings;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity = HomeActivity.this;
        deposit_req = findViewById(R.id.deposit_req);
        withdrawal = findViewById(R.id.withdrawal);
        declare_result = findViewById(R.id.declare_result);
        show_biddings = findViewById(R.id.show_biddings);
        sharing_point = findViewById(R.id.sharing_point);
        winners = findViewById(R.id.winners);
        users = findViewById(R.id.users);
        settings = findViewById(R.id.settings);

        deposit_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Deposit_requestActivity.class);
                startActivity(intent);

            }
        });
        withdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WithdrawalActivity.class);
                startActivity(intent);

            }
        });
        declare_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Declare_resultActivity.class);
                startActivity(intent);

            }
        });
        show_biddings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Show_biddinsActivity.class);
                startActivity(intent);


            }
        });
        sharing_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, Sharing_pointsActivity.class);
                startActivity(intent);

            }
        });
        winners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, WinnersActivity.class);
                startActivity(intent);

            }
        });
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, UsersActivity.class);
                startActivity(intent);

            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, SettingsActivity.class);
                startActivity(intent);

            }
        });
    }
}
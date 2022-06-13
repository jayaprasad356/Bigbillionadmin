package com.example.bigbillionadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigbillionadmin.helper.ApiConfig;
import com.example.bigbillionadmin.helper.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Declare_resultActivity extends AppCompatActivity {

    DatePicker picker;
    Button postResult;
    String day,month,year;
    EditText ds,gb,gl,fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare_result);
        postResult = findViewById(R.id.postResult);
        picker=(DatePicker)findViewById(R.id.datePicker1);
        ds = findViewById(R.id.ds);
        gb = findViewById(R.id.gb);
        gl = findViewById(R.id.gl);
        fd = findViewById(R.id.fd);



        postResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ds.getText().toString().equals("")){
                    ds.setError("Fill DS");
                    ds.requestFocus();
                }
                else if (gb.getText().toString().equals("")){
                    gb.setError("Fill GB");
                    gb.requestFocus();
                }
                else if (gl.getText().toString().equals("")){
                    gl.setError("Fill GL");
                    gl.requestFocus();
                }
                else if (fd.getText().toString().equals("")){
                    fd.setError("Fill FD");
                    fd.requestFocus();
                }
                else {
                    postResultApi();

                }

            }
        });


    }

    private void postResultApi()
    {
        day = ""+picker.getDayOfMonth();
        month = ""+(picker.getMonth() + 1);
        year = ""+picker.getYear();
        String format = "%1$02d"; // two digits
        long daytime = Long.parseLong(day);
        long monthtime = Long.parseLong(month);
        String date = year + "-" + String.format(format, monthtime) +  "-" + String.format(format, daytime) ;
        Map<String, String> params = new HashMap<>();
        params.put(Constant.DATE, date);
        params.put(Constant.DAY, day);
        params.put(Constant.MONTH, month);
        params.put(Constant.YEAR, year);
        params.put(Constant.DS, ds.getText().toString().trim());
        params.put(Constant.GB, gb.getText().toString().trim());
        params.put(Constant.GL, gl.getText().toString().trim());
        params.put(Constant.FD, fd.getText().toString().trim());
        ApiConfig.RequestToVolley((result, response) -> {

            if (result) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean(Constant.SUCCESS)) {
                        Toast.makeText(this, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Declare_resultActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, jsonObject.getString(Constant.MESSAGE), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            } else {
                Toast.makeText(this, String.valueOf(response) + String.valueOf(result), Toast.LENGTH_SHORT).show();

            }
            //pass url
        }, Declare_resultActivity.this, Constant.POST_RESULTS_URL, params, true);


    }
}

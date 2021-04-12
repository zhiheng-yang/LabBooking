package com.example.labbooking;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.labbooking.Entity.Lab;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class ChooseLab extends AppCompatActivity {

    private Button[] cells;
    private Button back;
    private LinearLayout table_layout;

    private LinearLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getAppointmentData("http://49.234.112.12:8081/lab/getLabs");
        main = (LinearLayout)this.findViewById(R.id.main);

        back = ChooseLab.this.findViewById(R.id.back);
        table_layout = ChooseLab.this.findViewById(R.id.table);  // 直接继承LinerLayout
        table_layout.removeAllViews();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChooseLab
                        .this, MainActivity.class); startActivity(intent);
            }
        });
    }


    public void getAppointmentData(String url){
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = new String(response.getBytes("ISO-8859-1"),"utf-8");

                            Toast.makeText(ChooseLab.this, response, Toast.LENGTH_SHORT).show();

                            initTableByAppointment(response);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        Log.d("TAG", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
//        return null;
    }


    public void initTableByAppointment(String jsonArrayStr){
        com.alibaba.fastjson.JSONObject object
                = com.alibaba.fastjson.JSONObject.parseObject(jsonArrayStr);
        List<Lab> labs = JSONArray.parseArray(object.getString("data"), Lab.class);

        cells = new Button[labs.size()];
        for (int i = 0; i < cells.length; i++) {
            LinearLayout each_row_linearOut = new LinearLayout(ChooseLab.this);
            final Button each_button = new Button(ChooseLab.this);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
            // left top right bottom
            lp.setMargins(3, 3,
                    3, 3);
            each_button.setBackgroundColor(Color.WHITE);
            each_button.setEnabled(true);
            each_button.setHeight(200);
            each_button.setWidth(800);

            each_row_linearOut.addView(each_button);
            each_button.setLayoutParams(lp);
            each_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(ChooseLab.this, each_button.getTag()+"被点击", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ChooseLab.this,Booking.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("lab",each_button.getTag()+"");//传递一个名为name值为"Demo"的string类型数据
                    intent.putExtras(bundle);//传递过去
                    startActivity(intent);
                }
            });
            cells[i] = each_button;
            table_layout.addView(each_row_linearOut);
        }
        int i = 0;

        for (Lab lab: labs){
            cells[i].setText(lab.getName());
            cells[i++].setTag(lab.getId());
        }

    }

}

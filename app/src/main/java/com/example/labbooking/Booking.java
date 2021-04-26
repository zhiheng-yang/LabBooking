package com.example.labbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.labbooking.Entity.Appointment;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Booking extends AppCompatActivity {

    public static int CHOSEN_MONTH;
    public int counter = 0;
    private static String lab; // 预约的哪个实验室，通过Intent传递
    private int year_now = new Date().getYear();
    private int month_now = new Date().getMonth()+1;
    private int day_now = new Date().getDay();
    private String test_data;
//    private RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

    private int tableCol = Booking.getMaxDay(year_now, CHOSEN_MONTH==0?month_now:CHOSEN_MONTH)+1;
    private int tableRow = 6;

    private String months_after_now[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

    public final String[] col = new String[tableCol+1];

    public final String[] row = {
            "",
            "1-2","3-5","6-7","8-9","10-12"};


    private Button[][] cells = new Button[tableRow][tableCol];
    private Button back;
    private int[][] result = new int[9][9];
    private LinearLayout table_layout;

    private LinearLayout main;

    Handler mHandler = null;// 用于线程之间交互


    // 下拉列表的
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        initCol();
        main = (LinearLayout)this.findViewById(R.id.main);
        final Bundle bundle=getIntent().getExtras();
        Intent getIntent = getIntent();
        lab = getIntent.getStringExtra("lab");


        // 下拉表-------------------------------------------------------------------------
        spinner = (Spinner) findViewById(R.id.spinner);
        //数据
        data_list = new ArrayList<String>();

        for (int i = CHOSEN_MONTH==0?month_now:CHOSEN_MONTH; i < months_after_now.length+1; i++) {
            data_list.add(i+"月");
        }
        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                     int position, long id) {
                String str = (String) spinner.getSelectedItem();
                String month_int = str.substring(0,(int)Math.ceil(str.length()/2.0));
                CHOSEN_MONTH=Integer.parseInt(month_int);
                if(++counter>1) {
                    Intent intent = new Intent(Booking.this, Booking.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("lab",lab);//传递一个名为name值为"Demo"的string类型数据
                    intent.putExtras(bundle);//传递过去
                    startActivity(intent);
                }
//                Toast.makeText(Booking.this, month_int, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              // TODO Auto-generated method stub
            }
        });
        // ----------------------------------------------------
        back = Booking.this.findViewById(R.id.back);
        table_layout = Booking.this.findViewById(R.id.table);  // 直接继承LinerLayout
        table_layout.removeAllViews();
        for (int i = 0; i < tableRow; i++) {
            LinearLayout each_row_linearOut = new LinearLayout(Booking.this);
            for (int j = 0; j < tableCol; j++) {
                final Button each_button = new Button(Booking.this);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
                // left top right bottom
//                lp.setMargins(j%3==0?(j==0?20:10):5, i%3==0?(i==0?20:10):5,
//                        (j+1)%3==0?(j==8?20:10):5, (i+1)%3==0?(i==8?20:10):5);
                lp.setMargins(3, 3,
                        3, 3);
                each_button.setBackgroundColor(Color.WHITE);
                each_button.setEnabled(true);
                each_button.setHeight(200);
                each_button.setTag(year_now+"-"+(CHOSEN_MONTH==0?month_now:CHOSEN_MONTH)+"-"+j+"-"+row[i]); // tag信息待定
                each_button.setTag(R.id.kalender_tag, i);
                if (i!=0 && j!=0) {
                    each_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Booking.this, each_button.getTag()+"被点击", Toast.LENGTH_SHORT).show();
//                            int user_id, int lab_id, String date, String remark, int kalendar_id
                            String[] date_ymd = each_button.getTag().toString().split("-");
                            int year_split = Integer.parseInt(date_ymd[0])+1900;
                            Toast.makeText(Booking.this, lab, Toast.LENGTH_SHORT).show();

                            volleypost(1, Integer.parseInt(lab), year_split+"-"+date_ymd[1]+"-"+date_ymd[2], "test123", Integer.parseInt(each_button.getTag(R.id.kalender_tag).toString()));
                        }
                    });
                }else if (i==0){
                    each_button.setText(col[j]);
                }else if (j==0){
                    each_button.setText(row[i]+"节");
                }

//                InputFilter[] filters = {new InputFilter.LengthFilter(1)}; //限制最大输入长度是1
//                editText.setFilters(filters);
//                        editText.setHeight(editText.getWidth());

//                cells[i][j] = editText;
                cells[i][j] = each_button;
                each_row_linearOut.addView(each_button);
                each_button.setLayoutParams(lp);
            }
            table_layout.addView(each_row_linearOut);
        }
        getAppointmentData("http://49.234.112.12:8081/appointment/getAppointments");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Booking
                        .this, ChooseLab.class); startActivity(intent);
            }
        });
    }

    public void initCol(){
        this.col[0] = "";
        for (int i = 1; i < this.tableCol; i++) {
            this.col[i] = CHOSEN_MONTH + "月" + i + "日";
            this.col[i] = i+"";
        }
    }



    public static int getMaxDay(int year, int month){
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            return 31;
        }else if (month == 2){
            if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0){
                return 29;
            }else{
                return 28;
            }
        }else if (month == 4 || month == 6 || month == 9 || month == 11){
            return 30;
        }
        return 0;
    }

    public String getAppointmentData(String url){
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            response = new String(response.getBytes("ISO-8859-1"),"utf-8");
//                            Toast.makeText(Booking.this, response, Toast.LENGTH_SHORT).show();
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
        return null;
    }


    public void initTableByAppointment(String jsonArrayStr){
        com.alibaba.fastjson.JSONObject object
                = com.alibaba.fastjson.JSONObject.parseObject(jsonArrayStr);
        List<Appointment> appointments =  JSONArray.parseArray(object.getString("data"), Appointment.class);


        for (int i = 1; i < tableRow; i++) {
            for (int j = 1; j < tableCol; j++) {
                for (Appointment appointment: appointments) {

                    int y = Integer.parseInt((appointment.getDate().toString()).substring(0,4))-1900;
                    int m = Integer.parseInt((appointment.getDate().toString()).substring(5,7));
                    int d = Integer.parseInt((appointment.getDate().toString()).substring(8,10));
                    String ymd = y+"-"+m+"-"+d+"-"+appointment.getKalendar().getDescription();

                    if (ymd.equals(cells[i][j].getTag())) {
                        cells[i][j].setText(appointment.getRemark());
                        cells[i][j].setEnabled(false);
                        cells[i][j].setBackgroundColor(Color.RED);
                    }
                }
            }
        }
    }

    //volley发送post请求
    private void volleypost(int user_id, int lab_id, String date, String remark, int kalendar_id) {
        final int user_id2 = user_id;
        final int lab_id2 = lab_id;
        final String date2 = date;
        final String remark2 = remark;
        final int kalendar_id2 = kalendar_id;
        String url = "http://49.234.112.12:8081/appointment/addAppointment?";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.i("aa", "post请求成功" + s);
//                initTableByAppointment(s);
                Intent intent=new Intent(Booking
                        .this, ChooseLab.class); startActivity(intent);
                Toast.makeText(Booking.this, "000000000000000000"+s, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "post请求失败" + volleyError.toString());
                Toast.makeText(Booking.this, "111111"+volleyError.toString(), Toast.LENGTH_LONG).show();
                }
            })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("user_id", user_id2+"");
                map.put("lab_id", lab_id2+"");
                map.put("date", date2);
                map.put("remark", remark2);
                map.put("kalendar_id", kalendar_id2+"");
                return map;
            }
        };
        request.setTag("volleypost");
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

}

package com.example.labbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Booking extends AppCompatActivity {

    public static int CHOSEN_MONTH = 0;
    public static final int START_PROGRESSBAR = 0x111;
    public static final int START_ACTIVITY = 0x222;
    private int year_now = new Date().getYear();
    private int month_now = new Date().getMonth()+1;
    private int day_now = new Date().getDay();

    private int tableCol = Booking.getMaxDay(year_now, month_now)+1;
    private int tableRow = 6;

    private String months_after_now[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

    public final String[] col = new String[tableCol+1];

    public final String[] row = {
            "",
            "1-2","3-5","6-7","8-9","10-12"};



    private Button[][] cells = new Button[tableRow][tableCol];
    private Button begin;

    private Button getResult;
    private Button submit;
    private ImageView constellation_image;
    private String[] square;
    private int[][] result = new int[9][9];
    private LinearLayout table_layout;
    private Button previous;

    private LinearLayout main;
    private ProgressBar progressBar;
    private int dataLength = 50;
    private int[] data = new int[dataLength];// 该程序模拟填充长度为1000的数组
    int index = 0;// 数组下标
    int step = 0;// 记录ProgressBar的完成进度
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


        spinner = (Spinner) findViewById(R.id.spinner);
        //数据
        data_list = new ArrayList<String>();

        for (int i = month_now; i < months_after_now.length+1; i++) {
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
                Toast.makeText(Booking.this, month_int, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
              // TODO Auto-generated method stub

            }
        });

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
//                editText.setGravity(Gravity.CENTER);
                each_button.setBackgroundColor(Color.WHITE);
                each_button.setEnabled(true);
                each_button.setHeight(200);
                each_button.setTag(j+"日"+row[i]+"节");

                if (j+1 < day_now) each_button.setVisibility(View.INVISIBLE);

                if (i!=0 && j!=0) {
                    each_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(Booking.this, month_now+"月"+each_button.getTag()+"被点击", Toast.LENGTH_SHORT).show();

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
                each_row_linearOut.addView(each_button);
                each_button.setLayoutParams(lp);
            }

            table_layout.addView(each_row_linearOut);
        }
    }

    public void initCol(){
        this.col[0] = "";
        for (int i = 1; i < this.tableCol; i++) {
            this.col[i] = this.month_now + "月" + i + "日";
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

}

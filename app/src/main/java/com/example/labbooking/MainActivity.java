package com.example.labbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


//import com.example.labbooking.util.HttpRequestor;

// 先不需要看util包中的所有代码
public class MainActivity extends AppCompatActivity {
    private TextView textView;
//    private HttpRequestor httpRequestor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(MainActivity.this, ChooseLab.class);
        startActivity(intent);

    }

}

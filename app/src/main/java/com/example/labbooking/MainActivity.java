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
//        textView = findViewById(R.id.text);
        Intent intent=new Intent(MainActivity.this, ChooseLab.class); startActivity(intent);
        // 获得所有预约信息/获得某一个用户的所有预约信息
        String appointmentJson = "{\n" +
                "    \"state\": 200,\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"date\": \"2021-01-23\",\n" +
                "            \"remark\": \"生化实验\",\n" +
                "            \"is_approved\": 0,\n" +
                "            \"user\": {\n" +
                "                \"id\": 1,\n" +
                "                \"username\": \"zach\",\n" +
                "                \"name\": \"杨志恒\",\n" +
                "                \"password\": \"123456\",\n" +
                "                \"contact\": \"110\",\n" +
                "                \"no\": \"201802104060\",\n" +
                "                \"is_admin\": \"1\",\n" +
                "                \"idcard\": \"110\"\n" +
                "            },\n" +
                "            \"lab\": {\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"生物实验室\",\n" +
                "                \"no\": \"1\"\n" +
                "            },\n" +
                "            \"kalendar\":  {\n" +
                "                \"id\": 1,\n" +
                "                \"description\": \"1-2\",\n" +
                "                \"no\": \"1\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // 所有实验室信息
        String labJson = "{\n" +
                "    \"state\": 200,\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"name\": \"生物实验室\",\n" +
                "            \"no\": \"1\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"物理实验室\",\n" +
                "            \"no\": \"2\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // 登录成功信息
        String loginSu = "{\n" +
                "    \"state\": 200,\n" +
                "    \"msg\": \"成功\",\n" +
                "    \"data\": {\n" +
                "        \"id\": 1,\n" +
                "        \"username\": \"zach\",\n" +
                "        \"name\": \"杨志恒\",\n" +
                "        \"password\": null,\n" +
                "        \"contact\": \"110\",\n" +
                "        \"no\": \"201802104060\",\n" +
                "        \"is_admin\": \"1\",\n" +
                "        \"idcard\": \"110\"\n" +
                "    }\n" +
                "}";

        // 登录失败信息
        String loginFail = "{\n" +
                "    \"state\": 500,\n" +
                "    \"msg\": \"获取失败\",\n" +
                "    \"data\": 200\n" +
                "}";

//        textView.setText(appointmentJson);
        // 先不需要用,未部署，路径未确定
//        //发送 GET 请求
//        String s=HttpRequestor.sendGet("http://dict.youdao.com/w/%E6%B0%A2/#keyfrom=dict2.top", "");
//
//        //发送 POST 请求
//        String sr=HttpRequestor.sendPost("https://49.234.112.12:8081/lab/login", "username=zach&password=123456");

    }

}

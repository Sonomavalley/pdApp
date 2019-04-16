package com.tedu.pdapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerBtn= (Button) findViewById(R.id.register_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.找到5个输入框
                EditText usernameEt= (EditText) findViewById(R.id.register_username);
                EditText passwordEt= (EditText) findViewById(R.id.register_password);
                EditText confirmPasswordEt= (EditText) findViewById(R.id.register_confirmPassword);
                EditText emailEt= (EditText) findViewById(R.id.register_email);
                EditText phoneEt= (EditText) findViewById(R.id.register_phone);
                //2.从输入框得到用户输入的内容
                String username=usernameEt.getText().toString();
                String password=passwordEt.getText().toString();
                String confirmPassword=confirmPasswordEt.getText().toString();
                String email=emailEt.getText().toString();
                String phone=phoneEt.getText().toString();
                //3.数据检查
                if(password.equals(confirmPassword)){
                    //4.用volley联网把数据发给服务器

                    //4.1拼出url
                    String url=PDApplication.SERVER_IP+"/user/register.html";
                    //4.2准备发送的数据
                    url=url+"?username="+username+"&password="+password+"&phone="+phone+"&email="+email;


                    //4.3发送
                    MySuccessListener mySuccessListener=new MySuccessListener();
                    MyErrorListener myErrorListener=new MyErrorListener();
                    StringRequest stringRequest=new StringRequest(url,mySuccessListener,myErrorListener);
                    RequestQueue requestQueue= Volley.newRequestQueue(RegisterActivity.this);
                    requestQueue.add(stringRequest);






                }
            }
        });


    }
    //内部类
    //联网成功后回调
    class MySuccessListener implements Response.Listener<String>{
        @Override
        public void onResponse(String s){
            //4.4接收服务器返回的数据
            //4.5显示结果
            ServerResult serverResult= JSON.parseObject(s,ServerResult.class);
            String msg="注册失败";
            if(serverResult.status==200){
                msg="注册成功";
            }
            Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }
    //联网失败后回调
   class MyErrorListener implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(RegisterActivity.this,volleyError.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}

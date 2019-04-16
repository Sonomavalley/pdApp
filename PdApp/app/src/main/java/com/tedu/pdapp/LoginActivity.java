package com.tedu.pdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LoginActivity extends AppCompatActivity {
  String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerBtn= (Button) findViewById(R.id.login_register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        //找到登录按钮
        Button loginBtn= (Button) findViewById(R.id.login_login);
        //设置事件监听
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.找到输入框

                EditText usernameEt= (EditText) findViewById(R.id.login_username);
                EditText passwordEt= (EditText) findViewById(R.id.login_password);
                //2.接收用户输入的内容
                 username=usernameEt.getText().toString();
                String password=passwordEt.getText().toString();
                //3.判断数据
                if((!TextUtils.isEmpty(username))&&(!TextUtils.isEmpty(password))){
                    String url=PDApplication.SERVER_IP+"/user/login.html";
                    url=url+"?username="+username+"&password="+password;
                   MySuccesssListener mySuccesssListener=new MySuccesssListener();
                    MyErrorListenner myErrorListenner=new MyErrorListenner();
                    StringRequest stringRequest=new StringRequest(url,mySuccesssListener,myErrorListenner);
                    RequestQueue requestQueue= Volley.newRequestQueue(LoginActivity.this);
                    requestQueue.add(stringRequest);
                }
                //4.联网把数据发给服务器的controller
                //4.1拼接url
                //4.2准备数据
                //4.3发送
                //4.4接收数据
                //4.5显示结果
            }
        });

    }
    class MySuccesssListener implements Response.Listener<String>{

        @Override
        public void onResponse(String s) {

            //4.4接收服务器返回的数据
            //4.5显示结果
            ServerResult serverResult= JSON.parseObject(s,ServerResult.class);
            String msg="登录失败";
            if(serverResult.status==200){
                msg="登录成功";
                Intent intent=new Intent(LoginActivity.this,MainFragmentActivity.class);
                startActivity(intent);
                UserDAO userDAO=new UserDAO();
                userDAO.insert(LoginActivity.this,username);
            }
            Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
        }
    }
    class MyErrorListenner implements Response.ErrorListener{

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            System.out.println("联网出错");
        }
    }
}

package com.tedu.pdapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToLoginThread toLoginThread=new ToLoginThread();
        toLoginThread.start();
    }
    class ToLoginThread extends Thread{
        @Override
        public void run() {
            super.run();
            try{
                sleep(3000);
                //查询数据库，确认用户是否登录
                //若登录，直接等到mainFragmentActivity
                //若未登录，跳到Login
                Class toActivity=LoginActivity.class;
                UserDAO userDAO=new UserDAO();
                int row=userDAO.query(MainActivity.this);
                if(row>=1){
                    toActivity=MainFragmentActivity.class;
                }

                Intent intent=new Intent(MainActivity.this,toActivity);
                startActivity(intent);
            }catch(Exception e){

            }
        }
    }
}

package com.tedu.pdapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

/**
 * Created by cgb on 12/26/2018.
 */
//管理首页，消息，我
    //activity必须在AndroidManifest.xml中注册
public class MainFragmentActivity extends FragmentActivity{
    IndexFragment indexFragment;//商城首页
    //indexfragment,messagefragment,mefragment
    Fragment[] fragments=new Fragment[3];
    //indexBtn,MessageBtn,meBtn
    Button[] buttons=new Button[3];
    //正在显示的fragment在fragments数组的下标
    int currentFragmentIndex=0;
    //单击的button的下标
    int clickedBtnIndex=0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载res/layout/main_fragment.xml
        setContentView(R.layout.main_fragment);
        //main_fragment.xml中有一个LinearLayout
        //id是fragment_container
        //显示fragment用的是事务
        //开始事务
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        //动作1：添加indexFragment
        indexFragment=new IndexFragment();
        int containerId=R.id.fragment_container;
        //使用LinearLayout来显示indexFragment
        transaction.add(containerId,indexFragment);
        //动作2：显示indexFragment
        transaction.show(indexFragment);
        //提交事务,前面有两个动作，如果有一个失败，就不提交
        transaction.commit();
       //创建fragment
        MessageFragment messageFragment=new MessageFragment();
        MeFragment meFragment=new MeFragment();
        fragments[0]=indexFragment;
        fragments[1]=messageFragment;
        fragments[2]=meFragment;
        //找出三个按钮
        buttons[0]= (Button) findViewById(R.id.btn_main_fragment_store);
        buttons[1]= (Button) findViewById(R.id.btn_main_fragment_message);
        buttons[2]= (Button) findViewById(R.id.btn_main_fragment_me);
       ButtonListener buttonListener=new ButtonListener();
        for(Button button:buttons){
            button.setOnClickListener(buttonListener);
        }


//让第一个按钮高亮显示
        //把按钮的状态改成selected
        buttons[currentFragmentIndex].setSelected(true);



    }
    class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            //判断单击的是第几个按钮
            switch(v.getId()){
                case R.id.btn_main_fragment_store:clickedBtnIndex=0;
                    break;
                case R.id.btn_main_fragment_message:clickedBtnIndex=1;
                    break;
                case R.id.btn_main_fragment_me:clickedBtnIndex=2;
                    break;
            }
            //判断单击的是不是当前按钮
            if(clickedBtnIndex!=currentFragmentIndex){
                //开启事务
                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                //动作1：隐藏以前的fragment
                Fragment hideFragment=fragments[currentFragmentIndex];
                transaction.hide(hideFragment);
                //动作2：添加要显示的fragment
                Fragment showFragment=fragments[clickedBtnIndex];
                //第一次点按钮，添加对应的fragment
                //以后点，不添加了
                if(showFragment.isAdded()==false){
                    //容器是LinearLayout
                    int containerId=R.id.fragment_container;
                    transaction.add(containerId,showFragment);
                }
                //动作3：显示fragment
                transaction.show(showFragment);

                //提交事务
                //前面的动作都成功了，才提交，有一个出错，就不会提交
                transaction.commit();
                buttons[currentFragmentIndex].setSelected(false);
                buttons[clickedBtnIndex].setSelected(true);
                //显示的fragment已经变了
                currentFragmentIndex=clickedBtnIndex;
            }

        }
    }
}

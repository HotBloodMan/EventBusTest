package com.ljt.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*
* EventBus3.0 如果没有接收到消息，则换一个高版本的手机测试
* 在Android 4.0手机上收不到post来的消息，在Android 7.0手机
* 上就能收到消息。
* */
public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private TextView mText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_main);
        mText = (TextView) findViewById(R.id.tv_main);
        mText.setText("今天是星期五");
        swichActivity();
        Log.d("TAG","---->>>One onCreate");
    }
    private void swichActivity() {
        if(!EventBus.getDefault().isRegistered(MainActivity.this)){
            EventBus.getDefault().register(this);
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG","---->>>One onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG","---->>>One onResume()");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent){
        Log.d("TAG","---->>>onMoonEvent()");
        mText.setText("接收："+messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            Log.d("TAG","---->>>One onDestroy");
            EventBus.getDefault().unregister(this);
        }
    }
}

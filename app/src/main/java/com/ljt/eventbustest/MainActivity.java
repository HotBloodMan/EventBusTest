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
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("TAG","---->>>One onResume()");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
//    @Subscribe(sticky = true)
    public void onEvent(MessageEvent messageEvent){
        mText.setText(messageEvent.getMessage());
        Log.d("TAG","---->>>One  Event");
        Toast.makeText(MainActivity.this, messageEvent.getMessage(), Toast.LENGTH_SHORT).show();
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

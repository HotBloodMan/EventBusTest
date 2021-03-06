package com.ljt.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    Button btnSecond;
    private Button btnThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnSecond= (Button) findViewById(R.id.btn_second);
        btnThird = (Button) findViewById(R.id.btn_third);
        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                finish();
            }
        });
        jumpActivity();
    }

    private void jumpActivity() {
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("Hello World SecondActivity"));
                finish();
//                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
//                startActivity(intent);
                Log.d("TAG","---->>>Second");
            }
        });
    }
}

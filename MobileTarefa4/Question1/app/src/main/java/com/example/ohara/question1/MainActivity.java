package com.example.ohara.question1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.Delayed;

public class MainActivity extends Activity {

    private Button    start;
    private TextView  count;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start   = (Button)   findViewById(R.id.btnStart);
        count   = (TextView) findViewById(R.id.txtCount);
        handler = new Handler();
    }

    public void start (View view) {
        start.setEnabled(false);
        toDoCount();
    }

    private void toDoCount () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 9; i <= 0; i++ ) {
                            count.setText(i);
                        }
                    }
                });
            }
        }).start();
    }
}

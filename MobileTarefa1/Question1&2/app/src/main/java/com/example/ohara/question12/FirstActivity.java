package com.example.ohara.question12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {

    private static final String Tag = "MyApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Log.i(Tag, "Information Menssage");
        Log.d(Tag, "Debug Menssage");
        Log.w(Tag, "Warning Menssage");
        Log.e(Tag, "Error Menssage");
        Log.v(Tag, "Verbose Menssage");
    }

    public void onClick (View view) {
        Intent intent = new Intent (this, SecondActivity.class);
        startActivity(intent);
    }
}

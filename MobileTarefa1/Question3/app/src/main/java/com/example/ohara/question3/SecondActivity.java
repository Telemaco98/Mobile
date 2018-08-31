package com.example.ohara.question3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.lang.String;

public class SecondActivity extends Activity {

    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btn2 = (Button) findViewById(R.id.btn2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        value = bundle.getString("value");

        value = changeValue(btn2, value);
    }

    private String changeValue (Button button, String value) {
        int intValue = Integer.valueOf(value);
        intValue++;
        String stringValue = Integer.toString(intValue);
        button.setText(stringValue);

        return stringValue;
    }

    public void onClick (View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent ();
        intent.putExtra("valueBack", value);
        setResult(RESULT_OK, intent);
        finish();
    }
}

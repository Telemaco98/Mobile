package com.example.ohara.question3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends Activity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn = (Button) findViewById(R.id.btn1);
    }

    public void onClick (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        String btnText = btn.getText().toString();
        intent.putExtra("value", btnText);
        startActivityForResult(intent, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String value = data.getStringExtra ("valueBack" );
        changeValue(btn, value);
    }

    private void changeValue (Button button, String value) {
        int intValue = Integer.valueOf(value);
        intValue++;
        String stringValue = Integer.toString(intValue);
        button.setText(stringValue);
    }
}

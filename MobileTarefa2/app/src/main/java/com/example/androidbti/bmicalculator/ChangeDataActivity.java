package com.example.androidbti.bmicalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeDataActivity extends Activity {

    private EditText editText;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);

        TextView textX = (TextView) findViewById(R.id.textX);
        editText = (EditText) findViewById(R.id.editText);

        Intent it = getIntent();
        bundle = it.getExtras();

        changeTextX(textX, bundle);
    }

    private void changeTextX (TextView textX, Bundle bundle) {
        String x = bundle.getString("tipo");
        textX.setText(x + ": ");
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.btnCancelar:
                setResult(RESULT_CANCELED);
                finish();
                break;
            case R.id.btnAlterar:
                Intent intent = new Intent();
                intent.putExtra("value", editText.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}

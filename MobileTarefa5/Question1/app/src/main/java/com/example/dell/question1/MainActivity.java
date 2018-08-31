package com.example.dell.question1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements ButtonsFragment.OnButtonListenner {
    private ColorsFragment colorsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorsFragment = (ColorsFragment) getFragmentManager().findFragmentById(R.id.fragColors);
    }

    @Override
    public void onButtonClick(Button button) {
        colorsFragment.changeColor(button.getText().toString());
    }
}
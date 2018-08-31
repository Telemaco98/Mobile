package com.example.dell.question2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity implements IncrementFragment.OnClickButton {
    private LeftCountFragment leftFrag;
    private RightCountFragment rightFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftFrag  = (LeftCountFragment)  getFragmentManager().findFragmentById(R.id.frag_left);
        rightFrag = (RightCountFragment) getFragmentManager().findFragmentById(R.id.frag_right);

        if (savedInstanceState != null) {
            leftFrag.textView.setText(savedInstanceState.getString("leftFrag"));
            rightFrag.textView.setText(savedInstanceState.getString("rightFrag"));
        }
    }

    @Override
    public void onClickIncrement() {
        leftFrag.increment();
        rightFrag.increment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("leftFrag", leftFrag.textView.getText().toString());
        outState.putString("rightFrag", rightFrag.textView.getText().toString());

        super.onSaveInstanceState(outState);
    }
}

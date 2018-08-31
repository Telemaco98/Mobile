package com.android.ohara.telemaco.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.ohara.telemaco.R;

public class AboutUsActivity extends AppCompatActivity {
    private TextView aboutTheToll;
    private Button   btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about_us );

        getSupportActionBar().hide();

        aboutTheToll = (TextView) findViewById( R.id.txt_about_tool );
        btnSend      = (Button) findViewById( R.id.btn_send);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/SF_Distant_Galaxy.ttf");
        btnSend.setTypeface( typeface );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
            case R.id.btn_face:
                //TODO
                break;
            case R.id.btn_twitter:
                //TODO
                break;
            case R.id.btn_insta:
                //TODO
                break;
            case R.id.btn_send:
                //TODO
                break;
        }
    }
}
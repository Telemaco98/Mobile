package com.android.ohara.question1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements TimePicker.OnTimeSet, DatePicker.OnDateSet {
    private TextView text;
    private String dateString = "";
    private String timeString = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.textDateHour);
        startText();
    }

    public void openDate (View view) {
        DatePicker datePicker = new DatePicker();
        datePicker.show(getFragmentManager(), "datePicker");
    }

    public void openHour (View view) {
        TimePicker timePicker = new TimePicker();
        timePicker.show(getFragmentManager(), "timePicker");
    }

    public void startText () {
        long date = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateString = dateFormat.format(date);

        SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
        timeString = hourFormat.format(date);

        text.setText(dateString + " " + timeString);
    }

    public void changeText () {
        text.setText(dateString + " " + timeString);
    }

    @Override
    public void setTime(String time) {
        this.timeString = time;
        changeText();
    }


    @Override
    public void setDate(String date) {
        this.dateString = date;
        changeText();
    }
}

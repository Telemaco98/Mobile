package com.example.dell.question2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;

public class LeftCountFragment extends Fragment {
    public TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count_left, container, false);

        textView = (TextView) view.findViewById(R.id.txt_left);

        return view;
    }

    public void increment () {
        String value = textView.getText().toString();
        int valueInt = Integer.parseInt(value) + 1;
        textView.setText(Integer.toString(valueInt));
    }
}

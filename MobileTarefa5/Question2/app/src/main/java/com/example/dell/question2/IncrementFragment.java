package com.example.dell.question2;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.Nullable;

public class IncrementFragment extends Fragment implements View.OnClickListener {
    private OnClickButton listenner;
    private Button button;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof OnClickButton)) {
            throw new RuntimeException("The activity doesn't implements the OnClickListenner interface!");
        }

        listenner = (OnClickButton) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_increment, container, false);

        button = (Button) view.findViewById(R.id.btnIncrement);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (listenner != null)
            listenner.onClickIncrement();
    }

    public interface OnClickButton {
        public void onClickIncrement();
    }
}
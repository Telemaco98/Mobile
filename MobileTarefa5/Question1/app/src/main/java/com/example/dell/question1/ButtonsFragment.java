package com.example.dell.question1;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.Nullable;

public class ButtonsFragment extends Fragment implements View.OnClickListener {
    private OnButtonListenner listenner;
    private Button btnBlack;
    private Button btnBlue;
    private Button btnGreen;
    private Button btnRed;
    private Button btnYellow;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(context instanceof OnButtonListenner)) {
            throw new RuntimeException("The activity doesn't implemente the OnButtonListenner interface!");
        }
        listenner = (OnButtonListenner) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.buttons_fragment, container, false);

        btnBlack  = view.findViewById(R.id.btnBlack);
        btnBlue   = view.findViewById(R.id.btnBlue);
        btnGreen  = view.findViewById(R.id.btnGreen);
        btnRed    = view.findViewById(R.id.btnRed);
        btnYellow = view.findViewById(R.id.btnYellow);

        btnBlack.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnRed.setOnClickListener(this);
        btnYellow.setOnClickListener(this);

        return view;
    }

    public void onClick (View view) {
        if (listenner != null) {
            switch (view.getId()) {
                case R.id.btnBlack:
                    listenner.onButtonClick (btnBlack);
                    break;
                case R.id.btnBlue:
                    listenner.onButtonClick (btnBlue);
                    break;
                case R.id.btnGreen:
                    listenner.onButtonClick (btnGreen);
                    break;
                case R.id.btnRed:
                    listenner.onButtonClick (btnRed);
                    break;
                case R.id.btnYellow:
                    listenner.onButtonClick (btnYellow);
                    break;
            }
        }
    }

    public interface OnButtonListenner {
        public void onButtonClick (Button button);
    }
}

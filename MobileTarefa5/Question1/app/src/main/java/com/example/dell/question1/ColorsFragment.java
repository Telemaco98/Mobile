package com.example.dell.question1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.jetbrains.annotations.Nullable;

public class ColorsFragment extends Fragment {
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.colors_fragment, container, false);

        imageView = (ImageView) view.findViewById(R.id.imgColors);

        return view;
    }

    public void changeColor (String buttonTxt) {
        switch (buttonTxt){
            case "Preto":
                imageView.setImageResource(R.color.black);
                break;
            case "Azul":
                imageView.setImageResource(R.color.blue);
                break;
            case "Verde":
                imageView.setImageResource(R.color.green);
                break;
            case "Vermelho":
                imageView.setImageResource(R.color.red);
                break;
            default:
                imageView.setImageResource(R.color.yellow);
        }

    }
}

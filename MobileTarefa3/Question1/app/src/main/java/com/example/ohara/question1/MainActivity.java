package com.example.ohara.question1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText nome;
    private RadioButton fem;
    private RadioButton masc;

    private TextView nomeExibir;
    private TextView sexoExibir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nome = (EditText) findViewById(R.id.editTxtNome);
        fem = (RadioButton) findViewById(R.id.fem);
        masc = (RadioButton) findViewById(R.id.masc);

        nomeExibir = (TextView) findViewById(R.id.txtNomeExibir);
        sexoExibir = (TextView) findViewById(R.id.txtSexoExibir);
    }

    public void exibir (View view) {
        String nomeString = nome.getText().toString();
        nomeExibir.setText(nomeString);

        if(fem.isChecked()) sexoExibir.setText("Feminino");
        if(masc.isChecked()) sexoExibir.setText("Masculino");
    }

    public void limpar (View view) {
        nome.setText("");
        fem.setChecked(true);
        masc.setChecked(false);
        nomeExibir.setText("");
        sexoExibir.setText("");
    }
}

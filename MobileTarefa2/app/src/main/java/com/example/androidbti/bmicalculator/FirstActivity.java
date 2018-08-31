package com.example.androidbti.bmicalculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static java.lang.Math.pow;

public class FirstActivity extends Activity {

    private Button btnPeso;
    private Button btnAltura;
    private TextView peso;
    private TextView altura;
    private TextView resultado;
    private Intent intent;
    private ImageView backgroundRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnPeso = (Button) findViewById(R.id.btnPeso);
        btnAltura = (Button) findViewById(R.id.btnAltura);
        peso = (TextView) findViewById(R.id.txtValuePeso);
        altura = (TextView) findViewById(R.id.txtValueAltura);
        resultado = (TextView) findViewById(R.id.textResultado);
        backgroundRes = (ImageView) findViewById(R.id.backgroundRes);
    }

    public void onClick (View view) {
        intent = new Intent(this, ChangeDataActivity.class);
        switch (view.getId()) {
            case R.id.btnPeso:
                String peso = btnPeso.getText().toString();
                intent.putExtra("tipo", "Peso");
                startActivityForResult(intent, 5);
                reset();
                break;
            case R.id.btnAltura:
                String altura = btnAltura.getText().toString();
                intent.putExtra("tipo", "Altura");
                startActivityForResult(intent, 5);
                reset();
                break;
            case R.id.btnCalcular:
                calcular();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 5 && resultCode == RESULT_OK) {
            Bundle bundle = intent.getExtras();
            String back = data.getStringExtra("value");

            if ((bundle.getString("tipo")).equals("Peso")) {
                if(!back.equals(""))
                    peso.setText(back);
            } else {
                if(!back.equals(""))
                    altura.setText(back);
            }
        }
    }

    private void calcular () {
        Double pesoDouble = Double.parseDouble(peso.getText().toString());
        Double alturaDouble = Double.parseDouble(altura.getText().toString());

        if (pesoDouble == 0 || alturaDouble == 0) {
            Toast.makeText(this, "É necessário fornecer todos os dados para fazer o calculo do IMC.", Toast.LENGTH_SHORT).show();
        } else {
            Double resultadoDouble = (pesoDouble)/(pow(alturaDouble, 2));
            Pair <String, Integer> classif = classificacao(resultadoDouble);

            DecimalFormat df = new DecimalFormat("#.##");
            String resultadoString = "Resultado: " + df.format(resultadoDouble) + "\n"
                                    + "Você está classificado como: \n"
                                    + classif.first;

            resultado.setText(resultadoString);
            backgroundRes.setImageResource(classif.second);
        }
    }

    @SuppressLint("ResourceAsColor")
    private Pair<String, Integer> classificacao (Double resultadoD) {
        Color color = new Color();

        if (resultadoD < 16) {
            color.rgb(255, 51, 51);
            return new Pair<String, Integer> ("Magreza grave", R.color.grauGrave);
        }
        if (resultadoD >= 16 && resultadoD < 17) {
            return new Pair<String, Integer> ("Magreza moderada", R.color.grauModerado);
        }
        if (resultadoD >= 17 && resultadoD < 18.5) {
            return new Pair<String, Integer> ("Magreza leve", R.color.grauLeve);
        }
        if (resultadoD >= 18.5 && resultadoD < 25) {
            return new Pair<String, Integer> ("Saudável", R.color.grauIdeal);
        }
        if (resultadoD >= 25 && resultadoD < 30) {
            return new Pair<String, Integer> ("Sobrepeso", R.color.grauLeve);
        }
        if (resultadoD >= 30 && resultadoD < 35) {
            return new Pair<String, Integer> ("Obesidade Grau I", R.color.grauModerado);
        }

        if (resultadoD >= 35 && resultadoD < 40) {
            return new Pair<String, Integer> ("Obesidade Grau II (severa) ", R.color.grauGrave);
        }

        return new Pair<String, Integer> ("Obesidade Grau III (Mórbida)", R.color.grauSuperGrave);
    }

    @SuppressLint("ResourceAsColor")
    private void reset () {
        resultado.setText(R.string.semResultado);
        backgroundRes.setImageResource(R.color.colorText);
    }
}
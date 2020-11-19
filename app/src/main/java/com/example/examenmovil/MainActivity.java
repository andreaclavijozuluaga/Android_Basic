package com.example.examenmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    RadioButton jbtnbandejapaisa, jbtnpastabolognesa, jbtnsanchochobagre, jbtngaseosa, jbtnjugo;
    CheckBox jcheckPropina;
    EditText jettotal, jetcant;

    private double total = 0;
    private double comidaTotal = 0;
    private double bebidaTotal = 0;
    private double propina = 0;
    private double cantidad = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jbtnbandejapaisa = findViewById(R.id.rbtnbandejapaisa);
        jbtnpastabolognesa = findViewById(R.id.rbtnbolonegsa);
        jbtnsanchochobagre = findViewById(R.id.rbtnsancocho);
        jettotal = findViewById(R.id.ettotal);
        jbtngaseosa = findViewById(R.id.rbtngaseosa);
        jbtnjugo = findViewById(R.id.rbtnjugo);
        jcheckPropina = findViewById(R.id.cbpropina);
        jetcant = findViewById(R.id.etcant);

        jetcant.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable cantidad) {
            }

            @Override
            public void beforeTextChanged(CharSequence cantidad, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence cantidad, int start, int before, int count) {
                if (cantidad.length() > 0) {
                    cant_p(Integer.parseInt(cantidad.toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese cantidad personas", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jettotal.setText(String.valueOf(total));
    }

    public void elegir_plato(View v) {
        comidaTotal = 0;

        jbtnbandejapaisa.setChecked(false);
        jbtnpastabolognesa.setChecked(false);
        jbtnsanchochobagre.setChecked(false);

        int platoId = v.getId();

        switch (platoId) {
            case R.id.rbtnbandejapaisa:
                comidaTotal += 24000;
                break;
            case R.id.rbtnbolonegsa:
                comidaTotal += 30000;
                break;
            case R.id.rbtnsancocho:
                comidaTotal += 28000;
                break;
        }

        RadioButton checkedPlato = findViewById(platoId);
        checkedPlato.setChecked(true);

        total = (comidaTotal + bebidaTotal + propina) * cantidad;

        jettotal.setText(String.valueOf(total));
    }

    public void elegirBebida(View v) {
        bebidaTotal = 0;

        jbtngaseosa.setChecked(false);
        jbtnjugo.setChecked(false);

        int bebidaId = v.getId();

        switch (bebidaId) {
            case R.id.rbtngaseosa:
                bebidaTotal += 6000;
                break;
            case R.id.rbtnjugo:
                bebidaTotal += 9000;
                break;
        }

        RadioButton checkedBebida = findViewById(bebidaId);

        checkedBebida.setChecked(true);

        total = (comidaTotal + bebidaTotal + propina) * cantidad;

        jettotal.setText(String.valueOf(total));
    }

    public void propina(View v) {
        propina = 0;

        if (jcheckPropina.isChecked()) {
            propina = total * 0.10;
        } else {
            propina = 0;
        }

        total = (comidaTotal + bebidaTotal + propina) * cantidad;

        jettotal.setText(String.valueOf(total));
    }

    public void cant_p(Integer cantidad) {
        total = (comidaTotal + bebidaTotal + propina) * cantidad;
        jettotal.setText(String.valueOf(total));
    }
}
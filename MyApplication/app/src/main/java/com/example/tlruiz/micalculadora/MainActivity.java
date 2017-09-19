package com.example.tlruiz.micalculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Se crean las variables
    EditText num1, num2;
    Button suma , resta , multip , dividi;
    TextView Resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se llaman los objetos del layout
        num1 = (EditText) findViewById(R.id.num1);
        num2 = (EditText) findViewById(R.id.num2);

        suma = (Button) findViewById(R.id.sumar);
        resta = (Button) findViewById(R.id.restar);
        multip = (Button) findViewById(R.id.multiplicar);
        dividi = (Button) findViewById(R.id.dividir);
        Resultado= (TextView) findViewById(R.id.resultado);

        //Se activan los listeners para los botones
        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
        multip.setOnClickListener(this);
        dividi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // Oculta el teclado al pulsar un boton
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(this.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //Verifica que existen los dos valores a operar
        if(num1.getText().toString().matches("") || num2.getText().toString().matches("")) {
            //Mensaje de error si falta algun numero
            Toast.makeText(this, R.string.error_numbers_value, Toast.LENGTH_SHORT).show();
        }else{
            //Trae los valores de los editview y los vuelve enteros
            int entero1 = Integer.parseInt(num1.getText().toString());
            int entero2 = Integer.parseInt(num2.getText().toString());

            String resp = "";

            //Selecciona la accion a realizar segun el boton pulsado
            switch (v.getId()) {
                case R.id.sumar:
                    resp = (entero1 + entero2) + "";
                    break;
                case R.id.restar:
                    resp = (entero1 - entero2) + "";
                    break;
                case R.id.multiplicar:
                    resp = (entero1 * entero2) + "";
                    break;
                case R.id.dividir:
                    if(entero2 != 0){
                        resp = ( Math.round( (entero1 * 100 / entero2)) / 100.0) + "";
                    }else{
                        //Devuelve error si se ha introducido cero al divisor
                        Toast.makeText(this, R.string.error_division_value, Toast.LENGTH_SHORT).show();
                        resp = "error";
                    }
                    break;
            }
            Resultado.setText(resp);
        }
    }
}

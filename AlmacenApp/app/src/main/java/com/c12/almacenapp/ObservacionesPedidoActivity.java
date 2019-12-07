package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ObservacionesPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones_pedido);
        EditText observaciones=findViewById(R.id.campoObservaciones);
        Button confirmar = findViewById(R.id.pedidoConfirmar);

    }
}

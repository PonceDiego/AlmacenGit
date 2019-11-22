package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ArticuloEspecificoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articulo_especifico);
        TextView textView= findViewById(R.id.textView);
        textView.setText(BusquedaArticuloActivity.nombreArticuloQR);
    }
}

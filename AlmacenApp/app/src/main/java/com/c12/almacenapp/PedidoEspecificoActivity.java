package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PedidoEspecificoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_especifico);

        String id =getIntent().getStringExtra("pedidoId");
        String fecha=getIntent().getStringExtra("pedidoFecha");
        String usuario=getIntent().getStringExtra("pedidoUsuario");
        String estado=getIntent().getStringExtra("pedidoEstado");
        String observaciones=getIntent().getStringExtra("pedidoObservaciones");

    }
}

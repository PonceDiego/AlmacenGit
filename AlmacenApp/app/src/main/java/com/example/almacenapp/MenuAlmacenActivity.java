package com.example.almacenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuAlmacenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);

        Button btn5 = findViewById(R.id.pedidoNuevo);
        Button btn6 = findViewById(R.id.articuloNuevo);
        Button proveedorNuevo= findViewById(R.id.proveedorNuevo);
        Button buscarArticulo = findViewById(R.id.buscarArticulo);
        Button listaArticulos= findViewById(R.id.listaArticulos);
        Button listaProveedores= findViewById(R.id.listaProveedores);

//        btn5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MenuAlmacenActivity.this, PedidoActivity.class));
//            }
//        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this, ArticuloActivity.class));
            }
        });
        proveedorNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this, ProveedorActivity.class));
            }
        });
        buscarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this,BusquedaArticuloActivity.class ));

            }
        });
        listaArticulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this,ListaArticulosActivity.class));
            }
        });
        listaProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this,ListaProveedoresActivity.class));
            }
        });
    }
}

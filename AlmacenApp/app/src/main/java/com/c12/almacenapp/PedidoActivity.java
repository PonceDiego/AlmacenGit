package com.c12.almacenapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.data.DataBufferObserverSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;

public class PedidoActivity  extends AppCompatActivity {
    ArrayList<String> nombreArts= new ArrayList<>();
    ArrayList<String> idArts= new ArrayList<>();
    private SearchableSpinner articulo;
    private  EditText cantidad;
    private int contador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_pedido);

        MenuAlmacenActivity.IP=getString(R.string.ipServidor);
        contador = 0;


        articulo = findViewById(R.id.spinner3);
        cantidad= findViewById(R.id.cantidad);
        Button agregar= findViewById(R.id.pedidoAgregar);
        Button siguiente=findViewById(R.id.pedidoSiguiente);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(articulo.getSelectedItem()!=null){
                    if(cantidad.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),"Seleccione una cantidad válida",Toast.LENGTH_SHORT).show();
                    }else{
                        agregarFila();
                    }
                }else{ Toast.makeText(getApplicationContext(),"Seleccione un artículo",Toast.LENGTH_SHORT).show();


                }
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contador>=1){
                    startActivity(new Intent(PedidoActivity.this, ObservacionesPedidoActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Seleccione al menos un artículo a pedir",Toast.LENGTH_SHORT).show();
                }
            }
        });


        try{
            JSONArray json = MenuAlmacenActivity.listaArticulos();

            for(int i= 0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre=jsonObject.get("nombre").toString();
                String idArt=jsonObject.get("articuloId").toString();
                nombreArts.add(nombre);
                idArts.add(idArt);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nombreArts);
        articulo.setAdapter(adapter);
    }
    private void agregarFila(){
        TableLayout tl = findViewById(R.id.tablaArticulosPorPedir);
        String nombreArt= articulo.getSelectedItem().toString();
        String cantidadA = cantidad.getText().toString();
        TableRow tr= new TableRow(this);
        TextView tvNombre = new TextView(this);
        TextView tvCantidad= new TextView(this);
        tvCantidad.setText(cantidadA);
        tvCantidad.setTextSize(18);
        tvCantidad.setPadding(5,5,5,5);
        tvNombre.setText(nombreArt);
        tvNombre.setTextSize(18);
        tvNombre.setPadding(5,5,5,5);
        tr.addView(tvCantidad);
        tr.addView(tvNombre);

        TextView x= new TextView(this);
        x.setText("X");
        x.setTextColor(Color.RED);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tl.removeView(tr);
                contador--;
            }
        });
        tr.addView(x);
        tl.addView(tr);
        contador++;
    }
}

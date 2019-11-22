package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;

public class BusquedaArticuloActivity extends AppCompatActivity {
    public static String TAG= "BusquedaArticuloActivity";
    static ArrayList<String> articulosNombres= new ArrayList<>();
    static ArrayList<String> articulosStock= new ArrayList<>();
    private SearchableSpinner listaArticulos;
    public static String nombreArticuloQR;
    public static int largoDelNombre=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_articulo);
        listaArticulos= findViewById(R.id.articulosSpinner);
        Button aceptar= findViewById(R.id.buscarArticuloEspecifico);
        Button qr = findViewById(R.id.qrScan);
        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusquedaArticuloActivity.this,CameraPreviewToQrActivity.class));
            }
        });

        try{

            String url = getString(R.string.ipServidor)+"ListaArticulosAndroid";
            JSONArray json = new JSONArray(readUrl(url));
            articulosNombres.clear();
            articulosStock.clear();

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("nombre").toString();
                String stock = jsonObject.get("stock").toString();
                articulosNombres.add(nombre);
                articulosStock.add(stock);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: inflar searchableSpinner con los datos del json.
       listaArticulos.setAdapter(new ArrayAdapter<String>(BusquedaArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,articulosNombres));
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaArticulos.getSelectedItem()!=null){

                    nombreArticuloQR=listaArticulos.getSelectedItem().toString();
                    startActivity(new Intent(BusquedaArticuloActivity.this,ArticuloEspecificoActivity.class));
                }else{
                    Toast.makeText(getApplicationContext(),"Por favor seleccione un artículo",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private static String readUrl(String urlString) throws Exception {
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer = new StringBuffer();
                int read;
                char[] chars = new char[1024];
                while ((read = reader.read(chars)) != -1)
                    buffer.append(chars, 0, read);

                return buffer.toString();
            } finally {
                if (reader != null)
                    reader.close();
            }
        }
}

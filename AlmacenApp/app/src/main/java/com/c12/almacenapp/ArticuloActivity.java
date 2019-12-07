package com.c12.almacenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;


public class ArticuloActivity extends AppCompatActivity {

    public static String nombreArticulo;
    public static String costoArticulo;
    public static String stockArticulo;
    public static String stockMinArticulo;
    static ArrayList<String> nombresCategorias=new ArrayList<>();
    static ArrayList<String> nombresSubcategorias=new ArrayList<>();
    static ArrayList<String> nombresProveedores=new ArrayList<>();

    private static String categoriaSeleccionada;


    final ArrayList<String> idCategorias=new ArrayList<>();
    final ArrayList<String> idSubcategorias=new ArrayList<>();
    final ArrayList<String> idProveedores=new ArrayList<>();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(ArticuloActivity.this,MenuAlmacenActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_articulo);

        final Button articuloAgregar = findViewById(R.id.articuloAgregar);
        final EditText nombreArt = findViewById(R.id.nombreArticulo);
        final EditText costoArt= findViewById(R.id.costoArticulo);
        final EditText stockMinArt= findViewById(R.id.stockMinArticulo);
        final EditText stockArt= findViewById(R.id.stockActualArticulo);
        final SearchableSpinner categorias = findViewById(R.id.categoria);
        final SearchableSpinner subcategorias = findViewById(R.id.subcategoria);
        final SearchableSpinner proveedores = findViewById(R.id.proveedor);


        //Se trae la lista de categorias de la api y se asignan esos valores al spinner de categorias.
            nombresCategorias=new ArrayList<String>();
        try{
            for (int i=0;i<MenuAlmacenActivity.jsonCategorias.length();i++){
                JSONObject jsonObject= MenuAlmacenActivity.jsonCategorias.getJSONObject(i);
                String nombre = jsonObject.get("nombre").toString();
                nombresCategorias.add(nombre);
                idCategorias.add(jsonObject.get("categoriaId").toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        categorias.setAdapter(new ArrayAdapter<String>(ArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,nombresCategorias));
        subcategoriasAdapterNoCat();
        subcategorias.setAdapter(new ArrayAdapter<String>(ArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,nombresSubcategorias));


        categorias.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                subcategoriaAdapter(position);
                subcategorias.setAdapter(new ArrayAdapter<String>(ArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,nombresSubcategorias));

            }

            @Override
            public void onNothingSelected() {
                articuloAgregar.setActivated(false);

            }
        });



        subcategorias.setAdapter(new ArrayAdapter<String>(ArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,nombresSubcategorias));

        subcategorias.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(View view, int position, long id) {
                articuloAgregar.setActivated(true);
            }

            @Override
            public void onNothingSelected() {
                articuloAgregar.setActivated(false);
            }
        });


        //Se cargan los proveedores

        nombresProveedores=new ArrayList<String>();
        JSONArray json = MenuAlmacenActivity.jsonProveedores;
        try{

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("provNombre").toString();
                String id = jsonObject.get("provId").toString();
                idProveedores.add(id);
                nombresProveedores.add(nombre);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        proveedores.setAdapter(new ArrayAdapter<String>(ArticuloActivity.this,android.R.layout.simple_spinner_dropdown_item,nombresProveedores));



        articuloAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nombreArt.getText().toString().equals("")) {
                    nombreArticulo = nombreArt.getText().toString();
                    String proveedorId = null;
                    String subcategoriaId = null;
                    if (!stockMinArt.getText().toString().equals("")) {
                        stockMinArticulo = stockMinArt.getText().toString();
                        if (!stockArt.getText().toString().equals("")) {
                            stockArticulo = stockArt.getText().toString();
                            if (!costoArt.getText().toString().equals("")) {
                                costoArticulo = costoArt.getText().toString();
                                if(categorias.getSelectedPosition()!=-1){
                                    if(subcategorias.getSelectedPosition()!=-1){
                                        subcategoriaId = idSubcategorias.get(subcategorias.getSelectedPosition());
                                        proveedorId = idProveedores.get(proveedores.getSelectedPosition());

                                    }
                                }

                            }
                        }
                    }


                    String urlS = getString(R.string.ipServidor) + "NuevoArticulo?" +
                            "inputProveedor=" + proveedorId +
                            "&and="+1+
                            "&inputNombre=" + nombreArticulo +
                            "&inputSMinimo=" + stockMinArticulo +
                            "&inputStock=" + stockArticulo +
                            "&inputCosto=" + costoArticulo +
                            "&inputSub=" + subcategoriaId;
                    URL url = null;
                    try {
                        url = new URL(urlS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    HttpURLConnection urlConnection = null;
                    boolean bandera =false;
                    try {
                        urlConnection = (HttpURLConnection) url.openConnection();
                        urlConnection.setDoOutput(true);
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        Log.e("artAct", urlConnection.getResponseCode()+" <cod");
                        in.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                        bandera=true;

                    } finally {
                        urlConnection.disconnect();
                    }

                    if(bandera){
                        Toast.makeText(getApplicationContext(), "Debe seleccionar una subcategoría válida!", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getApplicationContext(), "Artículo " + nombreArticulo + " cargado con éxito!", Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(new Intent(ArticuloActivity.this, QrActivity.class));
                    }


                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese los datos requeridos",Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    private void subcategoriasAdapterNoCat(){
        try{

            nombresSubcategorias=new ArrayList<String>();

            JSONArray json= MenuAlmacenActivity.jsonSubcategoriasCompleta;

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("subNombre").toString();
                String idSub = jsonObject.get("subId").toString();
                nombresSubcategorias.add(nombre);
                idSubcategorias.add(idSub);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


}

    private void subcategoriaAdapter(int position){
        categoriaSeleccionada=idCategorias.get(position);
        try{

            JSONArray json=MenuAlmacenActivity.subsFiltrada(categoriaSeleccionada);
            nombresSubcategorias=new ArrayList<String>();

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("subNombre").toString();
                String idSub = jsonObject.get("subId").toString();
                nombresSubcategorias.add(nombre);
                idSubcategorias.add(idSub);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}

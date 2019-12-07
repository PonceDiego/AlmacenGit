package com.c12.almacenapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


public class MenuAlmacenActivity extends AppCompatActivity {
    public static JSONArray jsonCategorias;
    public static JSONArray jsonProveedores;
    public static JSONArray jsonSubcategoriasCompleta;
    public static JSONArray jsonArticulos;
    static String IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_almacen);
        IP=getString(R.string.ipServidor);

        try {
            String url = getString(R.string.ipServidor) + "ListaCategorias";
            jsonCategorias = new JSONArray(readUrl(url));
            url = getString(R.string.ipServidor)+"ListaProveedoresAndroid";
            jsonProveedores = new JSONArray(readUrl(url));
            jsonSubcategoriasCompleta = new JSONArray(readUrl( getString(R.string.ipServidor)+"ListaSubcategoriasCompleta"));
            url = getString(R.string.ipServidor)+"ListaArticulosAndroid";
            jsonArticulos = new JSONArray(readUrl(url));

        }catch (Exception e){
            e.printStackTrace();
        }

        Button pedidoNuevo = findViewById(R.id.pedidoNuevo);
        Button misPedidos = findViewById(R.id.listaPedidos);
        Button articuloNuevo = findViewById(R.id.articuloNuevo);
        Button proveedorNuevo = findViewById(R.id.proveedorNuevo);
        Button buscarArticulo = findViewById(R.id.buscarArticulo);
        Button listaArticulos = findViewById(R.id.listaArticulos);
        Button listaProveedores = findViewById(R.id.listaProveedores);

        pedidoNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this, PedidoActivity.class));
            }
        });
        misPedidos.setOnClickListener(v -> startActivity(new Intent(MenuAlmacenActivity.this, MisPedidosActivity.class)));
        articuloNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                startActivity(new Intent(MenuAlmacenActivity.this, BusquedaArticuloActivity.class));

            }
        });
        listaArticulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this, ListaArticulosActivity.class));
            }
        });
        listaProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuAlmacenActivity.this, ListaProveedoresActivity.class));
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
    public static JSONArray subsFiltrada(String seleccionada) throws Exception {
        String extra="ListaSubcategorias?inputCat="+seleccionada;
        JSONArray json = new JSONArray(readUrl(IP+extra));
        return json;
    }
    public static JSONArray listaPedidoIndividual(String usuario) throws  Exception{
        String extra="ListaPedidosDe?username="+usuario+"&and=1";
        JSONArray jsonMisPedidos= new JSONArray(readUrl(IP+extra));
        return jsonMisPedidos;
    }
    public static JSONArray listaArticulos()throws Exception{
        return new JSONArray(readUrl(IP+"ListaArticulosAndroid"));
    }
}
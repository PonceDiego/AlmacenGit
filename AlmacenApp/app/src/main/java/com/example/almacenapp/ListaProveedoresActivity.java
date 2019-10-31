package com.example.almacenapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ListaProveedoresActivity extends AppCompatActivity {
    static ArrayList<String> proveedoresNombres= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_proveedores);
        try{

            String url = getString(R.string.ipServidor)+"ListaProveedoresAndroid";
            JSONArray json = new JSONArray(readUrl(url));
            proveedoresNombres.clear();

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("provNombre").toString();
                proveedoresNombres.add(nombre);

                Log.v("nombre", nombre);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        iniciarTabla();

    }
    private void iniciarTabla(){
        TableLayout tl = (TableLayout) findViewById(R.id.proveedoresLista);
        for (int i = 0; i < proveedoresNombres.size();i++){
            TableRow tr= new TableRow(this);

            TextView nombre = new TextView(this);
            nombre.setBackground(getDrawable(R.drawable.border));
            nombre.setTextSize(18);
            nombre.setPadding(5,5,5,5);

            nombre.setText(proveedoresNombres.get(i).toString());

            tr.addView(nombre);

            tl.addView(tr);
        }

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

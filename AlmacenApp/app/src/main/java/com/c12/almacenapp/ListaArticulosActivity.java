package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ListaArticulosActivity extends AppCompatActivity {
    static ArrayList<String> articulosNombres= new ArrayList<>();
    static ArrayList<String> articulosStock= new ArrayList<>();
//    static ArrayList<String> articulosSubcategoria= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_articulos);
        try{

            String url = getString(R.string.ipServidor)+"ListaArticulosAndroid";
            JSONArray json = new JSONArray(readUrl(url));
            articulosNombres.clear();
            articulosStock.clear();
//            articulosSubcategoria.clear();

            for (int i=0;i<json.length();i++){
                JSONObject jsonObject= json.getJSONObject(i);
                String nombre = jsonObject.get("nombre").toString();
                String stock = jsonObject.get("stock").toString();
//                String subCategoria= jsonObject.get("subcategoria").toString();
                articulosNombres.add(nombre);
                articulosStock.add(stock);
//                articulosSubcategoria.add(subCategoria);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        iniciarTabla();

    }
    private void iniciarTabla(){
        TableLayout tl = (TableLayout) findViewById(R.id.articulosTabla);
        for (int i = 0; i < articulosNombres.size();i++){
            TableRow tr= new TableRow(this);

            TextView nombreArt = new TextView(this);
            nombreArt.setBackground(getDrawable(R.drawable.border));
            nombreArt.setTextSize(18);
            nombreArt.setPadding(5,5,5,5);
            TextView stockArt= new TextView(this);
            stockArt.setBackground(getDrawable(R.drawable.border));
            stockArt.setTextSize(18);
            stockArt.setPadding(5,5,5,5);

            nombreArt.setText(articulosNombres.get(i).toString());
            stockArt.setText(articulosStock.get(i).toString());

            tr.addView(nombreArt);
            tr.addView(stockArt);

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

package com.c12.almacenapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class PedidoActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_pedido);


        //get the spinner from the xml.
        Spinner categoria = findViewById(R.id.spinner);
        Spinner subcategoria = findViewById(R.id.spinner2);
        Spinner articulo = findViewById(R.id.spinner3);

        //create a list of items for the spinner.
        String[] cats = new String[]{"Categoría", "cat1", "cat2"};
        String[] subs = new String[]{"Subcategoría", "sub1", "sub2"};
        String[] items = new String[]{"Artículo", "art1", "art2"};


        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cats);
        //set the spinners adapter to the previously created one.
        categoria.setAdapter(adapter);

        adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, subs);
        subcategoria.setAdapter(adapter);

        adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        articulo.setAdapter(adapter);
    }
}

package com.c12.almacenapp;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import com.c12.almacenapp.data.Pedido;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MisPedidosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MenuView.ItemView nuevoPedido=findViewById(R.id.action_nuevoPedido);


        MenuAlmacenActivity.IP=getString(R.string.ipServidor);


        TableLayout tl = findViewById(R.id.tablaPedidos);

        try{
            JSONArray misPedidos=MenuAlmacenActivity.listaPedidoIndividual(MainActivity.ACTUAL.getUsername());
            for (int i=0;i<misPedidos.length();i++){
                Pedido pedido= new Pedido(misPedidos.getJSONObject(i));

                TableRow tr = new TableRow(this);
                TextView fecha= new TextView(this);
                TextView estado= new TextView(this);
                TextView detalle= new TextView(this);

                fecha.setText(pedido.getFecha());
                estado.setText(pedido.getEstado());
                detalle.setText("Ver más");
                detalle.setTextColor(getColor(R.color.blueLink));
                detalle.setPaintFlags(detalle.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
                detalle.setOnClickListener(v -> {
                    Intent intent= new Intent(MisPedidosActivity.this, PedidoEspecificoActivity.class);
                    intent.putExtra("pedidoId",pedido.getId());
                    intent.putExtra("pedidoFecha",pedido.getFecha());
                    intent.putExtra("pedidoEstado",pedido.getEstado());
                    intent.putExtra("pedidoUsuario",pedido.getUsuario());
                    intent.putExtra("pedidoObservaciones",pedido.getObservaciones());


                    startActivity(intent);
                });

                tr.addView(fecha);
                tr.addView(estado);
                tr.addView(detalle);
                tl.addView(tr);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(MisPedidosActivity.this,PedidoActivity.class));
                return false;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mis_pedidos,menu);
        return super.onCreateOptionsMenu(menu);
    }

}

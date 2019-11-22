package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class QrActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);


        TextView tv=findViewById(R.id.mostrarNombreArt);
        final String nombre=ArticuloActivity.nombreArticulo;
        tv.setText(nombre);
        Button aceptar= findViewById(R.id.aceptarQR);
        ImageView qr = findViewById(R.id.imageView2);
        final String src=getString(R.string.qrApi)+nombre;
        Picasso.get().load(src).into(qr);


        String urlEncoded="";
        try {
            urlEncoded=URLEncoder.encode(src, StandardCharsets.UTF_8.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        final String finalUrlEncoded = urlEncoded;
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpURLConnection urlConnection=null;
                try{
                    String urlS = getString(R.string.ipServidor) + "SubirQR?" +
                            "src="+finalUrlEncoded +
                            "&nombreArt="+ nombre;
                    Log.e("src",finalUrlEncoded);
                    URL url= new URL(urlS);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.connect();
                    Log.v("RESPUESTA >>>>>>>>", urlConnection.getResponseMessage()+urlConnection.getResponseCode());
                    if(urlConnection.getResponseCode()==500){
                        Toast.makeText(getApplicationContext(),"Falló la conexión",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Código QR del artículo "+nombre+" actualizado",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(QrActivity.this, MenuAlmacenActivity.class));

                    }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }finally{
                    urlConnection.disconnect();
                }

            }
        });

    }
}

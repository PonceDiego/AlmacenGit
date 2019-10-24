package com.example.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;

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
        final String src="https://api.qrserver.com/v1/create-qr-code/?data="+nombre;
        Picasso.get().load(src).into(qr);

        String urlEncoded="";
        try {
            urlEncoded=URLEncoder.encode(src, StandardCharsets.UTF_8.toString());
            Log.w("E N C O D E D:",urlEncoded);


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
                    URL url= new URL(urlS);
                    urlConnection = (HttpURLConnection)url.openConnection();
                    urlConnection.connect();
                    Log.w("repuesta<<<<<<<<<<<",urlConnection.getResponseMessage()+" <-Mensaje"+urlConnection.getResponseCode()+"<-- código");
                    if(urlConnection.getResponseCode()==500){
                        Toast.makeText(getApplicationContext(),"Falló la conexión",Toast.LENGTH_SHORT).show();
                    }
                }catch (MalformedURLException e) {
                    e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }finally{
                    urlConnection.disconnect();
                }

                Toast.makeText(getApplicationContext(),"Código QR del artículo "+nombre+" actualizado",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(QrActivity.this, MenuAlmacenActivity.class));
            }
        });

    }
}

package com.example.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProveedorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_proveedor);
        Button agregarProv = findViewById(R.id.proveedorAgregar);
        final TextView nombreProveedor = findViewById(R.id.nombreProveedor);
        final TextView telefonoProveedor = findViewById(R.id.telefonoProveedor);
        final TextView contactoProveedor = findViewById(R.id.contactoProveedor);
        final TextView direccionProveedor = findViewById(R.id.direccionProveedor);
        final TextView mailProveedor = findViewById(R.id.mailProveedor);
        URL url=null;

        agregarProv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//TODO: comprobar otros campos requeridos.
                if (nombreProveedor.getText().toString().equals("")
                        || telefonoProveedor.getText().toString().equals("")
                        || contactoProveedor.getText().toString().equals("")
                        || direccionProveedor.getText().toString().equals("")
                        || mailProveedor.getText().toString().equals("")
                ) {
                    Toast.makeText(getApplicationContext(), "Ingrese los datos requeridos", Toast.LENGTH_SHORT).show();

                } else {
                    //TODO: insert db.
                    String nombre, telefono, contacto, dir, mail;
                    nombre = nombreProveedor.getText().toString();
                    telefono = telefonoProveedor.getText().toString();
                    contacto = contactoProveedor.getText().toString();
                    dir = direccionProveedor.getText().toString();
                    mail = mailProveedor.getText().toString();
                    URL url = null;
                        try {
                            //TODO: Asignar valores reales, no hardcode
                            url = new URL(getString(R.string.ipServidor)+"ProveedorNuevoAndroid" +
                                    "?provNombre=" + nombre +
                                    "&provMail=" + mail +
                                    "&provTel=" + telefono +
                                    "&provCont=" + contacto +
                                    "&provDire=" + dir);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        HttpURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpURLConnection) url.openConnection();
                            urlConnection.setDoInput(true);
                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            Log.w("RESPUESTA >>>>>>><",in.toString());

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {

                            urlConnection.disconnect();
                        }




                    Toast.makeText(getApplicationContext(), "Proveedor " + nombre + " cargado con éxito!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ProveedorActivity.this, MenuAlmacenActivity.class));

                }
            }

        });
    }
}

package com.c12.almacenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.c12.almacenapp.data.Usuario;
import com.c12.almacenapp.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    public static Usuario ACTUAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        Button almacen = findViewById(R.id.button1);
        Button tecnica = findViewById(R.id.button2);
        Button llaves = findViewById(R.id.button3);
        Button cerrarSesion= findViewById(R.id.button4);

        almacen.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MenuAlmacenActivity.class)));

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });


    }
}

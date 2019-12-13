package com.c12.almacenapp.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.c12.almacenapp.MainActivity;
import com.c12.almacenapp.R;
import com.c12.almacenapp.data.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText usuario,clave;
    Button logearse;
    ProgressBar loading;

    String user,pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.username);
        clave=findViewById(R.id.password);

        usuario.setText("apps");
        clave.setText("1234");
        logearse=findViewById(R.id.login);
        loading=findViewById(R.id.loading);
        MainActivity.ACTUAL =null;

        logearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        user=usuario.getText().toString();
        pass=clave.getText().toString();
        boolean us;
        boolean pw;
        us=validarUsuario(user);
        pw=validarClave(pass);

        if(us&&pw){
            RequestQueue queue = Volley.newRequestQueue(this);
            String url= getString(R.string.ipServidor)+"IniciarSesion?username="+user+
                    "&pass="+pass+
                    "&and="+1;
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        if(response.equals("0")){
                            loading.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),"Usuario y/o contraseña incorrectos.", Toast.LENGTH_SHORT).show();
                        }
                        JSONObject object= new JSONObject(response);
                        String token= (String) object.get("token");
                        String nombre= (String) object.get("nombre");
                        String username= (String) object.get("username");
                        String area= (String) object.get("area");
                        String rol= (String) object.get("rol");
                        MainActivity.ACTUAL= new Usuario(token,nombre,username,area,rol);
                        loading.setVisibility(View.GONE);
                        loginExitoso();
                    }catch (Throwable t){

                        Log.e("JSON","No se pudo parsear");
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Falló la conexión con la API.",Toast.LENGTH_LONG).show();
                }
            });
            queue.add(stringRequest);
            loading.setVisibility(View.VISIBLE);

        }else{
            Toast.makeText(getApplicationContext(),"Por favor ingrese un usuario y/o contraseña válidos.", Toast.LENGTH_SHORT).show();
        }
    }


    private void loginExitoso(){
        Intent goHome = new Intent(this, MainActivity.class);
        finish();
        Toast.makeText(getApplicationContext(),"Bienvenido, usuario "+MainActivity.ACTUAL.getUsername(),Toast.LENGTH_SHORT).show();
        startActivity(goHome);
    }


    private boolean validarUsuario(String usuario){
        boolean resultado = true;
        if(usuario.equals("")){
            Snackbar.make(findViewById(R.id.container),"El campo usuario no puede estar vacío!",Snackbar.LENGTH_SHORT).show();
            resultado=false;
        }else if(usuario.length()<=3){
            resultado=false;
        }

        return resultado;
    }

    private boolean validarClave(String clave){
        //TODO hacer los snack bars
        boolean resultado = true;
        if(clave.equals("")){
            Snackbar.make(findViewById(R.id.container),"El campo contraseña no puede estar vacío!",Snackbar.LENGTH_SHORT).show();
            resultado=false;
        }else if(clave.length()<=3){
            resultado=false;
        }

        return resultado;
    }
}
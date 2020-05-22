package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String username, password;
    EditText usernameET, passwordET;
    private boolean keepConnected;
    RequestQueue requestQueue;
    CheckBox keepCBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        keepCBox = findViewById(R.id.keepCBox);
        getLoginPreferences();
    }

    public void validateUser(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){
                    savePreferences();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Username or password are incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> parameters=new HashMap<>();
                parameters.put("username", username);
                parameters.put("password", password);
                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void launchHomeActivity(View view) {
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();
        keepConnected = keepCBox.isChecked();
        if(!username.isEmpty() && !password.isEmpty()){
            validateUser("http://192.168.1.67/tfg/validate_user.php");
            //validateUser("http://192.168.1.128/tfg/validate_user.php");
        }else{
            Toast.makeText(getApplicationContext(), "Write username and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void savePreferences(){
        SharedPreferences preferences = getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putBoolean("keep", keepConnected);
        editor.commit();
    }

    private void getLoginPreferences(){
        SharedPreferences preferences=getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        usernameET.setText(preferences.getString("usuario", "Example77"));
        passwordET.setText(preferences.getString("password", "1234"));
    }

    public void launchNewUserActivity(View view) {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
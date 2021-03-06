package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    String username, password;
    EditText usernameET, passwordET;
    private boolean keepConnected;
    RequestQueue requestQueue;
    CheckBox keepCBox;
    UserLol user;

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
                    getUser("http://192.168.1.67/tfg/searchUserProfile.php");
                }else{
                    Toast.makeText(getApplicationContext(), R.string.errorLogin, Toast.LENGTH_SHORT).show();
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
                parameters.put("username", usernameET.getText().toString());
                parameters.put("password", passwordET.getText().toString());
                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUser(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username="+username, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //Create user
                    user = new UserLol(Integer.parseInt(jsonArray.getJSONObject(0).getString("id_user")), jsonArray.getJSONObject(0).getString("user"), jsonArray.getJSONObject(0).getString("discord"),
                            Integer.parseInt(jsonArray.getJSONObject(0).getString("photo")), jsonArray.getJSONObject(0).getString("value"), jsonArray.getJSONObject(1).getString("value"),
                            jsonArray.getJSONObject(2).getString("value"), jsonArray.getJSONObject(3).getString("value"),
                            jsonArray.getJSONObject(4).getString("value"), jsonArray.getJSONObject(5).getString("value"),
                            jsonArray.getJSONObject(6).getString("value"), false);
                    //Launch Activity
                    Intent intent = new Intent(LoginActivity.this, MenuBottomActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void launchHomeActivity(View view) {
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();
        keepConnected = keepCBox.isChecked();
        if(!username.isEmpty() && !password.isEmpty()){
            validateUser("http://192.168.1.67/tfg/validate_user.php");
        }else{
            Toast.makeText(getApplicationContext(), R.string.errorEmptyFields, Toast.LENGTH_SHORT).show();
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
        usernameET.setText(preferences.getString("username", ""));
        passwordET.setText(preferences.getString("password", ""));
    }

    public void launchNewUserActivity(View view) {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
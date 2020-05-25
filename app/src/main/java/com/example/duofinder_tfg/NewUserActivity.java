package com.example.duofinder_tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NewUserActivity extends AppCompatActivity {
    private EditText edtPwdVerify, edtUsername, edtPwd, edtDiscord;
    private Spinner photoSP, gamesSP;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        edtPwdVerify = findViewById(R.id.edtVerifyPwd);
        edtUsername = findViewById(R.id.edtUsername);
        edtPwd = findViewById(R.id.edtPwd);
        edtDiscord = findViewById(R.id.edtDiscord);

        CustomAdapter photoAdapter = new CustomAdapter(this,Images.profilePhoto,Images.profilePhotoImages);
        photoSP = (Spinner)findViewById(R.id.imageSP);
        photoSP.setAdapter(photoAdapter);

    }

    public void onClickRegister(View view){
        /*if(edtUsername.getText().toString() == edtPwdVerify.getText().toString()){
            insertUser("http://192.168.1.67/tfg/insertNewUser.php");
        }else{
            edtPwdVerify.setError("Passwords aren't the same");
        }*/
        insertUser("http://192.168.1.67/tfg/insertNewUser.php");
    }

    private void insertUser(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Sucessfully", Toast.LENGTH_SHORT).show();
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
                parameters.put("username", edtUsername.getText().toString());
                parameters.put("password", edtPwd.getText().toString());
                parameters.put("discord", edtDiscord.getText().toString());
                parameters.put("photo", photoSP.getSelectedItem().toString());

                /*parameters.put("username","admin");
                parameters.put("summoner_name","administrador");
                parameters.put("server","EUW");
                parameters.put("elo","Bronce III");
                parameters.put("role","TOP");
                parameters.put("firstMain","Bard");
                parameters.put("secondMain","Aatrox");
                parameters.put("thirdMain","Morgana");*/

                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

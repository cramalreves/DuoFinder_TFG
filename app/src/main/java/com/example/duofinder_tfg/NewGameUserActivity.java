package com.example.duofinder_tfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
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

public class NewGameUserActivity extends AppCompatActivity {
    private EditText summoner_nameET;
    private Spinner serverSP, rolesSP, champ1SP, champ2SP, champ3SP, elosSP;
    private CustomAdapterSpinners champsAdapter, elosAdapter, rolesAdapter ;
    private CustomAdapterServer serversAdapter;
    private RequestQueue requestQueue;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_user);
        setSpinners();
        summoner_nameET = findViewById(R.id.summoner_nameET);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
    }

    public void setSpinners(){
        champsAdapter = new CustomAdapterSpinners(this,Images.champs,Images.champsImages);
        champ1SP = (Spinner)findViewById(R.id.imageMain1);
        champ1SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapterSpinners(this,Images.champs,Images.champsImages);
        champ2SP = (Spinner)findViewById(R.id.imageMain2);
        champ2SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapterSpinners(this,Images.champs,Images.champsImages);
        champ3SP = (Spinner)findViewById(R.id.imageMain3);
        champ3SP.setAdapter(champsAdapter);

        elosAdapter = new CustomAdapterSpinners(this,Images.elos,Images.elosImages);
        elosSP = (Spinner)findViewById(R.id.eloSP);
        elosSP.setAdapter(elosAdapter);

        rolesAdapter = new CustomAdapterSpinners(this,Images.roles,Images.rolesImages);
        rolesSP = (Spinner)findViewById(R.id.roleSP);
        rolesSP.setAdapter(rolesAdapter);

        serversAdapter = new CustomAdapterServer(this,Images.servers,Images.rolesImages);
        serverSP = (Spinner)findViewById(R.id.serverSP);
        serverSP.setAdapter(serversAdapter);
    }

    public void onClickRegister(View view){
        if(username.isEmpty()){
            Toast.makeText(getApplicationContext(), "usuario vacio", Toast.LENGTH_SHORT).show();
        }else{
        if(summoner_nameET.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), getString(R.string.errorSummonerName), Toast.LENGTH_SHORT).show();
        }else{
            insertUser("http://192.168.1.67/tfg/insertNewGameUser.php");
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }}
    }

    private void insertUser(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Succesfully", Toast.LENGTH_SHORT).show();
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
                parameters.put("summoner_name", summoner_nameET.getText().toString());
                parameters.put("server", serverSP.getSelectedItem().toString());
                parameters.put("elo", elosSP.getSelectedItem().toString());
                parameters.put("role", rolesSP.getSelectedItem().toString());
                parameters.put("firstMain", champ1SP.getSelectedItem().toString());
                parameters.put("secondMain", champ2SP.getSelectedItem().toString());
                parameters.put("thirdMain", champ3SP.getSelectedItem().toString());

                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}


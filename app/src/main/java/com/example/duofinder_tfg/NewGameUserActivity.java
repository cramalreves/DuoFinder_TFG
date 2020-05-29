package com.example.duofinder_tfg;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NewGameUserActivity extends AppCompatActivity {
    private EditText usernameET, summoner_nameET;
    private Spinner serverSP, rolesSP, champ1SP, champ2SP, champ3SP, elosSP;
    private CustomAdapter champsAdapter, elosAdapter, rolesAdapter ;
    private CustomAdapterServer serversAdapter;
    private RequestQueue requestQueue;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_user);
        setSpinners();
        usernameET = findViewById(R.id.usernameET);
        summoner_nameET = findViewById(R.id.summoner_nameET);

        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
    }

    public void setSpinners(){
        champsAdapter = new CustomAdapter(this,Images.champs,Images.champsImages);
        champ1SP = (Spinner)findViewById(R.id.imageMain1);
        champ1SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapter(this,Images.champs,Images.champsImages);
        champ2SP = (Spinner)findViewById(R.id.imageMain2);
        champ2SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapter(this,Images.champs,Images.champsImages);
        champ3SP = (Spinner)findViewById(R.id.imageMain3);
        champ3SP.setAdapter(champsAdapter);

        elosAdapter = new CustomAdapter(this,Images.elos,Images.elosImages);
        elosSP = (Spinner)findViewById(R.id.eloSP);
        elosSP.setAdapter(elosAdapter);

        rolesAdapter = new CustomAdapter(this,Images.roles,Images.rolesImages);
        rolesSP = (Spinner)findViewById(R.id.roleSP);
        rolesSP.setAdapter(rolesAdapter);

        serversAdapter = new CustomAdapterServer(this,Images.servers,Images.rolesImages);
        serverSP = (Spinner)findViewById(R.id.serverSP);
        serverSP.setAdapter(serversAdapter);

       /* ArrayAdapter<String> serversAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Images.servers);
        serverSP = (Spinner)findViewById(R.id.textServer);
        serversAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        serverSP.setAdapter(serversAdapter);*/
    }

    public void onClickRegister(View view){
        /*if(passwordET.getText().equals(verify_passwordET.getText())){
            executeService("https://192.168.1.67/tfg/insert_user.php");
        }else{
            verify_passwordET.setError("Passwords aren't the same");
        }*/
        //insert("http://192.168.1.128/tfg/insert_user.php");
        insertUser("http://192.168.1.67/tfg/insertNewGameUser.php");
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
                parameters.put("username",username);
                parameters.put("summoner_name",summoner_nameET.getText().toString());
                parameters.put("server",serverSP.getSelectedItem().toString());
                parameters.put("elo",elosSP.getSelectedItem().toString());
                parameters.put("role",rolesSP.getSelectedItem().toString());
                parameters.put("firstMain",champ1SP.getSelectedItem().toString());
                parameters.put("secondMain",champ2SP.getSelectedItem().toString());
                parameters.put("thirdMain",champ3SP.getSelectedItem().toString());

                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /*public void searchAllChamps(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i=0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        champs[i] = jsonObject.getString("name");
                    }catch(JSONException error){
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }*/
}


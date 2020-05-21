package com.example.duofinder_tfg;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
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

public class NewUserActivity extends AppCompatActivity {
    private EditText usernameET, passwordET, verify_passwordET, summoner_nameET, discordET;
    private Spinner serverSP, rolesSP, champ1SP, champ2SP, champ3SP, elosSP;
    private Map<String, Integer> championsMap = new HashMap<>();
    private Map<String, Integer> elosMap = new HashMap<>();
    private Map<String, Integer> rolesMap = new HashMap<>();
    private String[] champs, elos, servers, roles;
    int[] champsImages, elosImages, rolesImages;
    private CustomAdapter champsAdapter, elosAdapter, rolesAdapter;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        champs = new String[148];
//      searchAllChamps("http://192.168.1.67/tfg/searchAllChamps.php");
        searchAllChamps("http://192.168.1.128/tfg/searchAllChamps.php");
        setSpinners();
        usernameET = findViewById(R.id.usernameET);
        passwordET = findViewById(R.id.passwordET);
        verify_passwordET = findViewById(R.id.verify_passwordET);
        summoner_nameET = findViewById(R.id.summoner_nameET);
        discordET = findViewById(R.id.discordET);

    }

    public void setSpinners(){
        //ID ARRAYS
        champsImages = new int[]{R.drawable.aatrox, R.drawable.ahri, R.drawable.akali, R.drawable.alistar, R.drawable.amumu, R.drawable.anivia, R.drawable.annie, R.drawable.aphelios, R.drawable.ashe,
                R.drawable.aurelion_sol, R.drawable.azir, R.drawable.bard, R.drawable.blitzcrank, R.drawable.brand, R.drawable.braum, R.drawable.caitlyn, R.drawable.camille, R.drawable.cassiopeia,
                R.drawable.chogath, R.drawable.corki, R.drawable.darius, R.drawable.diana, R.drawable.drmundo, R.drawable.draven, R.drawable.ekko, R.drawable.elise, R.drawable.evelynn, R.drawable.ezreal,
                R.drawable.fiddlesticks, R.drawable.fiora, R.drawable.fizz, R.drawable.galio, R.drawable.gangplank, R.drawable.garen, R.drawable.gnar, R.drawable.gragas, R.drawable.graves, R.drawable.hecarim,
                R.drawable.heimer, R.drawable.illaoi, R.drawable.irelia, R.drawable.ivern, R.drawable.janna, R.drawable.jarvan, R.drawable.jax, R.drawable.jayce, R.drawable.jhin, R.drawable.jinx, R.drawable.kaisa,
                R.drawable.kalista, R.drawable.karma, R.drawable.karthus, R.drawable.kassadin, R.drawable.katarina, R.drawable.kayle, R.drawable.kayn, R.drawable.kennen, R.drawable.khazix, R.drawable.kindred,
                R.drawable.kled, R.drawable.kogmaw, R.drawable.leblanc, R.drawable.leesin, R.drawable.leona, R.drawable.lissandra, R.drawable.lucian, R.drawable.lulu, R.drawable.lux, R.drawable.malphite,
                R.drawable.malzahar, R.drawable.maokai, R.drawable.masteryi, R.drawable.missfortune, R.drawable.mordekaiser, R.drawable.morgana, R.drawable.nami, R.drawable.nasus, R.drawable.nautilus, R.drawable.neeko,
                R.drawable.nidalee, R.drawable.nocturne, R.drawable.nunu, R.drawable.olaf, R.drawable.orianna, R.drawable.ornn, R.drawable.pantheon, R.drawable.poppy, R.drawable.pyke, R.drawable.qiyana, R.drawable.quinn,
                R.drawable.rakan, R.drawable.rammus, R.drawable.reksai, R.drawable.renekton, R.drawable.rengar, R.drawable.riven, R.drawable.rumble, R.drawable.ryze, R.drawable.sejuani, R.drawable.senna, R.drawable.sett,
                R.drawable.shaco, R.drawable.shen, R.drawable.shyvanna, R.drawable.singed, R.drawable.sion, R.drawable.sivir, R.drawable.skarner, R.drawable.sona, R.drawable.soraka, R.drawable.swain, R.drawable.sylas,
                R.drawable.syndra, R.drawable.tahmkench, R.drawable.taliyah, R.drawable.talon, R.drawable.taric, R.drawable.teemo, R.drawable.thresh, R.drawable.tristana, R.drawable.trundle, R.drawable.tryndamere,
                R.drawable.twistedfate, R.drawable.twitch, R.drawable.udyr, R.drawable.urgot, R.drawable.varus, R.drawable.vayne, R.drawable.veigar, R.drawable.velkoz, R.drawable.vi, R.drawable.viktor, R.drawable.vladimir,
                R.drawable.volibear, R.drawable.warwick, R.drawable.wukong, R.drawable.xayah, R.drawable.xerath, R.drawable.xinzhao, R.drawable.yasuo, R.drawable.yorick, R.drawable.yuumi, R.drawable.zac, R.drawable.zed,
                R.drawable.ziggs, R.drawable.zilean, R.drawable.zoe, R.drawable.zyra};
        elosImages = new int[]{R.drawable.iron,R.drawable.iron,R.drawable.iron,R.drawable.iron,R.drawable.bronze,R.drawable.bronze,R.drawable.bronze,R.drawable.bronze, R.drawable.silver,R.drawable.silver,R.drawable.silver,R.drawable.silver,R.drawable.gold,R.drawable.gold,
                R.drawable.gold,R.drawable.gold,R.drawable.plat,R.drawable.plat,R.drawable.plat,R.drawable.plat,R.drawable.diamond,R.drawable.diamond,R.drawable.diamond,R.drawable.diamond,R.drawable.master,R.drawable.gmaster, R.drawable.challenger};
        rolesImages = new int[]{R.drawable.top,R.drawable.jungle,R.drawable.mid,R.drawable.adc,R.drawable.support};

        //STRING ARRAYS
        servers = new String[]{"BR","EUNE","EUW","LAN","LAS","NA","OCE","RU","TR","JP","KR","CN","TW","SAM","VN","TH","PH","MENA"};
        roles = new String[]{"TOP","JUNGLE","MID","ADC","SUPPORT"};
        elos = new String[]{String.valueOf(getText(R.string.iron4)),String.valueOf(getText(R.string.iron3)),String.valueOf(getText(R.string.iron2)),String.valueOf(getText(R.string.iron1)),
                String.valueOf(getText(R.string.bronze4)),String.valueOf(getText(R.string.bronze3)),String.valueOf(getText(R.string.bronze2)),String.valueOf(getText(R.string.bronze1)),
                String.valueOf(getText(R.string.silver4)),String.valueOf(getText(R.string.silver3)),String.valueOf(getText(R.string.silver2)),String.valueOf(getText(R.string.silver1)),
                String.valueOf(getText(R.string.gold4)),String.valueOf(getText(R.string.gold3)),String.valueOf(getText(R.string.gold2)),String.valueOf(getText(R.string.gold1)),
                String.valueOf(getText(R.string.plat4)),String.valueOf(getText(R.string.plat3)),String.valueOf(getText(R.string.plat2)),String.valueOf(getText(R.string.plat1)),
                String.valueOf(getText(R.string.diamond4)),String.valueOf(getText(R.string.diamond3)),String.valueOf(getText(R.string.diamond2)),String.valueOf(getText(R.string.diamond1)),
                String.valueOf(getText(R.string.master)),String.valueOf(getText(R.string.gmaster)),String.valueOf(getText(R.string.challenger))};

        champsAdapter = new CustomAdapter(this,champs,champsImages);
        champ1SP = (Spinner)findViewById(R.id.main1SP);
        champ1SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapter(this,champs,champsImages);
        champ2SP = (Spinner)findViewById(R.id.main2SP);
        champ2SP.setAdapter(champsAdapter);

        champsAdapter = new CustomAdapter(this,champs,champsImages);
        champ3SP = (Spinner)findViewById(R.id.main3SP);
        champ3SP.setAdapter(champsAdapter);

        elosAdapter = new CustomAdapter(this,elos,elosImages);
        elosSP = (Spinner)findViewById(R.id.eloSP);
        elosSP.setAdapter(elosAdapter);

        rolesAdapter = new CustomAdapter(this,roles,rolesImages);
        rolesSP = (Spinner)findViewById(R.id.roleSP);
        rolesSP.setAdapter(rolesAdapter);

        ArrayAdapter<String> serversAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, servers);
        serverSP = (Spinner)findViewById(R.id.serverSP);
        serversAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        serverSP.setAdapter(serversAdapter);
    }

    public void fillMaps(){
        for (int i=0;i<champs.length;i++){
            championsMap.put(champs[i],champsImages[i]);
        }
        for (int i=0;i<elos.length;i++){
            elosMap.put(elos[i], elosImages[i]);
        }
        for (int i=0;i<roles.length;i++){
            rolesMap.put(roles[i], rolesImages[i]);
        }
    }

    public void onClickRegister(View view){
        /*if(passwordET.getText().equals(verify_passwordET.getText())){
            executeService("https://192.168.1.67/tfg/insert_user.php");
        }else{
            verify_passwordET.setError("Passwords aren't the same");
        }*/
       // executeService("http://192.168.1.67/tfg/insert_user.php");
        executeService("http://192.168.1.128/tfg/insert_user.php");
    }

    private void executeService(String URL){
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
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parameters=new HashMap<>();
                parameters.put("username",usernameET.getText().toString());
                parameters.put("password",passwordET.getText().toString());
                parameters.put("summoner_name",summoner_nameET.getText().toString());
                parameters.put("server",serverSP.getSelectedItem().toString());
                parameters.put("elo",elosSP.getSelectedItem().toString());
                parameters.put("role",rolesSP.getSelectedItem().toString());
                parameters.put("firstMain",champ1SP.getSelectedItem().toString());
                parameters.put("secondMain",champ2SP.getSelectedItem().toString());
                parameters.put("thirdMain",champ3SP.getSelectedItem().toString());
                parameters.put("discord",discordET.getText().toString());
                parameters.put("opgg","https://"+serverSP.getSelectedItem().toString()+".op.gg/summoner/userName="+summoner_nameET.getText().toString());
                parameters.put("photo","poro.png");

                /*parameters.put("username","Patricio");
                parameters.put("password","123456789");
                parameters.put("summoner_name","123456789");
                parameters.put("server","EUW");
                parameters.put("elo","Bronce III");
                parameters.put("role","TOP");
                parameters.put("firstMain","Bard");
                parameters.put("secondMain","Aatrox");
                parameters.put("thirdMain","Morgana");
                parameters.put("discord","Patricio");
                parameters.put("opgg","hola");
                parameters.put("photo","poro.png");*/
                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void searchAllChamps(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i=0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        champs[i]=jsonObject.getString("name");
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
    }

    public void searchAllElos(String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;

                for (int i=0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        elos[i]=jsonObject.getString("elo");
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
    }
}


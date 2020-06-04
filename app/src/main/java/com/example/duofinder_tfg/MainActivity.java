package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    private ImageView logo;
    private RequestQueue requestQueue;
    private UserLol user;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.logo);

        Animation animStatic = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.static_anim);
        logo.startAnimation(animStatic);
        animStatic.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                SharedPreferences preferences=getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
                boolean keepConnected=preferences.getBoolean("keep", false);
                if(keepConnected){
                    username = preferences.getString("username", "ZeKroX24");
                    getUser("http://192.168.1.67/tfg/searchUserProfile.php");
                }else{
                    Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                }
            }
        });
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
                    Intent intent = new Intent(MainActivity.this, MenuBottomActivity.class);
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

}

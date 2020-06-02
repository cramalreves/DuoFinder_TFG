package com.example.duofinder_tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import link.fls.swipestack.SwipeStack;

public class MenuBottomActivity extends AppCompatActivity {
    SwipeStack swipeStack;
    SwipeAdapter adapter;
    private TextView textView;
    BottomNavigationView mBottomNavigation;
    static String username;
    static ArrayList<Usuario> users;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_bottom);
        showSelectedFragment(new HomeFragment());
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        mBottomNavigation.setSelectedItemId(R.id.home);
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.profile) {
                    showSelectedFragment(new ProfileFragment());
                }
                if (menuItem.getItemId() == R.id.home) {
                    showSelectedFragment(new HomeFragment());
                }
                if (menuItem.getItemId() == R.id.notis) {
                    showSelectedFragment(new NotisFragment());
                }
                return true;
            }
        });
        users = new ArrayList<>();
        getUsers("http://192.168.1.67/tfg/searchUsersProfiles.php");
    }

    //METODO QUE PERMITE ELEGIR EL FRAFMENT QUE SE MOSTRARA EN EL CONTAINER DEL HOME.
    private void showSelectedFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void exit(View view){
        SharedPreferences preferences=getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void getUsers(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i+=7){
                        users.add(new Usuario(R.drawable.icon8, jsonArray.getJSONObject(i).getString("value"), jsonArray.getJSONObject(i+1).getString("value"),
                                jsonArray.getJSONObject(i+2).getString("value"), jsonArray.getJSONObject(i+3).getString("value"),
                                jsonArray.getJSONObject(i+4).getString("value"), jsonArray.getJSONObject(i+5).getString("value"),
                                jsonArray.getJSONObject(i+6).getString("value"), false));
                    }

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

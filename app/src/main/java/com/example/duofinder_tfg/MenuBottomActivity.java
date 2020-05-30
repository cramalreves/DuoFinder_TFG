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

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

public class MenuBottomActivity extends AppCompatActivity {
    SwipeStack swipeStack;
    SwipeAdapter adapter;
    ArrayList<Usuario> users;
    private TextView textView;
    BottomNavigationView mBottomNavigation;

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

}

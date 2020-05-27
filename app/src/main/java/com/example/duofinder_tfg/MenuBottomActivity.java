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

    private ArrayList<Usuario> getUsers() {
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario(R.drawable.icon8, "Stellaa37", "Jungle", "Challenger", "Vi", "Kha'Zix", "Camille", false));
        list.add(new Usuario(R.drawable.icon3, "ZeKroX24", "Mid", "Iron 4", "Ekko", "Sylas", "Fizz", false));
        list.add(new Usuario(R.drawable.icon2, "SKT Faker", "Mid", "Challenger", "Zed", "Twisted Fate", "Kassadin", false));
        list.add(new Usuario(R.drawable.icon1, "G2 Perkz", "ADCarry", "Challenger", "Kai'Sa", "Xayah", "Aphelios", false));
        list.add(new Usuario(R.drawable.icon3, "G2 Ibai", "Top", "Master", "Renekton", "Rumble", "Riven", false));
        return list;
    }

    public void exit(View view){
        SharedPreferences preferences=getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

}

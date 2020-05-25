package com.example.duofinder_tfg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuBottomActivity extends AppCompatActivity {

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

                if (menuItem.getItemId()==R.id.profile){
                    showSelectedFragment(new ProfileFragment());
                }

                if (menuItem.getItemId()==R.id.home){
                    showSelectedFragment(new HomeFragment());
                }
                if (menuItem.getItemId()==R.id.notis){
                    showSelectedFragment(new NotisFragment());
                }
                return true;
            }
        });
    }

    //METODO QUE PERMITE ELEGIR EL FRAFMENT QUE SE MOSTRARA EN EL CONTAINER DEL HOME.
    private void showSelectedFragment (Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}

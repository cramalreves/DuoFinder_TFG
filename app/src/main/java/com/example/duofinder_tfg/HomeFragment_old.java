package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duofinder_tfg.LoginActivity;
import com.example.duofinder_tfg.NotificationActivity;
import com.example.duofinder_tfg.R;
import com.example.duofinder_tfg.SwipeAdapter;
import com.example.duofinder_tfg.Usuario;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment_old extends AppCompatActivity implements SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {
    SwipeStack swipeStack;
    SwipeAdapter adapter;
    ArrayList<Usuario> users;

    private TextView textView;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        swipeStack=findViewById(R.id.swipeStack);
        users=getUsers();
        adapter= new SwipeAdapter(this, users);
        swipeStack.setAdapter(adapter);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setDefaultTab(R.id.tab_home);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_profile) {

                } else if (tabId == R.id.tab_home) {

                } else if (tabId == R.id.tab_notis) {
                    Intent intent=new Intent(getApplicationContext(), NotificationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void exit(View view){
        SharedPreferences preferences=getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();

        Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private ArrayList <Usuario> getUsers(){
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario(R.drawable.icon8,"Stellaa37", "Jungle", "Challenger", "Vi", "Kha'Zix", "Camille", false));
        list.add(new Usuario(R.drawable.icon3,"ZeKroX24", "Mid", "Iron 4", "Ekko", "Sylas", "Fizz", false));
        list.add(new Usuario(R.drawable.icon2,"SKT Faker", "Mid", "Challenger", "Zed", "Twisted Fate", "Kassadin", false));
        list.add(new Usuario(R.drawable.icon1,"G2 Perkz", "ADCarry", "Challenger", "Kai'Sa", "Xayah", "Aphelios", false));
        list.add(new Usuario(R.drawable.icon3,"G2 Ibai", "Top", "Master", "Renekton", "Rumble", "Riven", false));
        return list;
    }
    public void like( View view){
        swipeStack.onViewSwipedToRight();
    }

    public void dislike (View view){
        swipeStack.onViewSwipedToLeft();
    }

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {

    }

    @Override
    public void onSwipeStart(int position) {

    }

    @Override
    public void onSwipeProgress(int position, float progress) {

    }

    @Override
    public void onSwipeEnd(int position) {

    }
}
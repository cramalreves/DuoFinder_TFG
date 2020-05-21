package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

public class HomeActivity extends AppCompatActivity implements SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener {
    SwipeStack swipeStack;
    SwipeAdapter adapter;
    ArrayList<Usuario> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        swipeStack=findViewById(R.id.swipeStack);
        users=getUsers();
        adapter= new SwipeAdapter(this, users);
        swipeStack.setAdapter(adapter);
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

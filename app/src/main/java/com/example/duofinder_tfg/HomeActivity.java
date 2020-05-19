package com.example.duofinder_tfg;

import android.os.Bundle;
import android.view.View;

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

    private ArrayList <Usuario> getUsers(){
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario("Stellaa37", R.drawable.icon4,false));
        list.add(new Usuario("ZeKroX24", R.drawable.icon2,false));
        list.add(new Usuario("SKT Faker", R.drawable.icon3,false));
        list.add(new Usuario("G2 Perkz", R.drawable.icon1,false));
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

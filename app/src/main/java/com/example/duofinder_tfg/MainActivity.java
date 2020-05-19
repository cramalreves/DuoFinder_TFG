package com.example.duofinder_tfg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void newUserActivity(View view) {
        Intent i = new Intent(this, NewUserActivity.class);
        startActivity(i);
    }
}

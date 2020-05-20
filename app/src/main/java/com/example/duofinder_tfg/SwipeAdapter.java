package com.example.duofinder_tfg;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SwipeAdapter extends ArrayAdapter<Usuario> {
    private ArrayList<Usuario> users;
    private Activity activity;

    public SwipeAdapter(@NonNull Activity activity, ArrayList<Usuario> users) {
        super(activity, 0, users);
        this.activity=activity;
        this.users=users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Nullable
    @Override
    public Usuario getItem(int position) {
        return users.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Usuario user = users.get(position);
        convertView = activity.getLayoutInflater().inflate(R.layout.content_item,parent,false);
        ImageView imgUser = convertView.findViewById(R.id.imgUser);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtElo = convertView.findViewById(R.id.txtElo);


        Glide.with(activity).load(user.getImagen()).into(imgUser);
        txtName.setText(user.getNombre());
        txtElo.setText(user.getElo());

        return convertView;
    }
}

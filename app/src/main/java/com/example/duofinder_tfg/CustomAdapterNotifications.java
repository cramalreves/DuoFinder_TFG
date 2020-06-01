package com.example.duofinder_tfg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapterNotifications extends ArrayAdapter<String> {
    Context context;
    ArrayList<String> users;
    ArrayList<Integer> images;

    public CustomAdapterNotifications(@NonNull Context context, ArrayList<String> users, ArrayList<Integer> images) {
        super(context, R.layout.listview_item, users);
        this.context = context;
        this.users = users;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_item, null);
        TextView t1 = (TextView)row.findViewById(R.id.edtUsername);
        ImageView i1 = (ImageView)row.findViewById(R.id.profileIcon);
        t1.setText(users.get(position));
        i1.setImageResource(images.get(position));

        return row;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_item, null);
        TextView t1 = (TextView)row.findViewById(R.id.edtUsername);
        ImageView i1 = (ImageView)row.findViewById(R.id.profileIcon);
        t1.setText(users.get(position));
        i1.setImageResource(images.get(position));

        return row;
    }
}

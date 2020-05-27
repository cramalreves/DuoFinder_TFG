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

public class CustomAdapterProfile extends ArrayAdapter<String> {
    Context context;
    String[] names;
    int[] images;

    public CustomAdapterProfile(@NonNull Context context, String[] names, int[] images) {
        super(context, R.layout.spinner_icon, names);
        this.context = context;
        this.names = names;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_icon, null);
        ImageView i1 = (ImageView)row.findViewById(R.id.imageView);
        i1.setImageResource(images[position]);

        return row;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_icon, null);
        ImageView i1 = (ImageView)row.findViewById(R.id.imageView);
        i1.setImageResource(images[position]);

        return row;
    }
}

package com.example.duofinder_tfg;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SwipeAdapter extends ArrayAdapter<UserLol> {
    private ArrayList<UserLol> users;
    private Activity activity;

    public SwipeAdapter(@NonNull Activity activity, ArrayList<UserLol> users) {
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
    public UserLol getItem(int position) {
        return users.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Images i = new Images();
        ImageView imgUser, imgRole, imgM1, imgM2, imgM3, imgElo;
        TextView txtName, txtElo, txtServer;
        UserLol user = users.get(position);
        convertView = activity.getLayoutInflater().inflate(R.layout.content_item,parent,false);
        imgUser = convertView.findViewById(R.id.imgUser);
        txtServer = convertView.findViewById(R.id.txtServer);
        txtName = convertView.findViewById(R.id.txtName);
        txtElo = convertView.findViewById(R.id.txtElo);
        imgRole = convertView.findViewById(R.id.imageRole);
        imgElo = convertView.findViewById(R.id.imageElo);
        imgM1 = convertView.findViewById(R.id.imageMain1);
        imgM2 = convertView.findViewById(R.id.imageMain2);
        imgM3 = convertView.findViewById(R.id.imageMain3);
        String role = user.getRole();
        imgRole.setImageResource(i.getRoleImageId(user.getRole()));
        imgElo.setImageResource(i.getEloImageId(user.getElo()));
        imgM1.setImageResource(i.getChampImageId(user.getChamp1()));
        imgM2.setImageResource(i.getChampImageId(user.getChamp2()));
        imgM3.setImageResource(i.getChampImageId(user.getChamp3()));
        txtServer.setText(user.getServer());
        imgUser.setImageResource(Images.profilePhotoImages[user.getPhoto()]);
        txtName.setText(user.getSummoner_name());
        txtElo.setText(user.getRole());

        return convertView;
    }
}

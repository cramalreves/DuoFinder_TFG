package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{
    private TextView textServer, textElo, textSummoner, edtDiscord;
    private ImageView imageMain1, imageMain2, imageMain3, imageElo, imageRole, icon, settings;
    private UserLol userLogged;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        MenuBottomActivity activity = (MenuBottomActivity)getActivity();
        userLogged = activity.getUserLogged();

        icon = rootView.findViewById(R.id.imageProfile);
        textElo = rootView.findViewById(R.id.textElo);
        textSummoner = rootView.findViewById(R.id.textSummoner);
        textServer = rootView.findViewById(R.id.textServer);
        settings = (ImageView) rootView.findViewById(R.id.imageReturn);
        edtDiscord = rootView.findViewById(R.id.edtDiscord);
        settings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        imageMain1 = rootView.findViewById(R.id.imageMain1);
        imageMain2 = rootView.findViewById(R.id.imageMain2);
        imageMain3 = rootView.findViewById(R.id.imageMain3);
        imageElo = rootView.findViewById(R.id.imageElo);
        imageRole = rootView.findViewById(R.id.imageRole);
        setUserProfile();
        return rootView;
    }

    private void setUserProfile(){
        Images img = new Images();
        icon.setImageResource(Images.profilePhotoImages[userLogged.getPhoto()]);
        edtDiscord.setText(userLogged.getDiscord());
        textSummoner.setText(userLogged.getSummoner_name());
        textServer.setText("#" + userLogged.getServer());
        textElo.setText(userLogged.getElo());
        imageElo.setImageResource(img.getEloImageId(userLogged.getElo()));
        imageRole.setImageResource(img.getRoleImageId(userLogged.getRole()));
        imageMain1.setImageResource(img.getChampImageId(userLogged.getChamp1()));
        imageMain2.setImageResource(img.getChampImageId(userLogged.getChamp2()));
        imageMain3.setImageResource(img.getChampImageId(userLogged.getChamp3()));
    }

}

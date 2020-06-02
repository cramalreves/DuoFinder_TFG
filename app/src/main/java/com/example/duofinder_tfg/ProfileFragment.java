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
    private RequestQueue requestQueue;
    private String username;
    private TextView textServer, textElo, textSummoner, edtDiscord;
    private ImageView imageMain1, imageMain2, imageMain3, imageElo, imageRole, icon, settings;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
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
        /*Bundle bundle = getActivity().getIntent().getExtras();
        username = bundle.getString("username");*/
        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        username = prefs.getString("username", "example_user");
        getUserAtributes("http://192.168.1.67/tfg/searchUserProfile.php");
        /**/

        return rootView;
    }

    private void getUserAtributes(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username="+username, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Images img = new Images();
                    JSONArray jsonArray = new JSONArray(response);
                    icon.setImageResource(Images.profilePhotoImages[Integer.parseInt(jsonArray.getJSONObject(0).getString("photo"))]);
                    edtDiscord.setText(jsonArray.getJSONObject(0).getString("discord"));
                    textSummoner.setText(jsonArray.getJSONObject(0).getString("value"));
                    textServer.setText("#" + jsonArray.getJSONObject(1).getString("value"));
                    textElo.setText(jsonArray.getJSONObject(2).getString("value"));
                    imageElo.setImageResource(img.getEloImageId(jsonArray.getJSONObject(2).getString("value")));
                    imageRole.setImageResource(img.getRoleImageId(jsonArray.getJSONObject(3).getString("value")));
                    imageMain1.setImageResource(img.getChampImageId(jsonArray.getJSONObject(4).getString("value")));
                    imageMain2.setImageResource(img.getChampImageId(jsonArray.getJSONObject(5).getString("value")));
                    imageMain3.setImageResource(img.getChampImageId(jsonArray.getJSONObject(6).getString("value")));
                } catch (JSONException e) {
                    Toast.makeText(getActivity().getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(request);
    }

}

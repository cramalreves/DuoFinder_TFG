package com.example.duofinder_tfg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private RequestQueue requestQueue;
    private String user;
    private TextView textServer, textElo, textSummoner, textRole;
    private ImageView imageMain1, imageMain2, imageMain3, imageElo, imageRole;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        textElo = rootView.findViewById(R.id.textElo);
        textSummoner = rootView.findViewById(R.id.textSummoner);
        textServer = rootView.findViewById(R.id.textServer);
       // textRole = rootView.findViewById(R.id.textLane);
        imageMain1 = rootView.findViewById(R.id.imageMain1);
        imageMain2 = rootView.findViewById(R.id.imageMain2);
        imageMain3 = rootView.findViewById(R.id.imageMain3);
        imageElo = rootView.findViewById(R.id.imageElo);
        imageRole = rootView.findViewById(R.id.imageRole);
        getUserAtributes("http://192.168.1.67/tfg/searchUserProfile.php");
        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        user = prefs.getString("username", "example_user");
        return rootView;

    }

    private void getUserAtributes(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username=ZeKroX24", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Images img = new Images();
                    JSONArray jsonArray = new JSONArray(response);
                    textSummoner.setText(jsonArray.getJSONObject(0).getString("value"));
                    textServer.setText("#" + jsonArray.getJSONObject(1).getString("value"));
                    textElo.setText(jsonArray.getJSONObject(2).getString("value"));
                   // textRole.setText(jsonArray.getJSONObject(3).getString("value"));
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

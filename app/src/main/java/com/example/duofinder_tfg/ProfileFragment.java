package com.example.duofinder_tfg;

import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    private RequestQueue requestQueue;
    String summoner_name, server, elo, role, main1, main2, main3;
    StringRequest stringRequest;
    TextView textServer, textElo, textSummoner;
    ImageView imageMain1, imageMain2, imageMain3, imageElo, imageRole;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Images img = new Images();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        textElo = rootView.findViewById(R.id.textElo);
        textSummoner = rootView.findViewById(R.id.textSummoner);
        textServer = rootView.findViewById(R.id.textServer);
        imageMain1 = rootView.findViewById(R.id.imageMain1);
        imageMain2 = rootView.findViewById(R.id.imageMain2);
        imageMain3 = rootView.findViewById(R.id.imageMain3);
        imageElo = rootView.findViewById(R.id.imageElo);
        imageRole = rootView.findViewById(R.id.imageRole);
        getUserAtributes("http://192.168.1.67/tfg/searchUserProfile.php");


        //textSummoner.setText();
        //textServer.setText();
        //textElo.setText();
        /*imageElo.setImageResource(img.getEloImageId());
        imageRole.setImageResource(img.getRoleImageId());
        imageMain1.setImageResource(img.getChampImageId());
        imageMain2.setImageResource(img.getChampImageId());
        imageMain3.setImageResource(img.getChampImageId());*/
        return rootView;

    }

    /*private void getUserAtributes(String URL){
        final String usuario = "ZeKroX24";
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    textSummoner.setText(jsonArray.getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters=new HashMap<>();
                parameters.put("username", usuario);
                return parameters;
            }
        };
        requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(request);
    }*/


    private void getUserAtributes(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i=0; i < response.length(); i++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        if(i==0){
                            textSummoner.setText(jsonObject.getString("name"));
                        }
                    }catch(JSONException error){
                        error.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });/*{
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> parameters=new HashMap<>();
                parameters.put("username", "ZeKroX24");
                return parameters;
            }
        };*/
        requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(jsonArrayRequest);
    }

}

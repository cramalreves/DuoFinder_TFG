package com.example.duofinder_tfg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.roughike.bottombar.BottomBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeStack.SwipeStackListener, SwipeStack.SwipeProgressListener{
    private SwipeStack swipeStack;
    private String transmitter, receiver;
    private SwipeAdapter adapter;
    private ImageView fav, clear;
    private ArrayList<Usuario> users;
    private TextView textView;
    private BottomBar bottomBar;
    private RequestQueue requestQueue;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        users = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        swipeStack=(SwipeStack) rootView.findViewById(R.id.swipeStack);
        fav = (ImageView) rootView.findViewById(R.id.imageButton4);
        clear = (ImageView) rootView.findViewById(R.id.imageButton2);
        getUsers("http://192.168.1.67/tfg/searchUsersProfiles.php");

        SharedPreferences prefs = this.getActivity().getSharedPreferences("loginPreferences", Context.MODE_PRIVATE);
        transmitter = prefs.getString("username", "example_user");
        
        adapter= new SwipeAdapter(this.getActivity(), getUsersList());
        swipeStack.setAdapter(adapter);

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                clear.setImageResource(R.drawable.ic_clear_red);
            }

            @Override
            public void onViewSwipedToRight(int position) {
                notifyUser("http://192.168.1.67/tfg/insertNewNotification.php");
            }

            @Override
            public void onStackEmpty() {

            }
        });

       // Inflate the layout for this fragment
        return rootView;
    }

    private ArrayList <Usuario> getUsersList(){
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario(R.drawable.icon8, "Stellaa37", "EUW","Challenger", "JUNGLE", "Vi", "Kha'Zix", "Camille", false));
        list.add(new Usuario(R.drawable.icon8, "ZeKroX24", "EUW","Challenger", "MID", "Ekko", "Sylas", "Fizz", false));
        return list;
    }

    private void getUsers(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username=ZeKroX24", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    users.add(new Usuario(R.drawable.icon8, jsonArray.getJSONObject(0).getString("value"), jsonArray.getJSONObject(1).getString("value"),
                            jsonArray.getJSONObject(2).getString("value"), jsonArray.getJSONObject(3).getString("value"),
                            jsonArray.getJSONObject(4).getString("value"), jsonArray.getJSONObject(5).getString("value"),
                            jsonArray.getJSONObject(6).getString("value"), false));
                    users.add(new Usuario(R.drawable.icon8, "Stellaa37", "EUW","Challenger", "MID", "Ekko", "Sylas", "Fizz", false));
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

    private void notifyUser(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity().getApplicationContext(), transmitter+" "+getString(R.string.userNotify), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getActivity().getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> parameters=new HashMap<>();
                parameters.put("transmitter", transmitter);
                parameters.put("receiver", receiver);

                return parameters;
            }
        };
        requestQueue= Volley.newRequestQueue(this.getActivity());
        requestQueue.add(stringRequest);
    }

    /*public void createCards(){
        adapter= new SwipeAdapter(this.getActivity(), users);
        swipeStack.setAdapter(adapter);

        swipeStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                clear.setImageResource(R.drawable.ic_clear_red);
                textView.setText("adios");
            }

            @Override
            public void onViewSwipedToRight(int position) {

            }

            @Override
            public void onStackEmpty() {

            }
        });
    }*/

    public void like( View view){
        swipeStack.onViewSwipedToRight();
    }

    public void dislike (View view){
        swipeStack.onViewSwipedToLeft();
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

    @Override
    public void onViewSwipedToLeft(int position) {

    }

    @Override
    public void onViewSwipedToRight(int position) {

    }

    @Override
    public void onStackEmpty() {

    }
}

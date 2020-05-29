package com.example.duofinder_tfg;

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
    private SwipeAdapter adapter;
    private ImageView fav, clear;
    private ArrayList<Usuario> users;
    private TextView textView;
    private BottomBar bottomBar;
    private String summoner;
    private RequestQueue requestQueue;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //swipeStack=getView().findViewById(R.id.swipeStack);
        users = new ArrayList<>();
        getUsers("http://192.168.1.67/tfg/searchUsersProfiles.php");
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        swipeStack=(SwipeStack) rootView.findViewById(R.id.swipeStack);
        fav = (ImageView) rootView.findViewById(R.id.imageButton4);
        clear = (ImageView) rootView.findViewById(R.id.imageButton2);
        textView = (TextView) rootView.findViewById(R.id.hola);

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

       // Inflate the layout for this fragment
        return rootView;
    }

    /*private ArrayList <Usuario> getUsersList(){
        ArrayList<Usuario> list = new ArrayList<>();
        list.add(new Usuario(R.drawable.icon8, summoner, "EUW","Challenger", "JUNGLE", "Vi", "Kha'Zix", "Camille", false));
        list.add(new Usuario(R.drawable.icon8, "Stellaa37", "EUW","Challenger", "MID", "Ekko", "Sylas", "Fizz", false));
        return list;
    }*/

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

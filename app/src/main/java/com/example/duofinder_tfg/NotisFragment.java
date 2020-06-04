package com.example.duofinder_tfg;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.duofinder_tfg.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotisFragment extends Fragment {
    private ListView listview;
    private RequestQueue requestQueue;
    private ArrayList<String> usersThatNotify = new ArrayList<>();
    private ArrayList<Integer> usersThatNotifyProfileImage = new ArrayList<>();
    private UserLol userLogged;

    public NotisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_notis, container, false);
        MenuBottomActivity activity = (MenuBottomActivity)getActivity();
        userLogged = activity.getUserLogged();
        getUserNotifications("http://192.168.1.67/tfg/searchUserNotifications.php");
        listview = rootView.findViewById(R.id.usersList);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String usernameSelected = (String) listview.getItemAtPosition(position);
                Intent i = new Intent(getActivity(), UserProfileActivity.class);
                i.putExtra("usernameSelected", usernameSelected);
                startActivity(i);
            }
        });
        listview.setEmptyView(rootView.findViewById(R.id.empty_view));

        return rootView;
    }

    private void getUserNotifications(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username="+userLogged.getUsername(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++){
                        usersThatNotify.add(jsonArray.getJSONObject(i).getString("user_emisor"));
                        usersThatNotifyProfileImage.add(Images.profilePhotoImages[Integer.valueOf(jsonArray.getJSONObject(i).getString("photo"))]);
                    }
                    createList();
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

    private void createList() {
        CustomAdapterNotifications adapter = new CustomAdapterNotifications(this.getActivity(),usersThatNotify,usersThatNotifyProfileImage);
        listview.setAdapter(adapter);
    }
}

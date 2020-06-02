package com.example.duofinder_tfg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class UserProfileActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private String usernameSelected;
    private TextView textServer, textElo, textSummoner, edtDiscord;
    private ImageView imageMain1, imageMain2, imageMain3, imageElo, imageRole, icon, settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        textSummoner = findViewById(R.id.textSummoner);
        icon = findViewById(R.id.imageProfile);
        textElo = findViewById(R.id.textElo);
        textServer = findViewById(R.id.textServer);
        settings = findViewById(R.id.imageReturn);
        edtDiscord = findViewById(R.id.edtDiscord);
        imageMain1 = findViewById(R.id.imageMain1);
        imageMain2 = findViewById(R.id.imageMain2);
        imageMain3 = findViewById(R.id.imageMain3);
        imageElo = findViewById(R.id.imageElo);
        imageRole = findViewById(R.id.imageRole);


        Bundle bundle = getIntent().getExtras();
        usernameSelected = bundle.getString("usernameSelected");
        textSummoner.setText(usernameSelected);
        getUserAtributes("http://192.168.1.67/tfg/searchUserSelectedProfile.php");
    }

    private void getUserAtributes(String URL){
        StringRequest request = new StringRequest(Request.Method.GET, URL+"?username="+usernameSelected, new Response.Listener<String>() {
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
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void onClickReturn(View view){
        finish();
    }
}

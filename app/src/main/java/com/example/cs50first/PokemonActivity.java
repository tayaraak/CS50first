package com.example.cs50first;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class PokemonActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView type1TextView;
    private TextView type2TextView;
    private TextView numberTextView;
    private RequestQueue requestQueue;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        url = getIntent().getStringExtra("url");
        nameTextView = findViewById(R.id.name_text_view);
        numberTextView = findViewById(R.id.number_text_view);
        type1TextView = findViewById(R.id.type1_text_view);
        type2TextView = findViewById(R.id.type2_text_view);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        load();
    }
    public void load(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                        nameTextView.setText(response.getString("name"));
                        numberTextView.setText(String.format("#%03d", response.getInt("id")));
                        JSONArray typeEntries = response.getJSONArray("types");
                        for (int i = 0; i<typeEntries.length(); i++){
                            JSONObject typeObject = typeEntries.getJSONObject(i);
                            int slot = typeObject.getInt("slot");
                            String type = typeObject.getJSONObject("type").getString("name");
                            if (slot == 1){
                                type1TextView.setText(type);
                            }else if (slot == 2){
                                type2TextView.setText(type);
                            }
                        }
                } catch (Exception e) {
                    Log.e("requesting api", "Pokemon Json error: ", e);
                    e.printStackTrace();
                  }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("requesting api", "onErrorResponse: pokemon details error ");
            }
        });
        requestQueue.add(request);
    }
}

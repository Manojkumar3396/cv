package com.example.json;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    TextView  textView, textView2, textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.mlistview);
//        textView2= (TextView) findViewById(R.id.ders);
//         textView3= (TextView) findViewById(R.id.dersd);
////        TextView  textView4= (TextView) findViewById(R.id.dersg);
        String url="https://jsonplaceholder.cypress.io/todos/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                for (int i = 0; i < response.length(); i++) {
                    //getting the json object of the particular index inside the array
                    JSONObject heroObject = response.getJSONObject();

                    //creating a hero object and giving them the values from json object
                    Hero hero = new Hero(heroObject.getString("name"), heroObject.getString("imageurl"));

                    //adding the hero to herolist
                    heroList.add(hero);
                }
//                listView.(response.toString());

                try {
                    int userIds=response.getInt("userId");
                    int ids=response.getInt("id");
                    String titles=response.getString("title");
                    boolean completed=response.getBoolean("completed");

                    textView. setText(userIds);
                    textView2.setText(ids);
                    textView3.setText(titles);
//                    textView4.setText(completed);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
textView.setText("ERROR");
            }
        });
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);

       }
}
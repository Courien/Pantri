package com.example.pantri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
<<<<<<< HEAD
import android.widget.EditText;
=======
>>>>>>> 1051388d55564e43b733d76c5ce34a2c8f145b3d
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class View_API_Here extends AppCompatActivity
{

    private RecyclerView mRecylerView;
    private Adapter mAdapter;
    private ArrayList<Item> mItemList;
    private RequestQueue mRequestQueue;
    private String FoodType;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__api__here);

        mRecylerView = findViewById(R.id.recycler_view);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<>();

        FoodType = getIntent().getStringExtra("FoodType");

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();


    }

    public void message (View view){
        Intent firstScreen = new Intent(this, FourthScreen.class);
        startActivity(firstScreen);
    }
    private void parseJSON()
    {
<<<<<<< HEAD
        String Url = "https://api.edamam.com/search?q=&app_id=8f438d16&app_key=816f5456dd70c634fd34a8c20ead557f&from=0&to=25&calories=591-722&health=alcohol-free";
=======
        String Url = "https://api.edamam.com/search?q=" + FoodType + "&app_id=8f438d16&app_key=816f5456dd70c634fd34a8c20ead557f&from=0&to=25&calories=591-722&health=alcohol-free";
>>>>>>> 1051388d55564e43b733d76c5ce34a2c8f145b3d

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try
                {

                    JSONArray jsonArray = response.getJSONArray("hits");

                    String allIngredients = "";

                    for(int index = 0; index < jsonArray.length(); ++index)
                    {
                        JSONObject hit = jsonArray.getJSONObject(index);

                        String creatorName = hit.getJSONObject("recipe").getString("label");
                        String imageUrl = hit.getJSONObject("recipe").getString("image");
                        int calories = hit.getJSONObject("recipe").getInt("calories");


                        JSONArray recipe = hit.getJSONObject("recipe").getJSONArray("ingredientLines");//.getString("ingredientLines");


                        for(int index2 = 0; index2 < recipe.length(); ++index2)
                        {
                            String eachIngredient = recipe.getString(index2) + "\n";

                            allIngredients += eachIngredient;
                        }

                        mItemList.add(new Item(imageUrl, creatorName, allIngredients, calories));

                        allIngredients = "";
                    }

                    mAdapter = new Adapter(View_API_Here.this, mItemList);
                    mRecylerView.setAdapter(mAdapter);

                } catch (JSONException e)
                {

                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();

            }
        });

        mRequestQueue.add(request);
    }

}

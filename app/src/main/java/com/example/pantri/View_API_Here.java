package com.example.pantri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

public class View_API_Here extends AppCompatActivity implements Adapter.OnItemClickListener {

    public static String EXTRA_IMAGE_URL = "imageUrl";
    public static String EXTRA_MEAL = "meal";
    public static String EXTRA_RECIPE = "recipe";
    public static String EXTRA_CALORIES = "calories";
    public static String EXTRA_NUTRITION = "nutrition";
    public static String EXTRA_PREPARATION_STEPS = "prepingSteps";
    public static String EXTRA_FOODTYPE = "foodType";

    private RecyclerView mRecylerView;
    private Adapter mAdapter;
    private ArrayList<Item> mItemList;
    private ArrayList<ParseNutrientsJSON> mItemNutrientList;
    private RequestQueue mRequestQueue;
    private String FoodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__api__here);


        mRecylerView = findViewById(R.id.recycler_view);
        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(this));

        mItemList = new ArrayList<>();
        mItemNutrientList = new ArrayList<>();

        FoodType = getIntent().getStringExtra("FoodType");

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
        ParseNutrients();

    }

    public void message(View view)
    {
        finish();
    }

    private void parseJSON()
    {
        String Url = "https://api.edamam.com/search?q=" + FoodType + "&app_id=8f438d16&app_key=816f5456dd70c634fd34a8c20ead557f&from=0&to=10&calories=591-722&health=alcohol-free";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                try {

                    JSONArray jsonArray = response.getJSONArray("hits");

                    String allIngredients = "";

                    for (int index = 0; index < jsonArray.length(); ++index) {
                        JSONObject hit = jsonArray.getJSONObject(index);

                        String meal = hit.getJSONObject("recipe").getString("label");
                        String imageUrl = hit.getJSONObject("recipe").getString("image");
                        int calories = hit.getJSONObject("recipe").getInt("calories");
                        String prepingSteps = hit.getJSONObject("recipe").getString("url");


                        JSONArray recipe = hit.getJSONObject("recipe").getJSONArray("ingredientLines");


                        for (int index2 = 0; index2 < recipe.length(); ++index2) {
                            String eachIngredient = recipe.getString(index2) + "\n";

                            allIngredients += eachIngredient;
                        }


                        mItemList.add(new Item(imageUrl, meal, allIngredients, calories, prepingSteps));

                        allIngredients = "";
                    }

                    mAdapter = new Adapter(View_API_Here.this, mItemList);
                    mRecylerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(View_API_Here.this);

                } catch (JSONException e) {

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

    void ParseNutrients()
    {
        String Url = "https://api.edamam.com/search?q=" + FoodType + "&app_id=8f438d16&app_key=816f5456dd70c634fd34a8c20ead557f&from=0&to=10&calories=591-722&health=alcohol-free";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {

                try {

                    JSONArray jsonArray = response.getJSONArray("hits");


                    for (int index = 0; index < jsonArray.length(); ++index)
                    {


                        JSONObject hit = jsonArray.getJSONObject(index);


                        StringBuilder nutrientsString = new StringBuilder();
                        JSONArray nutrientArray = hit.getJSONObject("recipe").getJSONArray("digest");

                        for (int index3 = 0; index3 < nutrientArray.length(); ++index3)
                        {
                            JSONObject nutrientsObject = nutrientArray.getJSONObject(index3);

                            int amountOfNutrient;

                            nutrientsString.append(nutrientsObject.getString("label"));
                            nutrientsString.append(": ");
                            amountOfNutrient = nutrientsObject.getInt("total");
                            nutrientsString.append(amountOfNutrient);
                            nutrientsString.append(" ");
                            nutrientsString.append(nutrientsObject.getString("unit"));
                            nutrientsString.append("\n");

                        }

                        mItemNutrientList.add(new ParseNutrientsJSON(nutrientsString.toString()));

                    }


                } catch (JSONException e) {
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

    @Override
    public void onItemClick(int position)
    {
        Intent detailIntent = new Intent(this, DetailActivity.class);

        Item clickedItem = mItemList.get(position);

        ParseNutrientsJSON clickedItemNutrients = mItemNutrientList.get(position);

        detailIntent.putExtra(EXTRA_IMAGE_URL, clickedItem.getImageURL());
        detailIntent.putExtra(EXTRA_MEAL, clickedItem.getMeal());
        detailIntent.putExtra(EXTRA_RECIPE, clickedItem.getRecipe());
        detailIntent.putExtra(EXTRA_CALORIES, clickedItem.getcalories());
        detailIntent.putExtra(EXTRA_NUTRITION, clickedItemNutrients.getNutritionValue());
        detailIntent.putExtra(EXTRA_PREPARATION_STEPS, clickedItem.getPreparationSteps());
        detailIntent.putExtra(EXTRA_FOODTYPE, FoodType);

        startActivity(detailIntent);
    }

}

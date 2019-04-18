package com.example.pantri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.pantri.View_API_Here.EXTRA_CALORIES;
import static com.example.pantri.View_API_Here.EXTRA_MEAL;
import static com.example.pantri.View_API_Here.EXTRA_RECIPE;
import static com.example.pantri.View_API_Here.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String imageUrl = getIntent().getStringExtra(EXTRA_URL);

        String recipe = getIntent().getStringExtra(EXTRA_RECIPE);

        String meal = getIntent().getStringExtra(EXTRA_MEAL);

        int calories = getIntent().getIntExtra(EXTRA_CALORIES, 0);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewMeal = findViewById(R.id.textView_Meal_detail);
        TextView textViewCalories = findViewById(R.id.textView_calories_detail);
        TextView textViewRecipe = findViewById(R.id.textView_recipe_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewMeal.setText(meal);
        textViewRecipe.setText("Recipe: " + recipe);
        textViewCalories.setText("Calories: " + calories);
    }
}

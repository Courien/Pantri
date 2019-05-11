package com.example.pantri;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.pantri.View_API_Here.EXTRA_CALORIES;
import static com.example.pantri.View_API_Here.EXTRA_IMAGE_URL;
import static com.example.pantri.View_API_Here.EXTRA_MEAL;
import static com.example.pantri.View_API_Here.EXTRA_NUTRITION;
import static com.example.pantri.View_API_Here.EXTRA_PREPARATION_STEPS;
import static com.example.pantri.View_API_Here.EXTRA_RECIPE;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        final String recipe = getIntent().getStringExtra(EXTRA_RECIPE);
        final String meal = getIntent().getStringExtra(EXTRA_MEAL);
        final int calories = getIntent().getIntExtra(EXTRA_CALORIES, 0);
        final String nutrition = getIntent().getStringExtra(EXTRA_NUTRITION);
        final String prepingSteps = getIntent().getStringExtra(EXTRA_PREPARATION_STEPS);


        final Button backButton = (Button) findViewById(R.id.BackButton_detail);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                finish();
            }
        });


        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewMeal = findViewById(R.id.textView_Meal_detail);
        TextView textViewNutrition = (TextView) findViewById(R.id.textView_view_nutrition);
        TextView textViewRecipe = findViewById(R.id.textView_recipe_detail);
        TextView preparation = (TextView) findViewById(R.id.Preperation_Steps_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewMeal.setText(meal);
        textViewRecipe.setText("Recipe: " + recipe);
        preparation.setText(prepingSteps);


        SpannableNutrientsLink(textViewNutrition, nutrition, calories);
        SpannablePrepingStepsLink(prepingSteps, preparation);
    }


    // Create spannable string methods
    public void SpannableNutrientsLink(TextView atextViewNutrition, final String anutrition, final int acalories)
    {
        SpannableString nutritionSpanString = new SpannableString(atextViewNutrition.getText());

        ClickableSpan clickableNutrition = new ClickableSpan()
        {
            @Override
            public void onClick(@NonNull View widget)
            {
                Intent goToNutrients = new Intent(DetailActivity.this, Nutrients.class);

                goToNutrients.putExtra(EXTRA_NUTRITION, anutrition);
                goToNutrients.putExtra(EXTRA_CALORIES, acalories);


                startActivity(goToNutrients);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);

                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };
        nutritionSpanString.setSpan(clickableNutrition, 0, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        atextViewNutrition.setText(nutritionSpanString);
        atextViewNutrition.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void SpannablePrepingStepsLink(final String aprepingSteps, TextView apreparation)
    {
        String clickableWords = "Preperation Steps (by clicking this you will exit PANTRI).";
        SpannableString spanClickableWords = new SpannableString(clickableWords);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget)
            {
                WebView goToWeb = new WebView(DetailActivity.this);

                setContentView(goToWeb);

                goToWeb.loadUrl(aprepingSteps);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);

                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };

        spanClickableWords.setSpan(clickableSpan1, 0, 58, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        apreparation.setText(spanClickableWords);
        apreparation.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
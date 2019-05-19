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
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import static com.example.pantri.View_API_Here.EXTRA_CALORIES;
import static com.example.pantri.View_API_Here.EXTRA_IMAGE_URL;
import static com.example.pantri.View_API_Here.EXTRA_MEAL;
import static com.example.pantri.View_API_Here.EXTRA_NUTRITION;
import static com.example.pantri.View_API_Here.EXTRA_PREPARATION_STEPS;
import static com.example.pantri.View_API_Here.EXTRA_RECIPE;
import static com.example.pantri.View_API_Here.EXTRA_SERVING_SIZE;

public class DetailActivity extends AppCompatActivity {


    //private EditText Thing;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Thing = (EditText) findViewById(R.id.Thing);
        //db = FirebaseFirestore.getInstance();
        final String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        final String recipe = getIntent().getStringExtra(EXTRA_RECIPE);
        final String meal = getIntent().getStringExtra(EXTRA_MEAL);
        final int calories = getIntent().getIntExtra(EXTRA_CALORIES, 0);
        final String nutrition = getIntent().getStringExtra(EXTRA_NUTRITION);
        final String prepingSteps = getIntent().getStringExtra(EXTRA_PREPARATION_STEPS);
        int servingSize = getIntent().getIntExtra(EXTRA_SERVING_SIZE, 0);


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

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewMeal.setText(meal);
        textViewRecipe.setText("Serving size: " + servingSize + "\n" + "\n" + "Recipe\t(If possible, scroll down to view entire recipe):" + "\n" + "\n" + recipe);


        SpannableNutrientsLink(textViewNutrition, nutrition, calories, servingSize);
        SpannablePrepingStepsLink(preparation, prepingSteps, imageUrl, recipe, meal, calories, nutrition, servingSize);
    }


    // Create spannable string methods
    public void SpannableNutrientsLink(TextView atextViewNutrition, final String anutrition, final int acalories, final int servingSize)
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
                goToNutrients.putExtra(EXTRA_SERVING_SIZE, servingSize);

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


    public void SpannablePrepingStepsLink(final TextView apreparation, final String prepingSteps, final String imageUrl, final String recipe, final String meal, final int calories, final String nutrition, final int servingSize)
    {
        String clickableWords = "Preperation Steps (by clicking this link you will EXIT PANTRI).";

        ForegroundColorSpan exitColor = new ForegroundColorSpan(Color.RED);

        SpannableString spanClickableWords = new SpannableString(clickableWords);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget)
            {
                WebView goToWeb = new WebView(DetailActivity.this);

                setContentView(goToWeb);

                goToWeb.loadUrl(prepingSteps);

                finish();

                Intent backToDetailView = new Intent(DetailActivity.this, DetailActivity.class);

                backToDetailView.putExtra(EXTRA_PREPARATION_STEPS, prepingSteps);
                backToDetailView.putExtra(EXTRA_IMAGE_URL, imageUrl);
                backToDetailView.putExtra(EXTRA_RECIPE, recipe);
                backToDetailView.putExtra(EXTRA_MEAL, meal);
                backToDetailView.putExtra(EXTRA_CALORIES, calories);
                backToDetailView.putExtra(EXTRA_NUTRITION, nutrition);
                backToDetailView.putExtra(EXTRA_SERVING_SIZE, servingSize);

                startActivity(backToDetailView);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds)
            {
                super.updateDrawState(ds);

                ds.setUnderlineText(false);
                ds.setColor(Color.BLUE);
            }
        };


        spanClickableWords.setSpan(clickableSpan1, 0, 63, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanClickableWords.setSpan(exitColor, 50, 54, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        apreparation.setText(spanClickableWords);
        apreparation.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*public void test( View v)
    {

        if (Thing.getText().toString().equals(""))
        {
            Thing.setText("Please Enter your username");
        }
        String thing2 = Thing.getText().toString().trim();

        Map<String,Object> test = new HashMap<>();
        test.put("test", getIntent().getStringExtra("imageUrl"));
        test.put("test",getIntent().getStringExtra("meal") );
        test.put("test", getIntent().getIntExtra("calories", 0));
        test.put("test", getIntent().getStringExtra("nutrition)"));
        test.put("test", getIntent().getStringExtra("prepingSteps"));



        db.collection("user").document(thing2).update(test);

    }*/
}
package com.example.pantri;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class Nutrients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);


        final String preparationSteps = getIntent().getStringExtra("preparationSteps");
        int calories = getIntent().getIntExtra("calorie", 0);
        String nutrition = getIntent().getStringExtra("nutrients");


        TextView nutrientText = (TextView) findViewById(R.id. Nutrients);
        TextView prepingSteps = (TextView) findViewById(R.id. Preperation_Steps);


        nutrientText.setText(nutrition);


        String clickableWords = "Preperation Steps (by clicking this you will exit PANTRI).";
        SpannableString spanClickableWords = new SpannableString(clickableWords);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget)
            {
                WebView goToWeb = new WebView(Nutrients.this);

                setContentView(goToWeb);

                goToWeb.loadUrl(preparationSteps);
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
        prepingSteps.setText(spanClickableWords);
        prepingSteps.setMovementMethod(LinkMovementMethod.getInstance());

    }
}
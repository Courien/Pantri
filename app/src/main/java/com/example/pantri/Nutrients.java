package com.example.pantri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.pantri.View_API_Here.EXTRA_NUTRITION;

public class Nutrients extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrients);

        final String nutrition = getIntent().getStringExtra(EXTRA_NUTRITION);


        TextView nutrientText = (TextView) findViewById(R.id. Nutrients);
        nutrientText.setText(nutrition);


        Button backButton = (Button) findViewById(R.id.nutrients_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

}
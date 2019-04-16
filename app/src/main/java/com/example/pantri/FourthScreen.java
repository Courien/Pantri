package com.example.pantri;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FourthScreen extends AppCompatActivity {

    public static TextView APItext;
    private EditText FoodSearching;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);

        APItext = (TextView) findViewById(R.id.API_info_here);

        Button buttonGo = (Button) findViewById(R.id.button_Go);

        FoodSearching = (EditText) findViewById(R.id.Search_Food);


        buttonGo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StartSearch(v, FoodSearching.getText().toString());
            }
        });
    }
    public void sendMessage (View view){
        Intent firstScreen = new Intent(this, SecondScreen.class);
        startActivity(firstScreen);
    }


    public void StartSearch(View view, String FoodType)
    {

            //API_info process = new API_info();
            //process.execute();

            Intent View_API = new Intent(this, View_API_Here.class);

            View_API.putExtra("FoodType", FoodSearching.getText().toString());

            startActivity(View_API);



    }
}

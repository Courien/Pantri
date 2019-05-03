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
    private TextView NoSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);

        //APItext = (TextView) findViewById(R.id.API_info_here);

        Button buttonGo = (Button) findViewById(R.id.button_Go);

        FoodSearching = (EditText) findViewById(R.id.Search_Food);

        NoSearch = (TextView) findViewById(R.id._invalidEntry);


        buttonGo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(FoodSearching.getText().toString().equals(""))
                {
                    NoSearch.setText("Invalid entry");
                }
                else
                {
                    StartSearch(v);
                }

            }
        });
    }
    public void sendMessage (View view){
        Intent firstScreen = new Intent(this, SecondScreen.class);
        startActivity(firstScreen);
    }


    public void StartSearch(View view)
    {

        //if(FoodType.equals("chicken"))
        //{

        //API_info process = new API_info();
        //process.execute();

        Intent View_API = new Intent(this, View_API_Here.class);

        View_API.putExtra("FoodType", FoodSearching.getText().toString());

        startActivity(View_API);
        //}

    }

    public void SearchChicken(View view)
    {
        FoodSearching.setText("Chicken");

        Intent searchChicken = new Intent(this, View_API_Here.class);

        searchChicken.putExtra("FoodType", FoodSearching.getText().toString());

        startActivity(searchChicken);
    }

    public void SearchPasta(View view)
    {
        FoodSearching.setText("Pasta");

        Intent searchPasta = new Intent(this, View_API_Here.class);

        searchPasta.putExtra("FoodType", FoodSearching.getText().toString());

        startActivity(searchPasta);
    }

    public void SearchSteak(View view)
    {
        FoodSearching.setText("Steak");

        Intent searchSteak = new Intent(this, View_API_Here.class);

        searchSteak.putExtra("FoodType", FoodSearching.getText().toString());

        startActivity(searchSteak);
    }

    public void SearchShrimp(View view)
    {
        FoodSearching.setText("Shrimp");

        Intent searchPizza = new Intent(this, View_API_Here.class);

        searchPizza.putExtra("FoodType", FoodSearching.getText().toString());

        startActivity(searchPizza);
    }


}
package com.example.pantri;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

    }
    public void sendMessage (View view){
        Intent firstScreen = new Intent(this, SecondScreen.class);
        startActivity(firstScreen);
    }

    public void Calculate (View view) {

        String View = "Calories:";
        String View2 = " ";
        String View1 = " ";
        EditText Weight = (EditText) findViewById(R.id.weight);
        try
        {
            Integer.parseInt(Weight.getText().toString());

        }
        catch (NumberFormatException nfe)
        {
            Weight.setText("0");
        }
        EditText Height = (EditText) findViewById(R.id.height);
        try{
            Integer.parseInt(Height.getText().toString());
        }
        catch (NumberFormatException nfe)
        {
            Height.setText("0");
        }
        EditText Height2  = (EditText) findViewById(R.id.height2);
        try{
            Integer.parseInt(Height2.getText().toString());
        }
        catch (NumberFormatException nfe)
        {
            Height2.setText("0");
        }
        EditText Age = (EditText) findViewById(R.id.age);
        try{
            Integer.parseInt(Age.getText().toString());
        }
        catch (NumberFormatException nfe)
        {
            Age.setText("0");
        }
        TextView CalorieInfo = (TextView) findViewById(R.id.result);
        TextView Carbs = (TextView) findViewById(R.id.result2);
        TextView Fats = (TextView) findViewById(R.id.result3);
        TextView Viewer = (TextView) findViewById(R.id.identiefier);
        TextView Viewer2 = (TextView) findViewById(R.id.identifier2);
        TextView Viewer3 = (TextView) findViewById(R.id.identeifier);
        Fats.setFocusable(false);
        Carbs.setFocusable(false);
        CalorieInfo.setFocusable(false);

        if (Weight.getText()!=null && Height.getText()!=null && Age.getText()!=null) {
            int WeightToInt = Integer.parseInt(Weight.getText().toString());
            if (WeightToInt > 400) {
                WeightToInt = 400;
                Weight.setText(Integer.toString(WeightToInt));

            }

            int HeightToInt = Integer.parseInt(Height.getText().toString());
            if (HeightToInt > 7)
            {
                HeightToInt = 7;
                Height.setText(Integer.toString(HeightToInt));
            }
            int Height2Tointt = Integer.parseInt(Height2.getText().toString());

            if (Height2Tointt >11)
            {
                Height2Tointt = 11;
                Height2.setText(Integer.toString(Height2Tointt));
            }
            int AgeInt = Integer.parseInt(Age.getText().toString());

            if (AgeInt >100)
            {
                AgeInt = 100;
                Age.setText(Integer.toString(AgeInt));
            }
            double CalInfo = 66 + (6.23 * WeightToInt) + (12.7 * ((HeightToInt * 12) + Height2Tointt)) - (6.8 * AgeInt);
            CalorieInfo.setText(Double.toString(Math.floor((CalInfo * 100) / 100)));
            Carbs.setText(View1);
            Fats.setText(View1);
            Viewer.setText(View);
            Viewer2.setText(View2);
            Viewer3.setText(View1);
            Carbs.setVisibility(Carbs.INVISIBLE);
            Fats.setVisibility(Fats.INVISIBLE);

        }
        else {


        }

    }
    public void Calculate2 (View view) {
        String View = "Protein(g):";
        String View1 = "Carbs(g):";
        String View2 = "Fats(g):";

        EditText Weight = (EditText) findViewById(R.id.weight);

        if (Weight.getText() != null) {

          try
          {
              Integer.parseInt(Weight.getText().toString());

          }
          catch (NumberFormatException nfe)
          {
              Weight.setText("0");
          }
            int WeighttoINt = Integer.parseInt(Weight.getText().toString());

            TextView Protien = (TextView) findViewById(R.id.result);
            TextView Carbs = (TextView) findViewById(R.id.result2);
            TextView Fats = (TextView) findViewById(R.id.result3);
            TextView Viewer1 = (TextView) findViewById(R.id.identiefier);
            TextView Viewer2 = (TextView) findViewById(R.id.identifier2);
            TextView Viewer3 = (TextView) findViewById(R.id.identeifier);
            double Protienintake = (WeighttoINt * 4) * .36;
            double Fatintake = (WeighttoINt * 9) * .30;
            double CarbIntake = (WeighttoINt * 4) * .45;
            int carbtoint = (int)CarbIntake;
            Protien.setText(String.format("%.0f", Protienintake));
            Carbs.setText(String.format("%.0f", CarbIntake));
            Fats.setText(String.format("%.0f", Fatintake));
            Viewer1.setText(View);
            Viewer2.setText(View1);
            Viewer3.setText(View2);
            Carbs.setVisibility(Carbs.VISIBLE);
            Fats.setVisibility(Fats.VISIBLE);
        } else {
            TextView Protien = (TextView) findViewById(R.id.result);
            TextView Carbs = (TextView) findViewById(R.id.result2);
            TextView Fats = (TextView) findViewById(R.id.result3);
            Carbs.setVisibility(Carbs.VISIBLE);
            Fats.setVisibility(Fats.VISIBLE);
            Protien.setText("0");
            Carbs.setText("0");
            Fats.setText("0");
            Fats.setFocusable(false);
            Protien.setFocusable(false);
            Carbs.setFocusable(false);


        }

        }



    }

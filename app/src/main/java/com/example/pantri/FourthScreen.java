package com.example.pantri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FourthScreen extends AppCompatActivity {

    public static TextView APItext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);

        APItext = (TextView) findViewById(R.id.API_info_here);

        API_info process = new API_info();
        process.execute();
    }
}

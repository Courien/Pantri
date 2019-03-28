package com.example.pantri;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class API_info extends AsyncTask<Void, Void, Void>
{

    StringBuilder line;

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {

            URL url = new URL("https://api.edamam.com/search?q=chicken&app_id=8f438d16&app_key=816f5456dd70c634fd34a8c20ead557f&from=0&to=3&calories=591-722&health=alcohol-free");

            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = httpUrlConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            line = new StringBuilder();

            while(bufferedReader.readLine() != null)
            {
                line.append(bufferedReader.readLine());
            }

        } catch (MalformedURLException e)
        {

            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);

        FourthScreen.APItext.setText(this.line);

    }
}
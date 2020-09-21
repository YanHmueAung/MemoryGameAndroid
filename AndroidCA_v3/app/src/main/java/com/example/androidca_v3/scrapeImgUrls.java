package com.example.androidca_v3;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class scrapeImgUrls extends AsyncTask<String, Integer, String[]> {
    private WeakReference<AppCompatActivity> caller;

    public scrapeImgUrls(WeakReference<AppCompatActivity> caller) {
        this.caller = caller;
    }

    @Override
    protected String[] doInBackground(String... urls) {
        String[] imageUrls = new String[21];
        int numOfImages = 0;
        String customUA = "Mozilla/5.0 (X11; Linux i686) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31";

        try {
            // extract html from the URL
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // website will allow this connection as this allows us to be recognized as a browser
            connection.addRequestProperty("User-Agent",customUA);
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            // extract the URLs of the first 20 images
            String line;
            int startIndex, endIndex;
            while ((line = br.readLine()) != null && numOfImages<21) {
                if (line.contains("<img src=")) {
                    startIndex = line.indexOf("<img src=") + 10;
                    endIndex = line.indexOf("\"", startIndex) - 1;
                    imageUrls[numOfImages] = line.substring(startIndex, endIndex+1);
                    numOfImages++;
                }
            }
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

        return imageUrls;
    }

    @Override
    protected void onPostExecute(String[] imageUrls) {
        MainActivity parent = (MainActivity) caller.get();
        parent.onImageUrlsExtracted(imageUrls);
    }
}

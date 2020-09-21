package com.example.androidca_v3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImgs extends AsyncTask<String, Integer, String[]> {
    private WeakReference<AppCompatActivity> caller;

    private String[] imagePaths = new String[21];
    public DownloadImgs(WeakReference<AppCompatActivity> caller) {
        this.caller = caller;
    }

    @Override
    protected String[] doInBackground(String... urls) {
        int count=urls.length;
        try {
            for (int i=1; i<count; i++) {
                URL url = new URL(urls[i]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                BufferedInputStream bufferedInputStrm = new BufferedInputStream(input, 2048); // 2048 is the buffer size

                String imagePath = caller.get().getFilesDir() + "/image" + Integer.toString(i) + ".jpg";
                imagePaths[i] = imagePath;
                OutputStream out = new FileOutputStream(imagePath); // Creates a file output stream to write to the file with the specified name.

                int readLen;
                byte[] data = new byte[1024];
                // bufferedInputStrm.read(data): Reads some number of bytes from the input stream and stores them
                // into a buffer array data.
                while ((readLen = bufferedInputStrm.read(data)) != -1) {
                    // Writes readLen bytes from the data byte array starting at offset 0 to this output stream.
                    out.write(data, 0, readLen);
                }

                input.close();
                out.close();
                publishProgress(i);//0 to 20 images
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imagePaths;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        MainActivity parent = (MainActivity) caller.get();

        int i = values[0];

        // convert the image to bitmap
        Bitmap bitmap = BitmapFactory.decodeFile(imagePaths[i]);

        // display the image on screen
        String imageViewId = "imageView" + i;
        int resId = parent.getResources().getIdentifier(imageViewId, "id", parent.getPackageName());
        ImageView imageView = parent.findViewById(resId);
        imageView.setImageBitmap(bitmap);

        // set OnClickListener on the image
        imageView.setOnClickListener(parent);

        // update progress bar and text
        ProgressBar bar = parent.findViewById(R.id.progressBar);
        bar.setProgress(i);

        TextView textView = parent.findViewById(R.id.textView3);
        textView.setText(Integer.toString(i));
    }
}

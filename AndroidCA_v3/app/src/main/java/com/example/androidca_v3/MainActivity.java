package com.example.androidca_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // contains file paths of the 20 downloaded images
    private String[] imagePaths = new String[21];

    // contains file paths of selected images (up to 6)
    private List<String> selectedImages = new ArrayList<>();

    scrapeImgUrls extractTask = null;
    DownloadImgs downloadTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String imagePath;
        for (int i=1; i<imagePaths.length; i++) {
            imagePath = getFilesDir() + "/image" + Integer.toString(i) + ".jpg";
            imagePaths[i] = imagePath;
        }

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hide keyboard after pressing fetch
                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // stop any downloading that is in progress
                if (extractTask != null)
                    extractTask.cancel(true);
                if (downloadTask != null)
                    downloadTask.cancel(true);

                // clear selected images
                selectedImages.clear();

                // clear images from screen
                for (int i=1; i<=20; i++) {
                    String imageViewId = "imageView" + i;
                    int resId = getResources().getIdentifier(imageViewId, "id", getPackageName());
                    ImageView imageView = findViewById(resId);

                    imageView.setImageDrawable(null);
                    //imageView.setBackground(null);
                    imageView.setTag("not_selected");
                    imageView.clearColorFilter();
                }

                // clear progress bar and text
                ProgressBar bar = findViewById(R.id.progressBar);
                bar.setProgress(0);

                TextView textView = findViewById(R.id.textView3);
                textView.setText(Integer.toString(0));

                // get the url string that user entered
                EditText urlView = findViewById(R.id.editText2);
                String url = urlView.getText().toString();

                // extract the image urls of first 20 images
                extractTask = new scrapeImgUrls(new WeakReference<AppCompatActivity>(MainActivity.this));
                extractTask.execute(url);
            }
        });
    }
    // call this after 20 image URLs are extracted from html
    public void onImageUrlsExtracted(String[] imageUrls) {
        // download the 20 images to internal storage and display on screen
        downloadTask = new DownloadImgs(new WeakReference<AppCompatActivity>(MainActivity.this));
        downloadTask.execute(imageUrls);
    }

    @Override
    public void onClick(View v) {
        final int semiTransparentGrey = Color.argb(155, 185, 185, 185);
        ImageView view = (ImageView) v;

        // get last number of ImageView Id because that indicates the position
        int resId = v.getId();
        String imageViewId = getResources().getResourceEntryName(resId);
        int position = Integer.parseInt(imageViewId.substring(9));

        // toggle the filter on the image based on selection
        if (v.getTag() == "not_selected") {
            view.setColorFilter(semiTransparentGrey, PorterDuff.Mode.SRC_ATOP);
            v.setTag("selected");
            selectedImages.add(imagePaths[position]);

            // start playing game if 6 images are selected
            if (selectedImages.size() == 6) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.putExtra("imagePaths", selectedImages.toArray(new String[6]));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        } else {
            view.clearColorFilter();
            v.setTag("not_selected");
            selectedImages.remove(imagePaths[position]);
        }
    }
}

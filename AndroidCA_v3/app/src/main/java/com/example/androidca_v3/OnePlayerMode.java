package com.example.androidca_v3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OnePlayerMode extends AppCompatActivity implements Chronometer.OnChronometerTickListener{
    private Chronometer chronometer;

    MediaPlayer mediaPlayer;
    TextView tv_p1;
    ImageView iv_11, iv_12,iv_13, iv_14, iv_21, iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34;
    Integer[] tilesArray = {101, 102, 103, 104, 105, 106, 201, 202, 203, 204, 205, 206};

    //boolean values to keep track of correct matches made by player
    boolean iv_11flip, iv_12flip, iv_13flip,  iv_14flip, iv_21flip, iv_22flip, iv_23flip, iv_24flip,
            iv_31flip, iv_32flip, iv_33flip, iv_34flip;

    List<Bitmap> downloadedBitmaps=new ArrayList<Bitmap>();

    int firstCard, secondCard;
    int clickedFirst,clickedSecond;
    int tileNumber = 1;
    boolean timerStart = false;

    int playerPoints = 0;
    RelativeLayout rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player_mode);
        initView();

        chronometer.setBase(SystemClock.elapsedRealtime());

        // setting the score.
        tv_p1 = (TextView) findViewById(R.id.tv_p1);

        rv=findViewById(R.id.one_player_relativeLayout);
        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);
        iv_14 = (ImageView) findViewById(R.id.iv_14);
        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);
        iv_24 = (ImageView) findViewById(R.id.iv_24);
        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);
        iv_34 = (ImageView) findViewById(R.id.iv_34);

        iv_11.setTag("0");
        iv_12.setTag("1");
        iv_13.setTag("2");
        iv_14.setTag("3");
        iv_21.setTag("4");
        iv_22.setTag("5");
        iv_23.setTag("6");
        iv_24.setTag("7");
        iv_31.setTag("8");
        iv_32.setTag("9");
        iv_33.setTag("10");
        iv_34.setTag("11");


        assignBitmapToTile();
        Collections.shuffle(Arrays.asList(tilesArray));

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_11,theCard);
            }
        });

        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_12,theCard);
            }
        });

        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_13,theCard);
            }
        });

        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_14,theCard);
            }
        });

        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_21,theCard);
            }
        });

        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_22,theCard);
            }
        });

        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_23,theCard);
            }
        });

        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_24,theCard);
            }
        });

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_31,theCard);
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_32,theCard);
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_33,theCard);
            }
        });

        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard = Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_34,theCard);
            }
        });

    }
    private void assignBitmapToTile(ImageView iv, int tile ) {
        //set the correct image to the image view
        if (timerStart==false){
            //if timer has not started, start it.Then set timer flag to true.
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            timerStart=true;
        }
        else{
            chronometer.start();
        }

        //set the correct Bitmap to the imageView
        if (tilesArray[tile]==101){
            iv.setImageBitmap(downloadedBitmaps.get(0));
        }else if (tilesArray[tile]==102){
            iv.setImageBitmap(downloadedBitmaps.get(1));
        }else if (tilesArray[tile]==103){
            iv.setImageBitmap(downloadedBitmaps.get(2));
        }else if (tilesArray[tile]==104){
            iv.setImageBitmap(downloadedBitmaps.get(3));
        }else if (tilesArray[tile]==105){
            iv.setImageBitmap(downloadedBitmaps.get(4));
        }else if (tilesArray[tile]==106){
            iv.setImageBitmap(downloadedBitmaps.get(5));
        }else if (tilesArray[tile]==201){
            iv.setImageBitmap(downloadedBitmaps.get(0));
        }else if (tilesArray[tile]==202){
            iv.setImageBitmap(downloadedBitmaps.get(1));
        }else if (tilesArray[tile]==203){
            iv.setImageBitmap(downloadedBitmaps.get(2));
        }else if (tilesArray[tile]==204){
            iv.setImageBitmap(downloadedBitmaps.get(3));
        }else if (tilesArray[tile]==205){
            iv.setImageBitmap(downloadedBitmaps.get(4));
        }else if (tilesArray[tile]==206){
            iv.setImageBitmap(downloadedBitmaps.get(5));
        }

        //Opening the first tile
        if (tileNumber == 1) {
            firstCard = tilesArray[tile];
            if (firstCard > 200) {
                firstCard = firstCard - 100;
            }
            tileNumber = 2;
            clickedFirst = tile;

            iv.setEnabled(false);

            //Opening the second card.
        } else if (tileNumber == 2) {
            secondCard = tilesArray[tile];
            if (secondCard > 200) {
                secondCard = secondCard - 100;
            }
            tileNumber = 1;
            clickedSecond = tile;

            //disable the rest of the imageViews from being clicked
            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    calculate();
                }
            }, 1000);
        }
    }
    private void calculate() {
        if (firstCard == secondCard) {
            mediaPlayer = MediaPlayer.create(OnePlayerMode.this, R.raw.recording);
            mediaPlayer.start();//play sound if correct match

            //flash green if correct match is made
            animateBackgroundColor(rv, "backgroundColor",Color.WHITE,Color.parseColor("#397e56"));

            //set boolean to true if there is a match.
            if (clickedFirst == 0) {
                iv_11flip = true;
            } else if (clickedFirst == 1) {
                iv_12flip = true;
            } else if (clickedFirst == 2) {
                iv_13flip = true;
            } else if (clickedFirst == 3) {
                iv_14flip = true;
            } else if (clickedFirst == 4) {
                iv_21flip = true;
            } else if (clickedFirst == 5) {
                iv_22flip = true;
            } else if (clickedFirst == 6) {
                iv_23flip = true;
            } else if (clickedFirst == 7) {
                iv_24flip = true;
            } else if (clickedFirst == 8) {
                iv_31flip = true;
            } else if (clickedFirst == 9) {
                iv_32flip = true;
            } else if (clickedFirst == 10) {
                iv_33flip = true;
            } else if (clickedFirst == 11) {
                iv_34flip = true;
            }

            if (clickedSecond == 0) {
                iv_11flip = true;
            } else if (clickedSecond == 1) {
                iv_12flip = true;
            } else if (clickedSecond == 2) {
                iv_13flip = true;
            } else if (clickedSecond == 3) {
                iv_14flip = true;
            } else if (clickedSecond == 4) {
                iv_21flip = true;
            } else if (clickedSecond == 5) {
                iv_22flip = true;
            } else if (clickedSecond == 6) {
                iv_23flip = true;
            } else if (clickedSecond == 7) {
                iv_24flip = true;
            } else if (clickedSecond == 8) {
                iv_31flip = true;
            } else if (clickedSecond == 9) {
                iv_32flip = true;
            } else if (clickedSecond == 10) {
                iv_33flip = true;
            } else if (clickedSecond == 11) {
                iv_34flip = true;
            }


            playerPoints++;
            tv_p1.setText("Matches:" + playerPoints+"/6");


        } else {
            //flash red in background if wrong match is made
            animateBackgroundColor(rv, "backgroundColor", Color.WHITE,Color.parseColor("#d13841"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (iv_11flip == false) {
                        iv_11.setImageResource(R.drawable.cardback);
                    }
                    if (iv_12flip == false) {
                        iv_12.setImageResource(R.drawable.cardback);
                    }
                    if (iv_13flip == false) {
                        iv_13.setImageResource(R.drawable.cardback);
                    }
                    if (iv_14flip == false) {
                        iv_14.setImageResource(R.drawable.cardback);
                    }
                    if (iv_21flip == false) {
                        iv_21.setImageResource(R.drawable.cardback);
                    }
                    if (iv_22flip == false) {
                        iv_22.setImageResource(R.drawable.cardback);
                    }
                    if (iv_23flip == false) {
                        iv_23.setImageResource(R.drawable.cardback);
                    }
                    if (iv_24flip == false) {
                        iv_24.setImageResource(R.drawable.cardback);
                    }
                    if (iv_31flip == false) {
                        iv_31.setImageResource(R.drawable.cardback);
                    }
                    if (iv_32flip == false) {
                        iv_32.setImageResource(R.drawable.cardback);
                    }
                    if (iv_33flip == false) {
                        iv_33.setImageResource(R.drawable.cardback);
                    }
                    if (iv_34flip == false) {
                        iv_34.setImageResource(R.drawable.cardback);
                    }
                }
            },800);

        }
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                iv_11.setEnabled(true);
                iv_12.setEnabled(true);
                iv_13.setEnabled(true);
                iv_14.setEnabled(true);
                iv_21.setEnabled(true);
                iv_22.setEnabled(true);
                iv_23.setEnabled(true);
                iv_24.setEnabled(true);
                iv_31.setEnabled(true);
                iv_32.setEnabled(true);
                iv_33.setEnabled(true);
                iv_34.setEnabled(true);
            }},800);
        checkEnd();
    }


    private void checkEnd(){
        if(playerPoints==6){//if points sum to 6, we reach the end of the game
            chronometer.stop();
            String time = chronometer.getText().toString();//show time

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OnePlayerMode.this);
            alertDialogBuilder.setMessage("Game over! \nYour score:" + playerPoints+"\nYou took : "+time  )
                    .setCancelable(false)
                    .setPositiveButton("OK ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            Intent intent = new Intent (getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                            //go back to first activity to download images.
                        }

                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


    }

    private void assignBitmapToTile(){

        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String[] filepaths=(String[]) extras.get("imagePaths");//file paths to downloaded images

        for(int i=0;i<filepaths.length;i++){
            downloadedBitmaps.add(i, BitmapFactory.decodeFile(filepaths[i]));
        }

    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        String time = chronometer.getText().toString();
        if (time.equals("00:00")) {
            Toast.makeText(OnePlayerMode.this, "Game start!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        chronometer.setOnChronometerTickListener(this);
    }

    protected void animateBackgroundColor(View v, String propertyName, int startColor, int endColor){

        // Initialize a new value animator of type int
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(
                v, // Target object
                propertyName, // Property name
                startColor, // Value
                endColor // Value
        );

        // Set value animator evaluator
        valueAnimator.setEvaluator(new ArgbEvaluator());
        // Set animation duration in milliseconds
        valueAnimator.setDuration(100);
        // Animation repeat count and mode
        valueAnimator.setRepeatCount(5);
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);

        // Finally, start the animation
        valueAnimator.start();
    }
}


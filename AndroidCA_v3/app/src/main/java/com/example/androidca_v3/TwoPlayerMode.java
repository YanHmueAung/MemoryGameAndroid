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
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TwoPlayerMode extends AppCompatActivity {

    //text views for players' scores
    TextView tv_p1,tv_p2;

    // imageViews of the tiles
    ImageView iv_11,iv_12,iv_13,iv_14,iv_21,iv_22,iv_23,iv_24,iv_31,iv_32,iv_33,iv_34;


    //array for the images
    Integer[] tilesArray ={101,102,103,104,105,106,201,202,203,204,205,206};

    //List to store decoded bitmaps that were previously downloaded
    List<Bitmap> downloadedBitmaps=new ArrayList<Bitmap>();


    int firstTile, secondTile;
    int clickedFirst,clickedSecond;
    int tileNumber =1;

    int turn=1;
    int player1points =0, player2points =0;
    RelativeLayout rv;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_mode);

        rv=findViewById(R.id.two_player_relativeLayout);
        tv_p1=(TextView)findViewById(R.id.tv_p1);//textViews of players' scores
        tv_p2=(TextView)findViewById(R.id.tv_p2);

        iv_11=(ImageView)findViewById(R.id.iv_11);//imageViews of the tiles
        iv_12=(ImageView)findViewById(R.id.iv_12);
        iv_13=(ImageView)findViewById(R.id.iv_13);
        iv_14=(ImageView)findViewById(R.id.iv_14);
        iv_21=(ImageView)findViewById(R.id.iv_21);
        iv_22=(ImageView)findViewById(R.id.iv_22);
        iv_23=(ImageView)findViewById(R.id.iv_23);
        iv_24=(ImageView)findViewById(R.id.iv_24);
        iv_31=(ImageView)findViewById(R.id.iv_31);
        iv_32=(ImageView)findViewById(R.id.iv_32);
        iv_33=(ImageView)findViewById(R.id.iv_33);
        iv_34=(ImageView)findViewById(R.id.iv_34);

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

        //load the card images
        loadImgIntoBitmaps();

        Collections.shuffle(Arrays.asList(tilesArray));
        tv_p2.setTextColor(Color.GRAY);

        iv_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int theCard= Integer.parseInt((String) view.getTag());
                assignBitmapToTile(iv_11,theCard);//assignBitmapToTile will assign the corresponding bitmaps to the tiles
            }
        });
        iv_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_12,theCard);


            }
        });
        iv_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_13,theCard);


            }
        });
        iv_14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_14,theCard);

            }
        });
        iv_21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_21,theCard);
            }
        });
        iv_22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_22,theCard);

            }
        });
        iv_23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_23,theCard);

            }
        });
        iv_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_24,theCard);

            }
        });
        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_31,theCard);


            }
        });
        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_32,theCard);


            }
        });
        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_33,theCard);


            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int theCard= Integer.parseInt((String) v.getTag());
                assignBitmapToTile(iv_34,theCard);


            }
        });

        Toast.makeText(TwoPlayerMode.this, "Player 1 start!", Toast.LENGTH_SHORT).show();
    }

    private void assignBitmapToTile(ImageView iv, int tile){

        //set the correct bitmap to the imageView
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


        //check which tile is selected and save it to a temporary variable
        if (tileNumber ==1){
            firstTile = tilesArray[tile];
            if (firstTile >200){
                firstTile = firstTile -100;
            }
            tileNumber =2;
            clickedFirst=tile;
            iv.setEnabled(false);

        }else if (tileNumber ==2){
            secondTile = tilesArray[tile];

            if (secondTile >200){
                secondTile = secondTile -100;
            }

            tileNumber =1;
            clickedSecond=tile;

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

            Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //check if the selected images are equal
                    calculate();
                }
            },1000);

        }
    }

    private void calculate(){
        if (firstTile == secondTile){
            mediaPlayer = MediaPlayer.create(TwoPlayerMode.this, R.raw.recording);
            mediaPlayer.start();//play sound if correct match is made

            // flash green in background if correct match is made
            animateBackgroundColor(rv, "backgroundColor",Color.WHITE,Color.parseColor("#397e56"));

            if (clickedFirst==0){
                //if correct match is made tiles are made invisible
                iv_11.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if (clickedFirst==11){
                iv_34.setVisibility(View.INVISIBLE);
            }


            if (clickedSecond==0){
                //if images are equal remove them and add point
                iv_11.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==1){
                iv_12.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==2){
                iv_13.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==3){
                iv_14.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==4){
                iv_21.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==5){
                iv_22.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==6){
                iv_23.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==7){
                iv_24.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==8){
                iv_31.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==9){
                iv_32.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==10){
                iv_33.setVisibility(View.INVISIBLE);
            }else if (clickedSecond==11){
                iv_34.setVisibility(View.INVISIBLE);
            }

            //add points to the correct player
            if (turn==1){
                player1points++;
                tv_p1.setText("P1 :"+ player1points);
            }else if (turn==2){
                player2points++;
                tv_p2.setText("P2 :"+ player2points);

            }

        }
        else {
            animateBackgroundColor(rv, "backgroundColor", Color.WHITE, Color.parseColor("#d13841"));
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    iv_11.setImageResource(R.drawable.cardback);
                    iv_12.setImageResource(R.drawable.cardback);
                    iv_13.setImageResource(R.drawable.cardback);
                    iv_14.setImageResource(R.drawable.cardback);

                    iv_21.setImageResource(R.drawable.cardback);
                    iv_22.setImageResource(R.drawable.cardback);
                    iv_23.setImageResource(R.drawable.cardback);
                    iv_24.setImageResource(R.drawable.cardback);

                    iv_31.setImageResource(R.drawable.cardback);
                    iv_32.setImageResource(R.drawable.cardback);
                    iv_33.setImageResource(R.drawable.cardback);
                    iv_34.setImageResource(R.drawable.cardback);

                    //change the player turn
                    if (turn == 1) {
                        turn = 2;
                        tv_p1.setTextColor(Color.GRAY);
                        tv_p1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);

                        tv_p2.setTextColor(Color.parseColor("#FBC501"));
                        tv_p2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,36);

                    } else if (turn == 2) {
                        turn = 1;
                        tv_p2.setTextColor(Color.GRAY);
                        tv_p2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);

                        tv_p1.setTextColor(Color.parseColor("#FBC501"));
                        tv_p1.setTextSize(TypedValue.COMPLEX_UNIT_DIP,36);
                    }

                }
            }, 800);

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


        //check if the game is over
        checkEnd();
    }

    private void checkEnd(){
        if (player1points + player2points ==6){//end game if total points is 6

            AlertDialog.Builder alertDialogBuilder =new AlertDialog.Builder(TwoPlayerMode.this);

            alertDialogBuilder.setMessage("Game over! Scores:\nP1: "+ player1points +"\nP2: "+ player2points)
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                            finish();
                        }
                    });//go back to main activity to download images

            AlertDialog alertDialog=alertDialogBuilder.create();
            alertDialog.show();
        }
    }



    private void loadImgIntoBitmaps(){
        //load and store downloaded images into bitmaps
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        String[] filepaths=(String[]) extras.get("imagePaths");

        for(int i=0;i<filepaths.length;i++){
            downloadedBitmaps.add(i,BitmapFactory.decodeFile(filepaths[i]));
        }
    }


    // flash green color in background if match is made
    // flash red color in background if wrong match is made
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

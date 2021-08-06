package com.farasatnovruzov.catchtheroadrunner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {
    TextView timeView;
    TextView scoreView;
    int second;
    int score;
    Runnable runnable;
    Handler handler;

//    ArrayList<String> imageList;

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16;
    ImageView imageArray[] = {imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        public void generateNumber(){
//            handler = new Handler();
//            run = new Runnable() {
//                @Override
//                public void run() {
//                    randomX = new Random().nextInt(1000 - 0) + 0;
//                    randomY = new Random().nextInt(1000 - 0) + 0;
//                    handler.postDelayed(this, 400);
//                    kenny.setX(randomX);
//                    kenny.setY(randomY);
//                    textView2.setText(Integer.toString(score));
//                }
//            };
//            handler.post(run);
//
//        }

        timeView = findViewById(R.id.timeView);
        scoreView = findViewById(R.id.scoreView);
        second = 10;
        score = 0;


        imageArray[0] = findViewById(R.id.imageView1);
        imageArray[1] =  findViewById(R.id.imageView2);
        imageArray[2] =  findViewById(R.id.imageView3);
        imageArray[3] =  findViewById(R.id.imageView4);
        imageArray[4] =  findViewById(R.id.imageView5);
        imageArray[5] =  findViewById(R.id.imageView6);
        imageArray[6] =  findViewById(R.id.imageView7);
        imageArray[7] =  findViewById(R.id.imageView8);
        imageArray[8] =  findViewById(R.id.imageView9);
        imageArray[9] = findViewById(R.id.imageView10);
        imageArray[10] = findViewById(R.id.imageView11);
        imageArray[11] = findViewById(R.id.imageView12);
        imageArray[12] = findViewById(R.id.imageView13);
        imageArray[13] = findViewById(R.id.imageView14);
        imageArray[14] = findViewById(R.id.imageView15);
        imageArray[15] = findViewById(R.id.imageView16);

        hideImages();
        scoreView.setText("Score: "+score);
        Toast.makeText(MainActivity.this, "Level 1", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, "You just need 7 score for pass next level", Toast.LENGTH_SHORT).show();

        new CountDownTimer(15000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText("Time: "+millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                timeView.setText("Time Off");
                if (score > 6){

                        Toast.makeText(MainActivity.this, "Good! You have passed the level", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(intent);


                }else {
                    scoreView.setText("Game Over:\n Your Score: " + score);
                    Toast.makeText(MainActivity.this, "Game Over:\n Your Score: " + score, Toast.LENGTH_SHORT).show();


                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }


                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setCancelable(false);
                alert.setTitle("Restart?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        handler = new Handler();
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                scoreView.setText("Game Over:\n Your Score: "+score);
                                handler.postDelayed(runnable,2000);
                                finishAffinity();
                                System.exit(0);
                            }
                        };
                        handler.post(runnable);

                    }
                });
                alert.show();

            }
            }
        }.start();


    }

    public void increaseScore(View view){
        score ++;
        scoreView.setText("Score: "+score);
    }

    public void hideImages(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                    Random random = new Random();
                    int i = random.nextInt(16);
                    imageArray[i].setVisibility(View.VISIBLE);
                    handler.postDelayed(this,700);
                }

        };
        handler.post(runnable);


    }
}
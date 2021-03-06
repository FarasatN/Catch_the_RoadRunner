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
import java.util.Random;

public class MainActivity4 extends AppCompatActivity {
    TextView timeView;
    TextView scoreView;
    int second;
    int score;
    Runnable runnable;
    Handler handler;

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView[] imageArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toast.makeText(MainActivity4.this, "Level 3", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity4.this, "You just need 1 score for victory", Toast.LENGTH_SHORT).show();
        timeView = findViewById(R.id.timeView);
        scoreView = findViewById(R.id.scoreView);
        second = 10;
        score = 0;


        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);
        imageView16 = findViewById(R.id.imageView16);


        imageArray = new ImageView[]{imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9, imageView10, imageView11, imageView12, imageView13, imageView14, imageView15, imageView16};
        hideImages();
        scoreView.setText("Score: " + score);

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeView.setText("Time: " + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                timeView.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }


                if (score > 0) {
                    try {
                        Thread.sleep(3000);
                        Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                } else {


                    scoreView.setText("Game Over:\n Your Score: " + score);
                    Toast.makeText(MainActivity4.this, "Game Over:\n Your Score: " + score, Toast.LENGTH_SHORT).show();


                    AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity4.this);
                    alert.setCancelable(false);
                    alert.setTitle("Restart?");
                    alert.setMessage("Are you sure to restart game?");
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getIntent();
                            finish();

                            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
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
                                    Toast.makeText(MainActivity4.this, "Game Over:\n Your Score: " + score, Toast.LENGTH_SHORT).show();
                                    scoreView.setText("Game Over:\n Your Score: " + score);
                                    handler.postDelayed(runnable, 1000);
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


    public void increaseScore(View view) {
        score++;
        scoreView.setText("Score: " + score);
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(16);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, 200);
            }

        };
        handler.post(runnable);


    }


}


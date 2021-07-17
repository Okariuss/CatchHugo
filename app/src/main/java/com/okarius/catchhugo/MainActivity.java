package com.okarius.catchhugo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView scoreText, timeText, modeText;
    private ImageView photo1,photo2,photo3,photo4,photo5,photo6,photo7,photo8,photo9;
    private Runnable runnable;
    private Handler handler;
    private int score, num, time;
    private ArrayList<ImageView> photos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        countDown();

        catchMethod();

    }

    public void initialization() {
        Intent intent = getIntent();
        time = intent.getIntExtra("time",0);

        score = 0;

        scoreText = findViewById(R.id.textViewScore);
        timeText = findViewById(R.id.textViewTime);
        modeText = findViewById(R.id.mode);


        photo1 = findViewById(R.id.imageView1);
        photo2 = findViewById(R.id.imageView2);
        photo3 = findViewById(R.id.imageView3);
        photo4 = findViewById(R.id.imageView4);
        photo5 = findViewById(R.id.imageView5);
        photo6 = findViewById(R.id.imageView6);
        photo7 = findViewById(R.id.imageView7);
        photo8 = findViewById(R.id.imageView8);
        photo9 = findViewById(R.id.imageView9);

        photos = new ArrayList<>();
        photos.add(photo1);
        photos.add(photo2);
        photos.add(photo3);
        photos.add(photo4);
        photos.add(photo5);
        photos.add(photo6);
        photos.add(photo7);
        photos.add(photo8);
        photos.add(photo9);
    }

    public void countDown() {
        Intent intent = getIntent();
        String difficulty = intent.getStringExtra("difficulty");
        modeText.setText(difficulty);

        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time Remaining: " + millisUntilFinished / 1000);

            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("Your score " + score);
                alert.setTitle("Game Finished!!!");
                alert.setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,StartScreen.class);
                        finish();
                        startActivity(intent);
                    }
                });

                handler.removeCallbacks(runnable);

                for (int i = 0; i < photos.size(); i++) {
                    photos.get(i).setVisibility(View.INVISIBLE);
                    photos.get(i).setClickable(false);
                }
                alert.show();
            }
        }.start();
    }

    public void catchMethod() {
        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < photos.size(); i++) {
                    photos.get(i).setVisibility(View.INVISIBLE);
                    photos.get(i).setClickable(false);
                }

                num = (int) (Math.random()*9);

                photos.get(num).setClickable(true);
                photos.get(num).setVisibility(View.VISIBLE);

                photos.get(num).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        score++;
                        scoreText.setText("Score: " + score);
                    }
                });

                handler.postDelayed(runnable,time);

            }
        };
        handler.post(runnable);
    }

    public void restart(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
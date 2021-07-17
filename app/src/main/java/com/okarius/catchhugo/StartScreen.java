package com.okarius.catchhugo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

    }

    public void easy(View view) {
        Intent intent = new Intent(StartScreen.this, MainActivity.class);
        intent.putExtra("time",750);
        intent.putExtra("difficulty","EASY");
        startActivity(intent);
    }

    public void normal(View view) {
        Intent intent = new Intent(StartScreen.this, MainActivity.class);
        intent.putExtra("time",500);
        intent.putExtra("difficulty","NORMAL");
        startActivity(intent);
    }

    public void hard(View view) {
        Intent intent = new Intent(StartScreen.this, MainActivity.class);
        intent.putExtra("time",250);
        intent.putExtra("difficulty","HARD");
        startActivity(intent);
    }
}
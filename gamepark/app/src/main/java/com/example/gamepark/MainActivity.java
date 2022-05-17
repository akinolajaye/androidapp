package com.example.gamepark;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

    }

    @Override
    protected void onPause() {
        super.onPause();
        stopService(new Intent(MainActivity.this,StartMusic.class));


    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(MainActivity.this,StartMusic.class));


    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
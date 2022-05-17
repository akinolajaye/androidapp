package com.example.gamepark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class UserGuide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_guide);
        getSupportActionBar().hide();
        WebView webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/guide.html");
    }
}
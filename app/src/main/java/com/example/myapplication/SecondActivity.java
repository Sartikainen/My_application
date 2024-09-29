package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "EXAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "SecondActivity onCreate" + this);
        setContentView(R.layout.activity_second);
        findViewById(R.id.image);
        findViewById(R.id.progressBar);
        findViewById(R.id.text_second_activity);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "SecondActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "SecondActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "SecondActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "SecondActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "SecondActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "SecondActivity onDestroy");
    }
}

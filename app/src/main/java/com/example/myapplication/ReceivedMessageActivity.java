package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReceivedMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_message);
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");
        TextView textViewMessage = findViewById(R.id.MessageTextReceived);
        textViewMessage.setText(message);
    }
}
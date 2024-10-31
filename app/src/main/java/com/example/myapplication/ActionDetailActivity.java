package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_detail);
        TextView textOrderDetail = findViewById(R.id.text_view_order);
        Intent intent = getIntent();
        if (intent.hasExtra("order")) {
            String order = intent.getStringExtra("order");
            textOrderDetail.setText(order);
        } else {
            Intent backToLoginIntent = new Intent(this, SecondActivity.class);
            startActivity(backToLoginIntent);
        }
    }
}
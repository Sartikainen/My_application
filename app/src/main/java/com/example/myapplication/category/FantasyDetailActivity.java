package com.example.myapplication.category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class FantasyDetailActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        TextView fantasyTitle = findViewById(R.id.text_view_fantasy_title);
        TextView fantasyInfo = findViewById(R.id.text_view_fantasy_info);
        ImageView fantasyImageView = findViewById(R.id.image_view_fantasy_png);

        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("info") && intent.hasExtra("imageResourceId")) {
            String title = intent.getStringExtra("title");
            String info = intent.getStringExtra("info");
            int png = intent.getIntExtra("imageResourceId", -1);
            fantasyImageView.setImageResource(png);
            fantasyInfo.setText(info);
            fantasyTitle.setText(title);
        } else {
            Intent backToCategory = new Intent(this, FantasyCategoryActivity.class);
            startActivity(backToCategory);
        }
    }
}
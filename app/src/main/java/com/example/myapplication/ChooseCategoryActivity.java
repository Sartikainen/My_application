package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ChooseCategoryActivity extends AppCompatActivity {

    private ListView listViewCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        listViewCategories = findViewById(R.id.list_of_categories);
        listViewCategories.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    Intent intent0 = new Intent(getApplicationContext(), FantasyCategoryActivity.class);
                    startActivity(intent0);
                    break;
                case 1:
                    Intent intent1 = new Intent(getApplicationContext(), DramaCategoryActivity.class);
                    startActivity(intent1);
                    break;
            }

        });
    }
}
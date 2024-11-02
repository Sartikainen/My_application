package com.example.myapplication.category;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.category.typeofcategory.Fantasy;

import java.util.ArrayList;

public class FantasyCategoryActivity extends AppCompatActivity {

    private ArrayList<Fantasy> fantasies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fantasy_category);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        fantasies = new ArrayList<>();
        fantasies.add(new Fantasy(getString(R.string.fantasy_mandalorian_title), getString(R.string.fantasy_mandalorian_info), R.drawable.the_mandalorian_png));
        fantasies.add(new Fantasy(getString(R.string.fantasy_witcher_title), getString(R.string.fantasy_witcher_info), R.drawable.the_witcher_png));
        fantasies.add(new Fantasy(getString(R.string.fantasy_harry_potter_title), getString(R.string.fantasy_harry_potter_info), R.drawable.harry_potter));
        ListView listViewFantasy = findViewById(R.id.list_view_fantasy);

        ArrayAdapter<Fantasy> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fantasies);
        listViewFantasy.setAdapter(adapter);

        listViewFantasy.setOnItemClickListener((parent, view, position, id) -> {
            Fantasy fantasy = fantasies.get(position);
            Intent intent = new Intent(getApplicationContext(), FantasyDetailActivity.class);
            intent.putExtra("title", fantasy.getTitle());
            intent.putExtra("info", fantasy.getInfo());
            intent.putExtra("imageResourceId", fantasy.getImageResourceId());
            startActivity(intent);
        });
    }
}
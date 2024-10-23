package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMovies;
    private TextView descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_describe);
        spinnerMovies = findViewById(R.id.action_bar_spinner);
        descriptionText = findViewById(R.id.text_describe_movie);
        button.setOnClickListener(view -> {
            Snackbar.make(view, "Запрос выполнен", Snackbar.LENGTH_LONG).show();
            showDescription();
        });

        findViewById(R.id.button_activity_second).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, SecondActivity.class)));

        findViewById(R.id.button_movies).setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ThirdActivity.class)));

        findViewById(R.id.finish).setOnClickListener(view ->
                finish());
    }

    public void showDescription() {
        int position = spinnerMovies.getSelectedItemPosition();
        String description = getDescriptionByPosition(position);
        descriptionText.setText(description);
    }

    private String getDescriptionByPosition (int position) {
        String[] descriptions = getResources().getStringArray(R.array.decriptions_of_movies);
        return descriptions[position];
    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.category.ChooseCategoryActivity;
import com.example.myapplication.category.GuessMovieActivity;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMovies;
    private TextView descriptionText;
    private EditText editTextMessage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerMovies = findViewById(R.id.action_bar_spinner);
        descriptionText = findViewById(R.id.text_describe_movie);
        editTextMessage = findViewById(R.id.message_text);

        findViewById(R.id.button_describe).setOnClickListener(view -> {
            Snackbar.make(view, "Запрос выполнен", Snackbar.LENGTH_LONG).show();
            showDescription();
        });

        findViewById(R.id.button_send).setOnClickListener(view -> {
            String message = editTextMessage.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, message);
            Intent choosenIntent = Intent.createChooser(intent, getString(R.string.choose_method));
            startActivity(choosenIntent);
        });

        findViewById(R.id.button_activity_second).setOnClickListener(view ->
                startActivity(new Intent(this, SecondActivity.class)));

        findViewById(R.id.button_movies).setOnClickListener(view ->
                startActivity(new Intent(this, ChooseCategoryActivity.class)));

        findViewById(R.id.finish).setOnClickListener(view ->
                finish());

        findViewById(R.id.button_stopwatch).setOnClickListener(view ->
                startActivity(new Intent(this, StopWatch.class)));

        findViewById(R.id.button_table).setOnClickListener(view ->
                startActivity(new Intent(this, TableActivity.class)));

        findViewById(R.id.button_guess_movie).setOnClickListener(view ->
                startActivity(new Intent(this, GuessMovieActivity.class)));

        findViewById(R.id.button_test_brain).setOnClickListener(view ->
                startActivity(new Intent(this, TestBrainActivity.class)));
    }

    public void showDescription() {
        int position = spinnerMovies.getSelectedItemPosition();
        String description = getDescriptionByPosition(position);
        descriptionText.setText(description);
    }

    private String getDescriptionByPosition(int position) {
        String[] descriptions = getResources().getStringArray(R.array.decriptions_of_movies);
        return descriptions[position];
    }
}

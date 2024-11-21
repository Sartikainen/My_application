package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView textViewFinalScore;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        textViewFinalScore = findViewById(R.id.text_view_final_score);
        Intent intent = getIntent();
        if (intent != null & intent.hasExtra("result") & intent.hasExtra("percent")) {
            int result = intent.getIntExtra("result", 0);
            int percent = intent.getIntExtra("percent", 0);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int max = preferences.getInt("max", 0);
            String score = String.format("Ваш результат: %s\n" +
                    "Процент правильных ответов: %s\n" +
                    "Максимальный результат: %s", result, percent, max);
            textViewFinalScore.setText(score);
        }

        findViewById(R.id.button_restart_game).setOnClickListener(view ->
                startActivity(new Intent(this, TestBrainActivity.class)));

        findViewById(R.id.button_exit_main_menu).setOnClickListener(view ->
                startActivity(new Intent(this, MainActivity.class)));
    }
}
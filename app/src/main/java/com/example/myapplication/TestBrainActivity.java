package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TestBrainActivity extends AppCompatActivity {

    private TextView textViewOpinion0;
    private TextView textViewOpinion1;
    private TextView textViewOpinion2;
    private TextView textViewOpinion3;
    private TextView textViewScore;
    private TextView textViewTimer;
    private TextView textViewQuestion;
    private ArrayList<TextView> options = new ArrayList<>();

    private String question;
    private int rightAnswer;
    private int rightAnswerPosition;
    private boolean isPositive;
    private int min = 5;
    private int max = 30;
    private int countScore = 0;
    private int allScore = 0;
    private int percent = 0;
    private boolean gameOver = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_brain);
        textViewOpinion0 = findViewById(R.id.text_view_opinion0);
        textViewOpinion1 = findViewById(R.id.text_view_opinion1);
        textViewOpinion2 = findViewById(R.id.text_view_opinion2);
        textViewOpinion3 = findViewById(R.id.text_view_opinion3);
        textViewScore = findViewById(R.id.text_view_score);
        textViewTimer = findViewById(R.id.text_view_timer);
        textViewQuestion = findViewById(R.id.text_view_question);
        options.add(textViewOpinion0);
        options.add(textViewOpinion1);
        options.add(textViewOpinion2);
        options.add(textViewOpinion3);

        for (TextView option : options) {
            option.setOnClickListener(this::onClickAnswer);
        }
        playNext();
        CountDownTimer timer = new CountDownTimer(40000, 1000) {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTimer.setText(getTime(millisUntilFinished));
                if (millisUntilFinished < 10000) {
                    textViewTimer.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                }
            }

            @Override
            public void onFinish() {
                Toast.makeText(TestBrainActivity.this, "Время вышло", Toast.LENGTH_SHORT).show();
                gameOver=true;
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int max = preferences.getInt("max", 0);
                if (countScore>= max) {
                    preferences.edit().putInt("max", countScore).apply();
                }
                Intent intent = new Intent(TestBrainActivity.this, ScoreActivity.class);
                intent.putExtra("result", countScore);
                intent.putExtra("percent", percent);
                startActivity(intent);
            }
        };
        timer.start();
    }

    @SuppressLint("DefaultLocale")
    private String getTime(long millis) {
        int secondsUntilFinished = (int) (millis/1000);
        int minutes = secondsUntilFinished/60;
        int seconds = secondsUntilFinished % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @SuppressLint("SetTextI18n")
    private void playNext() {
        generateQuestion();
        for (int i = 0; i < options.size(); i++) {
            if (i == rightAnswerPosition) {
                options.get(i).setText(Integer.toString(rightAnswer));
            } else {
                options.get(i).setText(Integer.toString(generateWrongAnswer()));
            }
        }
        textViewScore.setText(String.format("%s / %s", countScore, allScore));
    }

    private void generateQuestion() {
        int a = (int) (Math.random() * (max - min + 1) + min);
        int b = (int) (Math.random() * (max - min + 1) + min);
        int mark = (int) (Math.random() * 2);
        isPositive = mark == 1;
        if (isPositive) {
            rightAnswer = a + b;
            question = String.format("%s + %s", a, b);
        } else {
            rightAnswer = a - b;
            question = String.format("%s - %s", a, b);
        }
        textViewQuestion.setText(question);
        rightAnswerPosition = (int) (Math.random() * 4);
    }

    private int generateWrongAnswer() {
        int result;
        do {
            result = (int) (Math.random() * max * 2 + 1) - (max - min);
        } while (result == rightAnswer);
        return result;
    }

    private void onClickAnswer(View view) {
        if (!gameOver) {
            TextView textView = (TextView) view;

            String answer = textView.getText().toString();
            int chosenAnswer = Integer.parseInt(answer);
            if (chosenAnswer == rightAnswer) {
                countScore++;
            }
            allScore++;
            percent = countScore * 100 / allScore;
            playNext();
        }
    }
}
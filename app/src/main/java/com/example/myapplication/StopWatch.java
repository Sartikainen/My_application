package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {

    private TextView textViewStopwatch;
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        textViewStopwatch = findViewById(R.id.text_stopwatch);
        if (savedInstanceState != null) {                       // если есть сохранённые значения, то нашим переменным присваиваем сохранённые данные
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();

        findViewById(R.id.button_start_stopwatch). setOnClickListener(view ->
            isRunning = true
        );

        findViewById(R.id.button_pause_stopwatch). setOnClickListener(view ->
            isRunning = false
        );

        findViewById(R.id.button_reset_stopwatch). setOnClickListener(view -> {
            isRunning = false;
            seconds = 0;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning=isRunning;
        isRunning=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning=wasRunning;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) { //метод сохраняет переданные данные при повороте экрана
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("wasRunning", wasRunning);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds % 3600)/60;
                int secs = seconds % 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);
                textViewStopwatch.setText(time);

                if(isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}

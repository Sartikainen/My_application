package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHelloOrder;
    private TextView textViewAddOrder;
    private CheckBox checkBoxTowel;
    private CheckBox checkBoxChips;
    private CheckBox checkBoxBeer;
    private Spinner spinnerMovie;
    private Spinner spinnerSleep;

    private String name;
    private String password;
    private String action;

    private StringBuilder builderAdditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        Intent intent = getIntent();
        if (intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        } else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        textViewHelloOrder = findViewById(R.id.text_hello_order);
        String hello = String.format(getString(R.string.text_hello_client), name);
        textViewHelloOrder.setText(hello);

        action = getString(R.string.text_watch_movie);
        textViewAddOrder = findViewById(R.id.text_add_order);

        checkBoxTowel = findViewById(R.id.checkbox_towel);
        checkBoxChips = findViewById(R.id.checkbox_chips);
        checkBoxBeer = findViewById(R.id.checkbox_beer);
        spinnerMovie = findViewById(R.id.spinner_choose_movie);
        spinnerSleep = findViewById(R.id.spinner_choose_sleep);

        builderAdditions = new StringBuilder();


        findViewById(R.id.button_choose_movie).setOnClickListener(view -> {
            action = getString(R.string.text_watch_movie);
            spinnerMovie.setVisibility(View.VISIBLE);
            spinnerSleep.setVisibility(View.INVISIBLE);
            checkBoxChips.setVisibility(View.VISIBLE);
            checkBoxBeer.setVisibility(View.VISIBLE);
            String additions = String.format(getString(R.string.text_add_order), action);
            textViewAddOrder.setText(additions);
        });

        findViewById(R.id.button_choose_sleep).setOnClickListener((view -> {
            action = getString(R.string.text_sleep);
            spinnerMovie.setVisibility(View.INVISIBLE);
            spinnerSleep.setVisibility(View.VISIBLE);
            checkBoxChips.setVisibility(View.INVISIBLE);
            checkBoxBeer.setVisibility(View.INVISIBLE);
            String additions = String.format(getString(R.string.text_add_order), action);
            textViewAddOrder.setText(additions);
        }));

        findViewById(R.id.image_button_action).setOnClickListener(view -> {
            builderAdditions.setLength(0);
            if (checkBoxTowel.isChecked()) {
                builderAdditions.append(getString(R.string.text_towel)).append(" ");
            }
            if (checkBoxChips.isChecked() && action.equals(getString(R.string.text_watch_movie))) {
                builderAdditions.append(getString(R.string.text_chips)).append(" ");
            }
            if (checkBoxBeer.isChecked() && action.equals(getString(R.string.text_watch_movie))) {
                builderAdditions.append(getString(R.string.text_beer)).append(" ");
            }
            String optionOfAction = "";
            if (action.equals(getString(R.string.text_watch_movie))) {
                optionOfAction = spinnerMovie.getSelectedItem().toString();
            } else {
                optionOfAction = spinnerSleep.getSelectedItem().toString();
            }
            String order = String.format(getString(R.string.order), name, password, action, optionOfAction);
            String additions;
            if (builderAdditions.length() > 0) {
                additions = getString(R.string.need_additions) + builderAdditions.toString();
            } else {
                additions = "";
            }
            String fullAction = order + additions;
            Intent intentAction = new Intent(this, ActionDetailActivity.class);
            intentAction.putExtra("order", fullAction);
            startActivity(intentAction);
        });
    }
}
package com.example.myapplication.category;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.HashMap;
import java.util.Map;

public class CapitalActivity extends AppCompatActivity {

    private EditText etSearch;
    private EditText etCountry;
    private EditText etCapital;
    private TextView tvResult;
    private TextView tvListOfCapitals;
    private TextView tvCapitalResult;
    private Button buttonInsert;

    private Map<String, String> capitals;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital);
        etSearch = findViewById(R.id.etSearch);
        etCountry = findViewById(R.id.etCountry);
        etCapital = findViewById(R.id.etCapital);
        tvResult = findViewById(R.id.tvResult);
        tvListOfCapitals = findViewById(R.id.tvListOfCapitals);
        buttonInsert = findViewById(R.id.buttonInsert);
        tvCapitalResult = findViewById(R.id.tvCapitalResult);
        capitals = new HashMap<>();

        buttonInsert.setOnClickListener(view -> {
            String country = etCountry.getText().toString();
            String capital = etCapital.getText().toString();
            if (!country.isEmpty() && !capital.isEmpty()) {
                capitals.put(country, capital);
            }
            etCountry.setText("");
            etCapital.setText("");
            tvListOfCapitals.setText("");
            for (String key : capitals.keySet()) {
                tvListOfCapitals.setText(tvListOfCapitals.getText() + key + " - " + capitals.get(key) + "\n");
            }
            
            etSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (capitals.containsKey(s.toString())) {
                        tvCapitalResult.setText(capitals.get(s.toString()));
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        });
    }
}
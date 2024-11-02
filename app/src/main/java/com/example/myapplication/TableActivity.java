package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity {

    private ListView listViewTable;
    private SeekBar seekBar;

    private ArrayList<Integer> numbers;

    private int max = 20;
    private int min = 1;
    private int count = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        listViewTable = findViewById(R.id.list_view_table);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(max);
        numbers = new ArrayList<>();
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, numbers);
        listViewTable.setAdapter(arrayAdapter);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < min) {
                    seekBar.setProgress(min);
                }
                numbers.clear();
                for (int i = min; i<=count; i++) {
                    numbers.add(seekBar.getProgress()*i);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress(10);
    }
}
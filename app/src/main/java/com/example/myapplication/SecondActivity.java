package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SecondActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editTextName = findViewById(R.id.edit_text_name);
        editTextPassword = findViewById(R.id.edit_text_password);


        findViewById(R.id.button_lk).setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String password = editTextPassword.getText().toString().trim();
            if (!name.isEmpty() && !password.isEmpty()) {
                Intent intent = new Intent(this, CreateOrderActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("password", password);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

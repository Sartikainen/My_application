package com.example.myapplication.usersDB;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etLastname;
    private EditText etAge;
    private Spinner spinnerSex;

    private FirebaseFirestore db;
    List<User> users;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        etName = findViewById(R.id.etName);
        etLastname = findViewById(R.id.etLastname);
        spinnerSex = findViewById(R.id.spinnerSex);
        etAge = findViewById(R.id.etAge);
        db = FirebaseFirestore.getInstance();
        users = new ArrayList<>();
        findViewById(R.id.buttonAddUser).setOnClickListener(view -> {
            String name = etName.getText().toString();
            String lastname = etLastname.getText().toString();
            String sex = spinnerSex.getSelectedItem().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            User user = new User(name, lastname, sex, age);
            db.collection("users").document().set(user)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Пользователь успешно добавлен", Toast.LENGTH_SHORT).show();
                    }) .addOnFailureListener(e -> {
                        Toast.makeText(this, "Пользователь не добавлен" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
            Intent intent = new Intent(AddUserActivity.this, UsersActivity.class);
            startActivity(intent);
        });
    }
}
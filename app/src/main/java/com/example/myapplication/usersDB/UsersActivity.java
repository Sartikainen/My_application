package com.example.myapplication.usersDB;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    private UserAdapter adapter;
    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        rvUsers = findViewById(R.id.rvUsers);
        adapter = new UserAdapter();
        adapter.setUsers(new ArrayList<>());
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(adapter);
        findViewById(R.id.ivAddUser).setOnClickListener(view ->
                startActivity(new Intent(this, AddUserActivity.class)));
    }
}
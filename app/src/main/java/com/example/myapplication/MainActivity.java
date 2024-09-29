package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EXAMPLE";
//    private static final String KEY_STRING = "KEY_STRING";
    private Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "MainActivity onCreate" + this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Вопрос отправлен", Snackbar.LENGTH_LONG).show();
//                 startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                 Log.e("MY_LOG", "Yami");
            }
        });

        findViewById(R.id.button_activity_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                final Intent intent = new Intent(MainActivity.this, MyService.class);
//                startService(intent);
            }
        });

        findViewById(R.id.button_movies).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
//                final Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
//                if (cursor != null) {
//                  cursor.moveToFirst();
//                do {
//                      @SuppressLint("Range") final String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//                      Log.e("MY_LOG", name);
//                  } while (cursor.moveToNext());
//                 cursor.close();
//                }
            }
        });

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.e(TAG, "MainActivity onSaveInstanceState");
//        outState.putString(KEY_STRING, editText.getText().toString());
//    }
//
//    @Override
//    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onRestoreInstanceState(savedInstanceState, persistentState);
//        Log.e(TAG, "MainActivity onRestoreInstanceState");
//        final String text = savedInstanceState.getString(KEY_STRING);
//        if(text != null) {
//            button.setText(text);
//        }
//    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "MainActivity onRestart");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MainActivity onDestroy");
    }
}

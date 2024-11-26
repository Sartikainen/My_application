package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import classes.Note;
import classes.NotesAdapter;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    public static final ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        recyclerViewNotes = findViewById(R.id.recycler_view_notes);
        if (notes.isEmpty()) {
            notes.add(new Note("Барбер", "Сделать причёску", "Понедельник", 2));
            notes.add(new Note("Юстиция", "Сделать личный кабинет", "Понедельник", 1));
            notes.add(new Note("Банк", "Открыть счёт и терминал", "Понедельник", 1));
            notes.add(new Note("Рисунок", "Купить карандаши", "Понедельник", 3));
            notes.add(new Note("Посылка", "Передать посылку", "Пятница", 3));
            notes.add(new Note("Договор аренды", "Переоформить договор", "Понедельник", 1));
            notes.add(new Note("Расписание", "Сделать расписание", "Воскресенье", 3));
        }
        NotesAdapter adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(adapter);

        findViewById(R.id.floatingActionButton).setOnClickListener(view ->
                startActivity(new Intent(this, AddNoteActivity.class)));
    }
}
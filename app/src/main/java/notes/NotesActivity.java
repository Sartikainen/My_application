package notes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNotes;
    private NotesAdapter adapter;
    private final ArrayList<Note> notes = new ArrayList<>();
    private NotesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        database = NotesDatabase.getInstance(this);
        recyclerViewNotes = findViewById(R.id.recycler_view_notes);
        adapter = new NotesAdapter(notes);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        getData();
        recyclerViewNotes.setAdapter(adapter);
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(NotesActivity.this, "Нажат", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                remove(position);
                Toast.makeText(NotesActivity.this, "Заметка удалена", Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
                Toast.makeText(NotesActivity.this, "Заметка удалена", Toast.LENGTH_SHORT).show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes);

        findViewById(R.id.floatingActionButton).setOnClickListener(view ->
                startActivity(new Intent(this, AddNoteActivity.class)));
    }

    @SuppressLint("NotifyDataSetChanged")
    private void remove(int position) {
        Note note = notes.get(position);
        database.notesDao().deleteNote(note);
        getData();
        adapter.notifyDataSetChanged();
    }

    private void getData() {
        List<Note> notesFromDB = database.notesDao().getAllNotes();
        notes.clear();
        notes.addAll(notesFromDB);
    }

}
package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteActivity extends AppCompatActivity {

    int noteID = -1;
    EditText noteEditor;

    public void saveClick(View view) {
        noteEditor = findViewById(R.id.noteEditor);
        String content = noteEditor.getText().toString();
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);
        SharedPreferences sharedPreferences = getSharedPreferences("c.sakshi.lab5", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        //TODO save info to db?
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteID == -1) {
            title = "NOTE_" + (Activity2.notes.size() + 1);
            dbHelper.saveNotes(username, title, content, date);
        }
        else {
            title = "NOTE_" + (noteID + 1);
            dbHelper.updateNote(title, date, content, username);
        }

        Intent intent = new Intent(getApplicationContext(), Activity2.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteEditor = findViewById(R.id.noteEditor);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteid", -1);

        if (noteID != -1) {
            Note note = Activity2.notes.get(noteID);
            String noteContent = note.getContent();
            noteEditor.setText(noteContent);
        }
    }
}

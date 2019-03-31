package com.example.nootes;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ListView lv;
    static ArrayList<Note> notes;
    static CustomAdapter adapter;
    static DatabaseHelper helper;
    Dialog MyDialog;
    Button yes, no;
    EditText note_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listview);
        helper = new DatabaseHelper(this);
        notes = new ArrayList<>();
        notes = helper.getNotes();
        adapter = new CustomAdapter(this, notes);
        lv.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.add:
                MyCustomDialogAlert();
        }
        return super.onOptionsItemSelected(item);
    }

    public void MyCustomDialogAlert(){
        MyDialog = new Dialog(this);
        MyDialog.setContentView(R.layout.notedialog);
        MyDialog.setTitle("Add new note");

        note_text = MyDialog.findViewById(R.id.editText);
        yes = MyDialog.findViewById(R.id.yes);
        no = MyDialog.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note note = new Note(note_text.getText().toString());
                helper.insertNote(note);

                notes = helper.getNotes();
                adapter = new CustomAdapter(getBaseContext(), notes);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                MyDialog.cancel();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDialog.cancel();
            }
        });
        MyDialog.show();
    }


}


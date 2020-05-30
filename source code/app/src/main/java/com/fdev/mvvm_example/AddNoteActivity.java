package com.fdev.mvvm_example;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;


public class AddNoteActivity extends AppCompatActivity {

    private EditText mEditTextTitle;
    private EditText mEditTextDesc;
    private NumberPicker mNumberPickerPriority;

    public static final String EXTRA_REPLAY_NOTE_TITLE = "replay_note_title";
    public static final String EXTRA_REPLAY_NOTE_DESC = "replay_note_desc";
    public static final String EXTRA_REPLAY_NOTE_PRIORITY = "replay_note_priority";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mEditTextDesc = findViewById(R.id.edit_text_desc);
        mEditTextTitle = findViewById(R.id.edit_text_title);
        mNumberPickerPriority = findViewById(R.id.numberpicker_priority);

        mNumberPickerPriority.setMaxValue(10);
        mNumberPickerPriority.setMinValue(1);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        setTitle("Add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = mEditTextTitle.getText().toString();
        String desc = mEditTextDesc.getText().toString();
        int priority = mNumberPickerPriority.getValue();

        if(!title.trim().isEmpty() || !desc.trim().isEmpty()){
            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_REPLAY_NOTE_TITLE, title);
            replyIntent.putExtra(EXTRA_REPLAY_NOTE_DESC,desc);
            replyIntent.putExtra(EXTRA_REPLAY_NOTE_PRIORITY,priority);
            setResult(RESULT_OK,replyIntent);
            finish();
        }else{
            Toast.makeText(this,"All field has to be filled",Toast.LENGTH_SHORT).show();
        }
    }
}

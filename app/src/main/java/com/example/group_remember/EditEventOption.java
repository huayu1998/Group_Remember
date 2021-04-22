package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class EditEventOption extends AppCompatActivity {

    Button back, done, addTextContent, chooseDate, backgroundOption, addMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event_option);

        back = (Button) findViewById(R.id.back);
        done = (Button) findViewById(R.id.done);
        addTextContent = (Button) findViewById(R.id.addTextContent);
        chooseDate = (Button) findViewById(R.id.chooseDate);
        backgroundOption = (Button) findViewById(R.id.backgroundOption);
        addMusic = (Button) findViewById(R.id.addMusic);
    }
}
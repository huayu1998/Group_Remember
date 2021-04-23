package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Day extends AppCompatActivity {

    TextView title;
    TextView length;
    TextView time;
    TextView message;
    Button chooseBackground;
    FloatingActionButton music;
    Button editEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        title = (TextView)findViewById(R.id.titleT);
        length = (TextView)findViewById(R.id.lengthT);
        time = (TextView)findViewById(R.id.timeT);
        message = (TextView)findViewById(R.id.messageT);
        editEvent = (Button)findViewById(R.id.editTextb);
        chooseBackground = (Button)findViewById(R.id.photo);

        chooseBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, BackgroundOption.class);
                startActivity(intent);
            }
        });

        music = (FloatingActionButton)findViewById(R.id.musicFab);

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, PlayMusic.class);
                startActivity(intent);
            }
        });

        editEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, EventEdit.class);
                startActivity(intent);
            }
        });

    }

}
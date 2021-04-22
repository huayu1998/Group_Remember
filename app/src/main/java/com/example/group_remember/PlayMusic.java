package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class PlayMusic extends AppCompatActivity {

    RadioButton music1;
    RadioButton music2;
    RadioButton music3;
    RadioButton music4;
    Button finishMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        music1 = (RadioButton) findViewById(R.id.music1);
        music2 = (RadioButton) findViewById(R.id.music2);
        music3 = (RadioButton) findViewById(R.id.music3);
        music4 = (RadioButton) findViewById(R.id.music4);
        finishMusic = (Button) findViewById(R.id.finishMusic);

        finishMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
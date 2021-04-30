package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayMusic extends AppCompatActivity {

    RadioButton music1;
    RadioButton music2;
    RadioButton music3;
    RadioButton music4;
    RadioGroup musicGruop;
    Button back, finishMusic;
    boolean musicSelected = false;
    MediaPlayer mp;


    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");//在另一个activity中用于获取对象
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);//之后将serializable对象强转使用即可
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);

        music1 = (RadioButton) findViewById(R.id.music1);
        music2 = (RadioButton) findViewById(R.id.music2);
        music3 = (RadioButton) findViewById(R.id.music3);
        music4 = (RadioButton) findViewById(R.id.music4);
        musicGruop = (RadioGroup) findViewById(R.id.musicGroup);
        back = (Button) findViewById(R.id.back);
        finishMusic = (Button) findViewById(R.id.finishMusic);

        if(version.equals("old")){
            musicSelected = true;
            String i = date.getMusic();
            RadioButton radioButton;
            if(i.equals("Music1")){
                radioButton = findViewById(R.id.music1);
                if(!radioButton.isChecked()) {
                    radioButton.setChecked(true);
                }
            }
            if(i.equals("Music2")){
                radioButton = findViewById(R.id.music2);
                if(!radioButton.isChecked()) {
                    radioButton.setChecked(true);
                }
            }
            if(i.equals("Music3")){
                radioButton = findViewById(R.id.music3);
                if(!radioButton.isChecked()) {
                    radioButton.setChecked(true);
                }
            }
            if(i.equals("Music4")){
                radioButton = findViewById(R.id.music4);
                if(!radioButton.isChecked()) {
                    radioButton.setChecked(true);
                }
            }

        }


        musicGruop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.music1) {
                    Toast.makeText(PlayMusic.this, "Music 1 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music1");
                    musicSelected = true;

                    if (mp == null) {
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song1);
                        mp.start();
                    }
                    else if (mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song1);
                        mp.start();
                    }
                    else if (!mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song1);
                        mp.start();
                    }

                }
                else if(checkedId == R.id.music2) {
                    Toast.makeText(PlayMusic.this, "Music 2 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music2");
                    musicSelected = true;

                    if (mp == null) {
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song2);
                        mp.start();
                    }
                    else if (mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song2);
                        mp.start();
                    }
                    else if (!mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song2);
                        mp.start();
                    }

                }
                else if (checkedId == R.id.music3){
                    Toast.makeText(PlayMusic.this, "Music 3 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music3");
                    musicSelected = true;

                    if (mp == null) {
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song3);
                        mp.start();
                    }
                    else if (mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song3);
                        mp.start();
                    }
                    else if (!mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song3);
                        mp.start();
                    }

                }
                else if (checkedId == R.id.music4){
                    Toast.makeText(PlayMusic.this, "Music 4 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music4");
                    musicSelected = true;

                    if (mp == null) {
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song4);
                        mp.start();
                    }
                    else if (mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song4);
                        mp.start();
                    }
                    else if (!mp.isPlaying()) {
                        mp.release();
                        mp = MediaPlayer.create(PlayMusic.this, R.raw.song4);
                        mp.start();
                    }

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayMusic.this, EditEventOption.class);
                intent.putExtra("dateList", (Serializable)dateList);
                intent.putExtra("int",number);
                intent.putExtra("version",version);

                if (mp != null) {
                    mp.release();
                }
                startActivity(intent);
            }
        });

        finishMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (musicSelected == false) {
                    Toast.makeText(PlayMusic.this, "Please select a music.", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(PlayMusic.this, EditEventOption.class);
                    intent.putExtra("dateList", (Serializable)dateList);
                    intent.putExtra("int",number);
                    intent.putExtra("version",version);
                    if(mp!=null) {
                        mp.release();
                    }
                    startActivity(intent);
                }
            }
        });
    }

}
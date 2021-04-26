package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class Day extends AppCompatActivity {

    TextView title;
    TextView length;
    TextView time;
    TextView message;
    Button button;
    FloatingActionButton music;
    Button editText;
    MediaPlayer mp;

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        //接收信息
        Intent intent = getIntent();
        Serializable serializable = getIntent().getSerializableExtra("dateList");//在另一个activity中用于获取对象
        if(serializable != null) {
            dateList = new ArrayList<Date>();
            dateList.addAll((ArrayList<Date>) serializable);//之后将serializable对象强转使用即可
        }
        number = intent.getIntExtra("int",0);
        version = intent.getStringExtra("version");
        date = dateList.get(number);

        title = (TextView)findViewById(R.id.titleT);
        length = (TextView)findViewById(R.id.lengthT);
        time = (TextView)findViewById(R.id.timeT);
        message = (TextView)findViewById(R.id.messageT);
        button = (Button)findViewById(R.id.photo);

        // Play the music
        if (!date.getMusic().isEmpty()) {

            if (date.getMusic().equals("Music1")) {
                mp = MediaPlayer.create(Day.this, R.raw.song1);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music2")) {
                mp = MediaPlayer.create(Day.this, R.raw.song2);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music3")) {
                mp = MediaPlayer.create(Day.this, R.raw.song3);
                mp.setLooping(true);
                mp.start();
            }
            else if (date.getMusic().equals("Music4")) {
                mp = MediaPlayer.create(Day.this, R.raw.song4);
                mp.setLooping(true);
                mp.start();
            }

        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Day.this, EditEventOption.class);
                startActivity(intent);
            }
        });

        //music = (FloatingActionButton)findViewById(R.id.musicFab);

        //music.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
       //         Intent intent = new Intent(Day.this, PlayMusic.class);
       //         startActivity(intent);
       //     }
      //  });
      //  editText = (Button)findViewById(R.id.editb);
      //  editText.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(Day.this, EventEdit.class);
        //        startActivity(intent);
       //     }
       // });

    }

}
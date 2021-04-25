package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    //接收信息
    ArrayList<Date> dateList;
    int number;
    String version;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

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

        music1 = (RadioButton) findViewById(R.id.music1);
        music2 = (RadioButton) findViewById(R.id.music2);
        music3 = (RadioButton) findViewById(R.id.music3);
        music4 = (RadioButton) findViewById(R.id.music4);
        musicGruop = (RadioGroup) findViewById(R.id.musicGroup);
        back = (Button) findViewById(R.id.back);
        finishMusic = (Button) findViewById(R.id.finishMusic);

        musicGruop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.music1) {
                    Toast.makeText(PlayMusic.this, "Music 1 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music1");
                    musicSelected = true;
                }
                else if(checkedId == R.id.music2) {
                    Toast.makeText(PlayMusic.this, "Music 2 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music2");
                    musicSelected = true;
                }
                else if (checkedId == R.id.music3){
                    Toast.makeText(PlayMusic.this, "Music 3 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music3");
                    musicSelected = true;
                }
                else if (checkedId == R.id.music4){
                    Toast.makeText(PlayMusic.this, "Music 4 selected.", Toast.LENGTH_LONG).show();
                    date.setMusic("Music4");
                    musicSelected = true;
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
                    startActivity(intent);
                }
            }
        });
    }

}
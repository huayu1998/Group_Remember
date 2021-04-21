package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EventOption extends AppCompatActivity implements View.OnClickListener {

    Button back,done,addTextContent,chooseDate,backgroundOption,addMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_option);

        back = (Button) findViewById(R.id.Back);
        done = (Button) findViewById(R.id.Done);
        addTextContent = (Button) findViewById(R.id.AddTextContent);
        chooseDate = (Button) findViewById(R.id.ChooseDate);
        backgroundOption = (Button) findViewById(R.id.BackgroundOption);
        addMusic = (Button) findViewById(R.id.AddMusic);
        back.setOnClickListener(this);
        done.setOnClickListener(this);
        addTextContent.setOnClickListener(this);
        chooseDate.setOnClickListener(this);
        backgroundOption.setOnClickListener(this);
        addMusic.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.Back:
                break;
            case R.id.Done:
                break;
            case R.id.AddTextContent:
                break;
            case R.id.ChooseDate:
                break;
            case R.id.BackgroundOption:
                break;
            case R.id.AddMusic:
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
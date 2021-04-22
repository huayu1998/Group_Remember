package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class EditEventOption extends AppCompatActivity implements View.OnClickListener {

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
            case R.id.back:
                Intent intent1 = new Intent(EditEventOption.this, Day.class);
                startActivity(intent1);
                break;
            case R.id.done:
                Intent intent2 = new Intent(EditEventOption.this, Day.class);
                startActivity(intent2);
                break;
            case R.id.addTextContent:
                Intent intent3 = new Intent(EditEventOption.this, EditTextForEvent.class);
                startActivity(intent3);
                break;
            case R.id.chooseDate:
                Intent intent4 = new Intent(EditEventOption.this, ChooseDate.class);
                startActivity(intent4);
                break;
            case R.id.backgroundOption:
                Intent intent5 = new Intent(EditEventOption.this, BackgroundOption.class);
                startActivity(intent5);
                break;
            case R.id.addMusic:
                Intent intent6 = new Intent(EditEventOption.this, PlayMusic.class);
                startActivity(intent6);
                break;
        }
    }

}
package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EventEdit extends AppCompatActivity {

    EditText year, month, day, topic, eventContent;
    Button finish;
    Date date = new Date();
    boolean validTitleNDescription = false;
    boolean validDate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);

        year = (EditText) findViewById(R.id.year);
        month = (EditText) findViewById(R.id.month);
        day = (EditText) findViewById(R.id.day);
        topic = (EditText) findViewById(R.id.topic);
        eventContent = (EditText) findViewById(R.id.eventContent);
        finish = (Button) findViewById(R.id.finish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!setTopicNDescription()) {
                    Toast.makeText(EventEdit.this, "Please enter title and description.", Toast.LENGTH_LONG).show();
                }
                else if (!setDate()) {
                    Toast.makeText(EventEdit.this, "Please enter valid date.", Toast.LENGTH_LONG).show();
                }
                else {
                    //getIntent().putExtra("DateDetails", );
                    //getIntent().putExtra("DateDetails", );
                    //getIntent().putExtra("DateDetails", );
                    finish();
                }
            }
        });
    }

    public boolean setTopicNDescription() {

        if (!topic.getText().toString().isEmpty() && !eventContent.getText().toString().isEmpty()) {
            date.setTopic(topic.getText().toString());
            date.setText(eventContent.getText().toString());
            validTitleNDescription = true;
        }
        else {
            validTitleNDescription = false;
        }
        return validTitleNDescription;
    }

    public boolean setDate() {

        if (Integer.valueOf(year.getText().toString()) >= 2016 && Integer.valueOf(year.getText().toString()) <= 2026
            && Integer.valueOf(month.getText().toString()) >= 1 && Integer.valueOf(month.getText().toString()) <= 12
            && Integer.valueOf(day.getText().toString()) >= 1 && Integer.valueOf(day.getText().toString()) <= 31) {

            date.setYear(Integer.valueOf(year.getText().toString()));
            date.setMonth(Integer.valueOf(month.getText().toString()));
            date.setDay(Integer.valueOf(day.getText().toString()));
            validDate = true;
        }
        else {
            validDate = false;
        }
        return validDate;
    }
}
package com.example.group_remember;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseDate extends AppCompatActivity {

     EditText inputYear;
     EditText inputMonth;
     EditText inputDay;
     Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        inputYear = (EditText) findViewById(R.id.yearInput);
        inputMonth = (EditText) findViewById(R.id.monthInput);
        inputDay = (EditText) findViewById(R.id.dayInput);
        finish = (Button) findViewById(R.id.FinishDate);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseDate.this, Day.class);
                startActivity(intent);
            }
        });

    }
}